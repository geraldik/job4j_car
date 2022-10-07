package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.Driver;
import ru.job4j.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {
    private static final String FAIL_MESSAGE = "Пользователь с такой почтой уже существует";
    private static final String SUCCESS_MESSAGE = "Пользователь зарегестрирован успешно";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute Driver driver) {
        Optional<Driver> regDriver = userService.create(driver);
        if (regDriver.isEmpty()) {
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    @GetMapping("/formRegistration")
    public String formRegistration(Model model, HttpSession session) {
        SessionControl.getUserSession(model, session);
        return "registration";
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("message", SUCCESS_MESSAGE);
        return "success";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        model.addAttribute("message", FAIL_MESSAGE);
        return "fail";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Driver driver, HttpServletRequest req) {
        Optional<Driver> driverDb = userService.findByLoginAndPas(
                driver.getLogin(), driver.getPassword()
        );
        if (driverDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("driver", driverDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}

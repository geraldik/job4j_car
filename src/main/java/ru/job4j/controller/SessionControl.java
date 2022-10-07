package ru.job4j.controller;

import org.springframework.ui.Model;
import ru.job4j.model.Driver;

import javax.servlet.http.HttpSession;

public final class SessionControl {

    private SessionControl() {
    }

    public static void getUserSession(Model model, HttpSession session) {
        Driver driver = (Driver) session.getAttribute("driver");
        if (driver == null) {
            driver = new Driver();
            driver.setName("Гость");
        }
        model.addAttribute("driver", driver);
    }
}
package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Driver;
import ru.job4j.repository.UserRepository;

import java.util.Optional;
@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<Driver> create(Driver driver) {
        return repository.create(driver);
    }

    public Optional<Driver> findByLoginAndPas(String login, String password) {
        return repository.findByLoginAndPas(login, password);
    }
}

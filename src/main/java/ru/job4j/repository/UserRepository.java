package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Driver;
import ru.job4j.utility.TransactionService;

import java.util.Optional;

@Repository
public class UserRepository implements TransactionService {

    private final SessionFactory sf;
    public static final String FIND_BY_LOGIN_AND_PAS = "from Driver a where a.login = :login "
            + "and a.password = :password";

    public UserRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<Driver> create(final Driver driver) {
        return this.tx(session -> {
            Integer id = (Integer) session.save(driver);
            if (id == null) {

                return Optional.empty();
            }
            return Optional.of(driver);
        }, sf);
    }

    public Optional<Driver> findByLoginAndPas(final String login, final String password) {
        return this.tx(session -> session.createQuery(FIND_BY_LOGIN_AND_PAS, Driver.class)
                        .setParameter("login", login)
                        .setParameter("password", password)
                        .uniqueResultOptional(),
                sf);
    }
}

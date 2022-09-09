package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Car;
import ru.job4j.utility.TransactionService;

import java.util.List;

@Repository
public class CarRepository implements TransactionService {

    private final SessionFactory sf;

    private static final String QUERY_FIND_ALL = "from Car";

    public CarRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public Car create(final Car car) {
        return this.tx(
                session -> {
                    session.save(car);
                    return car;
                }, sf
        );
    }

    public List<Car> findAll() {
        return this.tx(
                session -> session.createQuery(QUERY_FIND_ALL, Car.class)
                        .list(),
                sf
        );
    }

    public void update(final Car car) {
        this.tx(
                session -> {
                    session.update(car);
                    return session;
                }, sf
        );
    }

    public void delete(final Car car) {
        this.tx(
                session -> {
                    session.delete(car);
                    return session;
                }, sf
        );
    }
}

package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.AutoPost;
import ru.job4j.utility.TransactionService;

import java.util.List;

@Repository
public class PostRepository implements TransactionService {

    private static final String QUERY_FIND_ALL_FOR_LAST_DAY = "select a from AutoPost a "
            + "where date(a.created) = current_date";
    private static final String QUERY_FIND_ALL_WITH_PHOTO = "select a from AutoPost a "
            + "where a.photo is not null";

    private static final String QUERY_FIND_SPECIFIC_BRAND = "select a from AutoPost a join a.car car "
            + "where car.brand = :brand";
    private final SessionFactory sf;

    public PostRepository(SessionFactory sf) {
        this.sf = sf;
    }

    public List<AutoPost> findAllForLastDay() {
        return this.tx(
                session -> session.createQuery(QUERY_FIND_ALL_FOR_LAST_DAY, AutoPost.class)
                        .list(),
                sf
        );
    }

    public List<AutoPost> findAllWithPhoto() {
        return this.tx(
                session -> session.createQuery(QUERY_FIND_ALL_WITH_PHOTO, AutoPost.class)
                        .list(),
                sf
        );
    }

    public List<AutoPost> findSpecificBrand(String brand) {
        return this.tx(
                session -> session.createQuery(QUERY_FIND_SPECIFIC_BRAND, AutoPost.class)
                        .setParameter("brand", brand)
                        .list(),
                sf
        );
    }

    public void add(AutoPost post) {
        this.tx(session -> session.save(post), sf);
    }
}

package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.model.AutoPost;
import ru.job4j.model.Brand;
import ru.job4j.model.Car;
import ru.job4j.utility.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PostRepositoryTest implements TransactionService {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Test
    public void whenAddThreeAndFindByLastDayWhenGetTwo() {
        PostRepository repository = new PostRepository(sf);
        AutoPost post1 = AutoPost.of()
                .text("post 1")
                .created(LocalDateTime.now())
                .build();
        AutoPost post2 = AutoPost.of()
                .text("post 2")
                .created(LocalDateTime.now())
                .build();
        AutoPost post3 = AutoPost.of()
                .text("post 2")
                .created(LocalDateTime.now().minusDays(1))
                .build();
        repository.add(post1);
        repository.add(post2);
        repository.add(post3);
        List<AutoPost> posts = repository.findAllForLastDay();
        assertThat(posts).isEqualTo(List.of(post1, post2));
    }

    @Test
    public void whenAddThreeAndFindWithPhotoWhenGetOne() {
        PostRepository repository = new PostRepository(sf);
        AutoPost post1 = AutoPost.of()
                .text("post 1")
                .photo(new byte[]{1})
                .build();
        AutoPost post2 = AutoPost.of()
                .text("post 2")
                .build();
        AutoPost post3 = AutoPost.of()
                .text("post 2")
                .build();
        repository.add(post1);
        repository.add(post2);
        repository.add(post3);
        List<AutoPost> posts = repository.findAllWithPhoto();
        assertThat(posts).isEqualTo(List.of(post1));
    }

    @Test
    public void whenFindSpecificBrandWhenGetOne() {
        PostRepository repository = new PostRepository(sf);
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        Brand brand1 = Brand.of()
                .name("BMW")
                .build();
        Brand brand2 = Brand.of()
                .name("Lada")
                .build();
        Car car1 = Car.of()
                .brand(brand1)
                .manufactured(LocalDateTime.now())
                .model("X5")
                .build();
        Car car2 = Car.of()
                .brand(brand2)
                .manufactured(LocalDateTime.now())
                .model("Vesta")
                .build();
        session.persist(car1);
        session.persist(car2);
        ts.commit();
        session.close();
        AutoPost post1 = AutoPost.of()
                .text("post 1")
                .car(car1)
                .build();
        AutoPost post2 = AutoPost.of()
                .text("post 2")
                .car(car2)
                .build();
        repository.add(post1);
        repository.add(post2);
        List<AutoPost> posts = repository.findSpecificBrand("BMW");
        assertThat(posts).isEqualTo(List.of(post1));
    }
}
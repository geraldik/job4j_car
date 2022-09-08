package ru.job4j.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import ru.job4j.model.*;
import ru.job4j.utility.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PostRepositoryTest implements TransactionService {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Before
    public  void fillTables() {
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        Driver driver = Driver.of()
                .name("name")
                .login("login")
                .password("password")
                .build();
        Brand brand = Brand.of()
                .name("Lada")
                .build();
        CarBody carBody = CarBody.of()
                .color("black")
                .doorsNumber(4)
                .carBodyType(CarBodyType.SEDAN)
                .build();
        Engine engine = Engine.of()
                .volume(1.8)
                .horsePower(110)
                .diesel(false)
                .build();
        Gearbox gearbox = Gearbox.of()
                .automatic(true)
                .speedNumber(4)
                .build();
        Car car = Car.of()
                .brand(brand)
                .manufactureYear(2022)
                .model("Vesta")
                .engine(engine)
                .gearbox(gearbox)
                .carBody(carBody)
                .build();
        session.persist(driver);
        session.persist(car);
        ts.commit();
        session.close();
    }

    @Test
    public void whenAddThreeAndFindByLastDayWhenGetTwo() {
        PostRepository repository = new PostRepository(sf);

        AutoPost post1 = AutoPost.of()
                .text("post 1")
                .created(LocalDateTime.now())
                .driver(new Driver())
                .car(new Car())
                .build();
        AutoPost post2 = AutoPost.of()
                .text("post 2")
                .created(LocalDateTime.now())
                .driver(new Driver())
                .car(new Car())
                .build();
        AutoPost post3 = AutoPost.of()
                .text("post 2")
                .created(LocalDateTime.now().minusDays(1))
                .driver(new Driver())
                .car(new Car())
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
                .manufactureYear(2022)
                .model("X5")
                .build();
        Car car2 = Car.of()
                .brand(brand2)
                .manufactureYear(2022)
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
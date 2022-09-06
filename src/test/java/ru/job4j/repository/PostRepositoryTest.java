package ru.job4j.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.model.AutoPost;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PostRepositoryTest {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Test
    public void whenAddTwoAndFindByLastDay() {
        PostRepository repository = new PostRepository(sf);
        AutoPost post1 = AutoPost.of()
                .text("post 1")
                .created(LocalDateTime.now())
                .build();
        AutoPost post2 = AutoPost.of()
                .text("post 2")
                .created(LocalDateTime.now())
                .build();
        repository.add(post1);
        repository.add(post2);
        List<AutoPost> posts = repository.findAllForLastDay();
        assertThat(posts).isEqualTo(List.of(post1, post2));

    }

}
package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.AutoPost;
import ru.job4j.repository.PostRepository;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Collection<AutoPost> findAll() {
        return postRepository.findAll();
    }

    public void add(AutoPost post) {
        postRepository.add(post);
    }

    public List<AutoPost> findAllForLastDay()  {
        return postRepository.findAllForLastDay();
    }

    public List<AutoPost> findAllWithPhoto() {
        return postRepository.findAllWithPhoto();
    }

    public List<AutoPost> findSpecificBrand(String brand) {
        return postRepository.findSpecificBrand(brand);
    }

    public AutoPost findById(int id) {
        return postRepository.findById(id);
    }
}

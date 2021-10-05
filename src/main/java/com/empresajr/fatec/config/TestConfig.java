package com.empresajr.fatec.config;

import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.User;
import com.empresajr.fatec.entities.enums.Type;
import com.empresajr.fatec.repositories.AuthorRepository;
import com.empresajr.fatec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.time.Instant;
import java.util.Arrays;

import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null, "Marcos", "marcos@mail.com", "123456", Type.USER);
        User user2 = new User(null, "Ana", "ana@mail.com", "123456", Type.USER);

        Date date = Date.from(Instant.parse("2021-10-04T22:12:55Z"));


        Author author1 = new Author(null, "Maria", "maria@mail.com",
                "123456", date, Type.AUTHOR);

        Author author2 = new Author(null, "José", "jose@mail.com",
                "123456", date, Type.AUTHOR);

        userRepository.saveAll(Arrays.asList(user1, user2));
        authorRepository.saveAll(Arrays.asList(author1, author2));

    }
}

package com.empresajr.fatec.config;

import com.empresajr.fatec.entities.User;
import com.empresajr.fatec.entities.enums.Type;
import com.empresajr.fatec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null, "Marcos", "marcos@mail.com", "123456", Type.USER);
        User user2 = new User(null, "Ana", "ana@mail.com", "123456", Type.USER);

        userRepository.saveAll(Arrays.asList(user1, user2));

    }
}

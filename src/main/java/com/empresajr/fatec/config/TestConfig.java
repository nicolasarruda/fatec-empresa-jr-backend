package com.empresajr.fatec.config;

import com.empresajr.fatec.entities.Author;
import com.empresajr.fatec.entities.InternPost;
import com.empresajr.fatec.entities.InternTopic;
import com.empresajr.fatec.entities.Post;
import com.empresajr.fatec.entities.Topic;
import com.empresajr.fatec.repositories.AuthorRepository;
import com.empresajr.fatec.repositories.InternPostRepository;
import com.empresajr.fatec.repositories.InternTopicRepository;
import com.empresajr.fatec.repositories.PostRepository;
import com.empresajr.fatec.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private InternPostRepository internPostRepository;

    @Autowired
    private InternTopicRepository internTopicRepository;

    @Override
    public void run(String... args) throws Exception {

        Author author1 = new Author(null, "Maria", "maria@mail.com");

        Author author2 = new Author(null, "José", "jose@mail.com");

        authorRepository.saveAll(Arrays.asList(author1, author2));

        Topic topic1 = new Topic(null, "ADS");
        Topic topic2 = new Topic(null, "Sistemas Embarcados");
        Topic topic3 = new Topic(null, "Eventos");

        topicRepository.saveAll(Arrays.asList(topic1, topic2, topic3));

        Post post1 = new Post(null,"Inter Fatecs começam essa semana", topic1,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "", author1);

        Post post2 = new Post(null,"Hackathon", topic2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "", author2);

        postRepository.saveAll(Arrays.asList(post1,post2));

        topic1.getPosts().add(post1);
        topic2.getPosts().add(post2);

        topicRepository.saveAll(Arrays.asList(topic1, topic2, topic3));

        author1.getPosts().add(post1);
        author2.getPosts().add(post2);

        authorRepository.saveAll(Arrays.asList(author1, author2));

        InternTopic internTopic1 = new InternTopic(null, "Back-end java");
        InternTopic internTopic2 = new InternTopic(null, "Front-end react");
        InternTopic internTopic3 = new InternTopic(null, "DBA MongoDB");

        internTopicRepository.saveAll(Arrays.asList(internTopic1, internTopic2, internTopic3));

        InternPost internPost1 = new InternPost(null,"Estágio", internTopic1,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "", author2);

        InternPost internPost2 = new InternPost(null,"CLT: desenvolvedor php", internTopic2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "", author2);

        internPostRepository.saveAll(Arrays.asList(internPost1, internPost2));

        internTopic1.getInternPosts().add(internPost1);
        internTopic2.getInternPosts().add(internPost2);

        internTopicRepository.saveAll(Arrays.asList(internTopic1, internTopic2, internTopic3));

        author2.getInternPosts().add(internPost1);
        author2.getInternPosts().add(internPost2);

        authorRepository.saveAll(Arrays.asList(author1, author2));

    }
}

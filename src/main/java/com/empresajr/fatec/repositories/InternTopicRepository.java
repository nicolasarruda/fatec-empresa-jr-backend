package com.empresajr.fatec.repositories;

import com.empresajr.fatec.entities.InternTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternTopicRepository extends JpaRepository<InternTopic, Long> {

    boolean existsByName(String name);
}


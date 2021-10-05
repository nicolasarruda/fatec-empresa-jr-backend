package com.empresajr.fatec.repositories;

import com.empresajr.fatec.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}

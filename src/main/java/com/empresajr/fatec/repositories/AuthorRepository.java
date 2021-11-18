package com.empresajr.fatec.repositories;

import com.empresajr.fatec.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Long> {

    /*
    @Query("SELECT name, email FROM tb_author WHERE name LIKE :name OR email LIKE :email")
    Optional<Author> findByEmailOrName(String name, String email);
     */
}

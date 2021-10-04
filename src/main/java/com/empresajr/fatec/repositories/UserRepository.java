package com.empresajr.fatec.repositories;

import com.empresajr.fatec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

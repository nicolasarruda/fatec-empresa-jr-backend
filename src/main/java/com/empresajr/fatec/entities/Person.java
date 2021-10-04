package com.empresajr.fatec.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass()
public abstract class Person {

    protected String name;
    protected String email;
    protected String password;

}

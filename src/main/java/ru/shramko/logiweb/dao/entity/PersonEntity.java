package ru.shramko.logiweb.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
@Table(name = "person")
public class PersonEntity extends AbstractEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
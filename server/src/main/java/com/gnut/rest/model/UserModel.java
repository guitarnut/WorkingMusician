package com.gnut.rest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by guitarnut on 10/21/16.
 */

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public UserModel() {
    }

    public UserModel(long id) {
        this.id = id;
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public long getId() {
        return this.id;
    }

}
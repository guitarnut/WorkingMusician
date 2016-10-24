package com.gnut.rest.model;

import javax.persistence.*;

/**
 * Created by guitarnut on 10/23/16.
 */
@Entity
@Table(name = "genres")
public class GenreModel implements ProfileData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;
    private String name;
    private Boolean selected;

    public GenreModel() {
    }

    public GenreModel(long id) {
        this.id = id;
    }

    public GenreModel(String name, Boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public long getId() {
        return this.id;
    }

}
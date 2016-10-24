package com.gnut.rest.model;

import javax.persistence.*;

/**
 * Created by guitarnut on 10/24/16.
 */
@Entity
@Table(name = "instruments")
public class InstrumentModel implements ProfileData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;
    private String name;
    private Boolean selected;

    public InstrumentModel() {
    }

    public InstrumentModel(long id) {
        this.id = id;
    }

    public InstrumentModel(String name, Boolean selected) {
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
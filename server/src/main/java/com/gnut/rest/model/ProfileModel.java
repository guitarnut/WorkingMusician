package com.gnut.rest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by guitarnut on 10/21/16.
 */

@Entity
@Table(name = "profiles")
public class ProfileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private long userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String profession;

    @NotNull
    private String availability;

    @NotNull
    private String travel;

    public ProfileModel() {
    }

    public ProfileModel(long id) {
        this.id = id;
    }

    public ProfileModel(long userId,
                        String firstName,
                        String lastName,
                        String city,
                        String state,
                        String profession,
                        String availability,
                        String travel
    ) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return this.id;
    }

    public long getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getAvailability() {
        return this.availability;
    }

    public String getTravel() {
        return this.travel;
    }

}
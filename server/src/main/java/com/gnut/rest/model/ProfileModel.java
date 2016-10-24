package com.gnut.rest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by guitarnut on 10/21/16.
 */

@Entity
@Table(name = "profiles")
public class ProfileModel implements ProfileData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private long userId;

    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String profession;
    private String availability;
    private String travel;
    private String profilePicture;
    private int profileViews;

    public ProfileModel() {
    }

    public ProfileModel(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public void setProfileViews(int profileViews) { this.profileViews = profileViews; }

    public int getProfileViews() { return this.profileViews; }

    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    public String getProfilePicture() { return this.profilePicture; }

}
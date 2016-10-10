package com.gnut.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by guitarnut on 10/3/16.
 */

@Document(collection = "profiles")
public class Profile {
    @Id
    private String id;
    private String userId;
    private String firstName;
    private String lastName;

    public Profile() {
    }

    public Profile(
            String userId,
            String firstName,
            String lastName
    ) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(String id) {

    }

}
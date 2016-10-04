package com.gnut.rest.repository;

import com.gnut.rest.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guitarnut on 9/22/16.
 */
public interface UserMongoRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
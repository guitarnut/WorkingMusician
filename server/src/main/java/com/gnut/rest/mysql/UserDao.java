package com.gnut.rest.mysql;

import com.gnut.rest.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by guitarnut on 10/21/16.
 */
public interface UserDao extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(@Param("username") String username);
}
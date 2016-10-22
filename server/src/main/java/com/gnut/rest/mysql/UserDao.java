package com.gnut.rest.mysql;

import com.gnut.rest.model.UserModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by guitarnut on 10/21/16.
 */
public interface UserDao extends CrudRepository<UserModel, Long> {
}

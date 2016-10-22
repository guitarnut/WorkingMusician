package com.gnut.rest.mysql;

import com.gnut.rest.model.ProfileModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by guitarnut on 10/21/16.
 */
public interface ProfileDao extends CrudRepository<ProfileModel, Long> {
    ProfileModel findByUserId(@Param("userId") long userId);
}

package com.gnut.rest.mysql;

import com.gnut.rest.model.VocalModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by guitarnut on 10/24/16.
 */
public interface VocalDao extends CrudRepository<VocalModel, Long> {
    List<VocalModel> findByUserId(@Param("userId") long userId);
}

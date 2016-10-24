package com.gnut.rest.mysql;

import com.gnut.rest.model.GenreModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by guitarnut on 10/23/16.
 */
public interface GenreDao extends CrudRepository<GenreModel, Long> {
    List<GenreModel> findByUserId(@Param("userId") long userId);
}

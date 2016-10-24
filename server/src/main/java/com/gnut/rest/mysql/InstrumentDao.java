package com.gnut.rest.mysql;

import com.gnut.rest.model.InstrumentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by guitarnut on 10/24/16.
 */
public interface InstrumentDao extends CrudRepository<InstrumentModel, Long> {
    List<InstrumentModel> findByUserId(@Param("userId") long userId);
}

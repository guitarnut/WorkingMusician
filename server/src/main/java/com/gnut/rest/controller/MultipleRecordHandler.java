package com.gnut.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guitarnut on 10/24/16.
 */
@Component
public class MultipleRecordHandler<T> {

    private static final ObjectMapper mapper = new ObjectMapper();

    public void insertMultipleProfileDataRecords(
            Class<T> model,
            Map<String, Object> data,
            String dataKey,
            CrudRepository<T, Long> dao
    ) {
        try {
            ArrayList<LinkedHashMap<String, String>> genres = (ArrayList) data.get(dataKey);

            for(LinkedHashMap genre : genres) {
                T record = mapper.convertValue(genre, model);
                dao.save(record);
            }
        } catch (Exception e) {
            String ex = e.toString();
        }
    }
}

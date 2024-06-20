package com.example.sql_dao_layer_hibernate.service;

import com.example.sql_dao_layer_hibernate.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final DatabaseRepository databaseRepository;

    @Autowired
    public Service(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public List<String> getProductName(String name) {
        if (isEmpty(name)) {
            return null;
        }
        return databaseRepository.getProductName(name);
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

}
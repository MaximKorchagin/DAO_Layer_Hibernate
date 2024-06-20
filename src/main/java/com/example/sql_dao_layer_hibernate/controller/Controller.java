package com.example.sql_dao_layer_hibernate.controller;


import com.example.sql_dao_layer_hibernate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/products/fetch-product")
    public List<String> getRequest(@RequestParam String name) {
        System.out.println(name);
        return service.getProductName(name);
    }
}
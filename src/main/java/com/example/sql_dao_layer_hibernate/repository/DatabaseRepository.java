package com.example.sql_dao_layer_hibernate.repository;


import com.example.sql_dao_layer_hibernate.model.Customer;
import com.example.sql_dao_layer_hibernate.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DatabaseRepository implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public DatabaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<String> getProductName(String name) {
        Query query = entityManager.createQuery("select o.productName from Orders o where o.customer.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        fillTableCustomers();
    }

    public void fillTableCustomers() {
        var names = List.of("Ivan", "Petr", "Olga", "Elena", "Oleg", "Olga");
        var surnames = List.of("Ivanov", "Petrov", "Frolova", "Sidorova", "Semenov", "Filatova");
        var ages = List.of(18, 20, 38, 22, 52, 42);
        var phoneNumbers = List.of("8(921)123-00-11", "8(921)123-00-22", "8(921)123-00-33",
                "8(921)123-00-44", "8(921)123-00-55", "8(921)123-00-66");

        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            var customer = Customer.builder()
                    .name(names.get(i))
                    .surname(surnames.get(i))
                    .age(ages.get(i))
                    .phone_number(phoneNumbers.get(i))
                    .build();

            entityManager.persist(customer);
            customerList.add(customer);
        }
        fillTableOrders(customerList);
    }

    public void fillTableOrders(List<Customer> customer) {
        var customerId = List.of(1, 5, 2, 2, 4, 6);
        var productName = List.of("telephone", "computer", "telephone", "televisor", "telephone", "computer");
        var amount = List.of(15000, 50000, 25000, 65000, 22000, 80000);

        for (int i = 0; i < customerId.size(); i++) {
            var oder = Orders.builder()
                    .customer(customer.get(customerId.get(i) - 1))
                    .productName(productName.get(i))
                    .amount(amount.get(i))
                    .date(new Date())
                    .build();

            entityManager.persist(oder);
        }
    }
}
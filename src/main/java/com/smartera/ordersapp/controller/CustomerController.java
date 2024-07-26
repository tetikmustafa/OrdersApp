package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.model.Customer;
import com.smartera.ordersapp.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController{

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @PostMapping("customers")
    public Customer save(@RequestBody Customer customer) {
        customerServiceImpl.save(customer);
        return customerServiceImpl.findById(customer.getCustomerId());
    }


    @GetMapping("customers/{customerId}")
    public Customer findById(@PathVariable int customerId) {
        return customerServiceImpl.findById(customerId);
    }

    @GetMapping("customers")
    public List<Customer> findAll() {
        return customerServiceImpl.findAll();
    }

    @GetMapping("customers/keyword/{keyword}")
    public List<Customer> findByKeyword(@PathVariable String keyword) {
        return customerServiceImpl.findByKeyword(keyword);
    }


    @PutMapping("customers/{customerId}")
    public Customer update(@RequestBody Customer customer, @PathVariable int customerId) {
        customer.setCustomerId(customerId);
        customerServiceImpl.update(customer);
        return customerServiceImpl.findById(customer.getCustomerId());
    }


    @PutMapping("customers/{customerId}/authorize")
    public String authorize(@PathVariable int customerId) {
        customerServiceImpl.authorize(customerId);
        return "Customer "+customerId+" has been authorized.";
    }

    @DeleteMapping("customers/{customerId}")
    public String deleteById(@PathVariable int customerId) {
        customerServiceImpl.deleteById(customerId);
        return "Customer with id " + customerId + " has been deleted";
    }

    @DeleteMapping("customers")
    public String deleteAll() {
        customerServiceImpl.deleteAll();
        return "All customers have been deleted";
    }
}

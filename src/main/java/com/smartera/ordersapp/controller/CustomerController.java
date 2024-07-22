package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.model.Customer;
import com.smartera.ordersapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController implements IController<Customer>{

    @Autowired
    CustomerService customerService;

    @PostMapping("customers")
    public Customer save(@RequestBody Customer customer) {
        customerService.save(customer);
        return customerService.findById(customer.getCustomerId());
    }


    @GetMapping("customers/{customerId}")
    public Customer findById(@PathVariable int customerId) {
        return customerService.findById(customerId);
    }

    @GetMapping("customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("customers/keyword/{keyword}")
    public List<Customer> findByKeyword(@PathVariable String keyword) {
        return customerService.findByKeyword(keyword);
    }


    @PutMapping("customers/{customerId}")
    public Customer update(@RequestBody Customer customer, @PathVariable int customerId) {
        customer.setCustomerId(customerId);
        customerService.update(customer);
        return customerService.findById(customer.getCustomerId());
    }


    @PutMapping("customers/{customerId}/authorize")
    public String authorize(@PathVariable int customerId) {
        customerService.authorize(customerId);
        return "Customer "+customerId+" has been authorized.";
    }

    @DeleteMapping("customers/{customerId}")
    public String deleteById(@PathVariable int customerId) {
        customerService.deleteById(customerId);
        return "Customer with id " + customerId + " has been deleted";
    }

    @DeleteMapping("customers")
    public String deleteAll() {
        customerService.deleteAll();
        return "All customers have been deleted";
    }
}

package com.smartera.ordersapp.controller;

import com.smartera.ordersapp.dto.customer.CustomerCreateDto;
import com.smartera.ordersapp.dto.customer.CustomerDto;
import com.smartera.ordersapp.dto.customer.CustomerIdDto;
import com.smartera.ordersapp.entity.Customer;
import com.smartera.ordersapp.mapper.CustomerMapper;
import com.smartera.ordersapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("customers")
public class CustomerController{

    @Autowired
    CustomerService customerService;

    @PostMapping()
    public CustomerIdDto save(@RequestBody CustomerCreateDto customerCreateDto) {
        Customer customer = CustomerMapper.toCustomer(customerCreateDto);
        customerService.save(customer);
        return CustomerMapper.toCustomerIdDto(customerService.findById(customer.getCustomerId()));
    }


    @GetMapping("/{customerId}")
    public CustomerDto findById(@PathVariable UUID customerId) {
        return CustomerMapper.toCustomerDto(customerService.findById(customerId));
    }

    @GetMapping()
    public List<CustomerDto> findAll() {
        return customerService.findAll()
                .stream().map(CustomerMapper::toCustomerDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<CustomerDto> findByKeyword(@PathVariable String keyword) {
        return customerService.findByKeyword(keyword)
                .stream().map(CustomerMapper::toCustomerDto).toList();
    }


    @PutMapping("/{customerId}")
    public CustomerIdDto update(@RequestBody CustomerCreateDto customerCreateDto, @PathVariable UUID customerId) {
        Customer customer = CustomerMapper.toCustomer(customerCreateDto);
        customer.setCustomerId(customerId);
        customerService.update(customer);
        return CustomerMapper.toCustomerIdDto(customerService.findById(customerId));
    }


    @PutMapping("/{customerId}/authorize")
    public String authorize(@PathVariable UUID customerId) {
        customerService.authorize(customerId);
        return "Customer "+customerId+" has been authorized.";
    }

    @DeleteMapping("/{customerId}")
    public String deleteById(@PathVariable UUID customerId) {
        customerService.deleteById(customerId);
        return "Customer with id " + customerId + " has been deleted";
    }

    @DeleteMapping()
    public String deleteAll() {
        customerService.deleteAll();
        return "All customers have been deleted";
    }
}

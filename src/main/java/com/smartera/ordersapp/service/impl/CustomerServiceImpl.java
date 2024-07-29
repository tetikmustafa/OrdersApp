package com.smartera.ordersapp.service.impl;

import com.smartera.ordersapp.exception.CustomerNotAuthorizedException;
import com.smartera.ordersapp.exception.CustomerNotFoundException;
import com.smartera.ordersapp.entity.Customer;
import com.smartera.ordersapp.entity.Order;
import com.smartera.ordersapp.repository.CustomerRepository;
import com.smartera.ordersapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findById(@PathVariable UUID customerId) {
        return  customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findByKeyword(String keyword) {
        return customerRepository.findByCustomerNameContainingOrCustomerDescriptionContaining(keyword, keyword);
    }

    public void update(Customer customer) {
        Optional<Customer> o = customerRepository.findById(customer.getCustomerId());
        if (o.isEmpty()) {
            throw new CustomerNotFoundException(customer.getCustomerId());
        }
        customerRepository.save(customer);
    }

    public void deleteById(@PathVariable UUID customerId) {
        Optional<Customer> o = customerRepository.findById(customerId);
        if (o.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }
        customerRepository.deleteById(customerId);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public void authorize(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.setCustomerAuthorization(true);
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void saveOrder(UUID customerId, Order order) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().add(order.getOrderId());
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void updateOrder(UUID customerId, Order order) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().remove((Object) order.getOrderId());
            c.getCustomerOrdersIds().add(order.getOrderId());
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void deleteOrder(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().clear();
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }


    }

    public void deleteOrder(UUID customerId, UUID orderId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().remove((Object) orderId);
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void checkAuthorization(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            if(!c.isCustomerAuthorization()){
                throw new CustomerNotAuthorizedException(customerId);
            }
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }
}

package com.smartera.ordersapp.service;

import com.smartera.ordersapp.exception.CustomerNotAuthorizedException;
import com.smartera.ordersapp.exception.CustomerNotFoundException;
import com.smartera.ordersapp.exception.OrderNotFoundException;
import com.smartera.ordersapp.model.Customer;
import com.smartera.ordersapp.model.Order;
import com.smartera.ordersapp.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements IService<Customer>{

    @Autowired
    CustomerRepo customerRepo;

    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    public Customer findById(@PathVariable int customerId) {
        return  customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public List<Customer> findByKeyword(String keyword) {
        return customerRepo.findByCustomerNameContainingOrCustomerDescriptionContaining(keyword, keyword);
    }

    public void update(Customer customer) {
        Optional<Customer> o = customerRepo.findById(customer.getCustomerId());
        if (o.isEmpty()) {
            throw new CustomerNotFoundException(customer.getCustomerId());
        }
        customerRepo.save(customer);
    }

    public void deleteById(@PathVariable int customerId) {
        Optional<Customer> o = customerRepo.findById(customerId);
        if (o.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }
        customerRepo.deleteById(customerId);
    }

    public void deleteAll() {
        customerRepo.deleteAll();
    }

    public void authorize(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.setCustomerAuthorization(true);
            customerRepo.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void saveOrder(int customerId, Order order) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().add(order.getOrderId());
            customerRepo.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void updateOrder(int customerId, Order order) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().remove((Object) order.getOrderId());
            c.getCustomerOrdersIds().add(order.getOrderId());
            customerRepo.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void deleteOrder(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().clear();
            customerRepo.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }


    }

    public void deleteOrder(int customerId, int orderId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().remove((Object) orderId);
            customerRepo.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void checkAuthorization(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
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

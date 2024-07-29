package com.smartera.ordersapp.mapper;

import com.smartera.ordersapp.dto.customer.CustomerCreateDto;
import com.smartera.ordersapp.dto.customer.CustomerDto;
import com.smartera.ordersapp.dto.customer.CustomerIdDto;
import com.smartera.ordersapp.entity.Customer;

import java.util.UUID;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setCustomerDescription(customer.getCustomerDescription());
        customerDto.setCustomerAuthorization(customer.isCustomerAuthorization());
        customerDto.setCustomerOrdersIds(customer.getCustomerOrdersIds());
        return customerDto;
    }

    public static Customer toCustomer(CustomerCreateDto customerCreateDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerCreateDto.getCustomerName());
        customer.setCustomerDescription(customerCreateDto.getCustomerDescription());
        customer.setCustomerAuthorization(false);
        return customer;
    }

    public static CustomerIdDto toCustomerIdDto(Customer customer) {
        CustomerIdDto customerIdDto = new CustomerIdDto();
        customerIdDto.setCustomerId(customer.getCustomerId());
        return customerIdDto;
    }
}

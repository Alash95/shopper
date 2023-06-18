package com.alash.shopper.service;

import com.alash.shopper.dto.request.CustomerRequest;
import com.alash.shopper.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse registerCustomer(CustomerRequest customerRequest);
    CustomerResponse findCustomerById(Integer id);
    CustomerResponse findCustomerByUsername(String username);
    CustomerResponse updateCustomerDetailsById(Integer id, CustomerRequest customerRequest);
    List<CustomerRequest> fetchAllCustomers();
    String deleteUserById(Integer id);
}

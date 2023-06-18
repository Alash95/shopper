package com.alash.shopper.service.impl;

import com.alash.shopper.dto.request.CustomerRequest;
import com.alash.shopper.dto.request.Data;
import com.alash.shopper.dto.response.CustomerResponse;
import com.alash.shopper.model.Customer;
import com.alash.shopper.repository.CustomerRepository;
import com.alash.shopper.service.CustomerService;
import com.alash.shopper.utils.ResponseUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper){
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerResponse registerCustomer(CustomerRequest customerRequest) {

        boolean isEmailExist = customerRepository.existsByEmail(customerRequest.getEmail());
        if (isEmailExist){
            return CustomerResponse.builder()
                    .responseCode(ResponseUtils.USER_EXISTS_CODE)
                    .responseMessage(ResponseUtils.USER_EXISTS_MESSAGE)
                    .data(null)
                    .build();
        }

        Customer newCustomer = modelMapper.map(customerRequest, Customer.class);
        customerRepository.save(newCustomer);
        return CustomerResponse.builder()
                .responseCode(ResponseUtils.SUCCESS)
                .responseMessage(ResponseUtils.USER_REGISTERED_SUCCESS)
                .data(modelMapper.map(newCustomer, Data.class))
                .build();

    }

    @Override
    public CustomerResponse findCustomerById(Integer customerId) {

        if (!customerRepository.existsById(customerId)) {
            return CustomerResponse.builder()
                    .responseCode(ResponseUtils.USER_NOT_FOUND_CODE)
                    .responseMessage(ResponseUtils.USER_NOT_FOUND)
                    .data(null)
                    .build();
        }
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return CustomerResponse.builder()
                .responseCode(ResponseUtils.SUCCESS)
                .responseMessage(ResponseUtils.SUCCESS_MESSAGE)
                .data(modelMapper.map(customer, Data.class))
                .build();

    }

    @Override
    public CustomerResponse findCustomerByUsername(String email) {
        if (!customerRepository.existsByEmail(email)) {
            return CustomerResponse.builder()
                    .responseCode(ResponseUtils.USER_NOT_FOUND_CODE)
                    .responseMessage(ResponseUtils.USER_NOT_FOUND)
                    .data(null)
                    .build();
        }
        Customer findByEmail = customerRepository.findByEmail(email);

        return CustomerResponse.builder()
                .responseCode(ResponseUtils.SUCCESS)
                .responseMessage(ResponseUtils.SUCCESS_MESSAGE)
                .data(modelMapper.map(findByEmail, Data.class))
                .build();
    }

    @Override
    public CustomerResponse updateCustomerDetailsById(Integer id, CustomerRequest customerRequest) {

        if (!customerRepository.existsById(id)) {
            return CustomerResponse.builder()
                    .responseCode(ResponseUtils.USER_NOT_FOUND_CODE)
                    .responseMessage(ResponseUtils.USER_NOT_FOUND)
                    .data(null)
                    .build();
        }

        Customer fetchedCustomer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(customerRequest, fetchedCustomer);
        customerRepository.save(fetchedCustomer);

        return CustomerResponse.builder()
                .responseCode(ResponseUtils.SUCCESS)
                .responseMessage(ResponseUtils.UPDATE_MESSAGE)
                .data(modelMapper.map(fetchedCustomer, Data.class))
                .build();

    }

    @Override
    public List<CustomerRequest> fetchAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerRequest> customerRequests = new ArrayList<>();
        for (Customer element: customerList) {
            CustomerRequest customerRequest = modelMapper.map(element, CustomerRequest.class);
            customerRequests.add(customerRequest);
        }
        return customerRequests;
    }

    @Override
    public String deleteUserById(Integer id) {
        if (!customerRepository.existsById(id)) {
            return "Delete task unsuccessful, Customer to be deleted doesn't exist.";
        }
        customerRepository.deleteById(id);
        return "Delete task successful";
    }


}

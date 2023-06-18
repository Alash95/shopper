package com.alash.shopper.controller;

import com.alash.shopper.dto.request.CustomerRequest;
import com.alash.shopper.dto.response.CustomerResponse;
import com.alash.shopper.service.CustomerService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@OpenAPIDefinition(
        info = @Info(
                title = "Spring boot ecommerce application",
                description = "Spring boot Banking Application REST APIs Implementation",
                version = "v1.0",
                contact = @Contact(
                        name = "Oyin",
                        email = "oyinlolaalasho@gmail.com",
                        url = "https://github.com/Alash95"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://github.com/Alash95"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring boot ecommerce implementation",
                url = "https://github.com/Alash95"
        )
)
@Tag(
        name = "Customer account service REST APIs/Endpoint",
        description = "Endpoints for manipulating customer Account"
)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.registerCustomer(customerRequest));
    }

    @GetMapping("fetchId/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable(value = "customerId") Integer customerId) {
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @GetMapping("/findEmail/{email}")
    public ResponseEntity<CustomerResponse> findCustomerByEmail(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok(customerService.findCustomerByUsername(email));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponse> updateCustomerById(@PathVariable(value = "id") Integer id, @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.updateCustomerDetailsById(id, customerRequest));
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<CustomerRequest>> fetchAllCustomers() {
        return ResponseEntity.ok(customerService.fetchAllCustomers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(customerService.deleteUserById(id));
    }


}

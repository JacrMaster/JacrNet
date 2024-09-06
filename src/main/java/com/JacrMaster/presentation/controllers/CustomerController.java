package com.JacrMaster.presentation.controllers;

import com.JacrMaster.presentation.dto.CustomerDTO;
import com.JacrMaster.services.customer.ICustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class CustomerController {
    @Autowired
    private ICustomerServiceImpl customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        return new ResponseEntity<>(this.customerService.addCustomer(customerDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        return new ResponseEntity<>(this.customerService.deleteCustomer(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> findAll(){
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), HttpStatus.OK);
    }
}

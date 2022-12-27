package com.crud.lombok.crud.controller;

import com.crud.lombok.crud.model.Customer;
import com.crud.lombok.crud.service.Customer.CustomerService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@NoArgsConstructor
public class CustomerController {

    //https://www.baeldung.com/spring-boot-react-crud

    @Autowired
    private CustomerService customerService;

    // ================================== Getting Customers Data =================================================

    //Getting all the customers
    @GetMapping("/users")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    //Getting one customer by Id
    @GetMapping("/id/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    //Getting one customer by First and Last Name
    @GetMapping("/name/{firstName}/{lastName}")
    public Optional<Customer> getCustomerByName(@PathVariable String firstName, @PathVariable String lastName) {
        return customerService.getCustomerByFirstAndLastName(firstName, lastName);
    }

    //Getting one customer by email
    @GetMapping("/email/{email}")
    public Optional<Customer> getCustomerByMail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    //Getting one customer by the phone number
    @GetMapping("/phone/{phoneNumber}")
    public Optional<Customer> getCustomerByPhoneNumber(@PathVariable String phoneNumber){
        return customerService.getCustomerByPhoneNumber(phoneNumber);
    }

    //Creating new Customers
    @PostMapping("/users/add")
    public String add(@RequestBody Customer customer) {
        boolean newCustomer = customerService.save(customer);
        if(newCustomer){
            return "A new customer has been added";
        }
        return "The customer already exists";
    }

    //Updating an existing customer
    @PutMapping("/users/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable Integer id) {
        try{
            Customer existingCustomer = customerService.getCustomerById(id);
            customerService.save(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting an existing customer
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Integer id) {
        try{
            Customer existingCustomer = customerService.getCustomerById(id);
            customerService.delete(existingCustomer, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

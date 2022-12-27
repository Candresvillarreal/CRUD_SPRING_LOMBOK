package com.crud.lombok.crud.service.Customer;

import com.crud.lombok.crud.model.Customer;
import com.crud.lombok.crud.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    //Get All Customers

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //Get Customer by Id
    public Customer getCustomerById(Integer id){
        return customerRepository.findById(id).get();
    }

    //Get Customer using firstName and LastName
    public Optional<Customer> getCustomerByFirstAndLastName(String firstName, String lastName){
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    //Get Customer by email
    public Optional<Customer> getCustomerByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    //Get Customer by phoneNumber
    public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber){
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    //Save a new Customer checking if the customer don´t exist in the database

    public boolean save(Customer customer){
        Optional<Customer> newCustomer = customerRepository.findByFirstNameAndLastName(customer.getFirstName(), customer.getLastName());
        if (newCustomer.isPresent()) {
            return  false;
        }
        customerRepository.save(customer);
        return true;
    }

    //Delete an existing Customer
    public boolean delete(Customer customer, Integer id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isPresent()) {
            customerRepository.delete(customer);
            return true;
        }
        return false;

    }

}

package com.crud.lombok.crud.repository;

import com.crud.lombok.crud.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phoneNumber);



}

package com.sked.repos;

import com.sked.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {
}

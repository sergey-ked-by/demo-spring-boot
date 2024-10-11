package com.sked;

import com.sked.models.Customer;
import com.sked.repos.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    private CustomerRepository customerRepository;
    private Customer customer = new Customer();

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) { }

    @PostMapping()
    public void createCustomer(@RequestBody NewCustomerRequest request) {
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestBody NewCustomerRequest request) {
        Optional<Customer> currentCustomer = customerRepository.findById(id);
        if (!currentCustomer.isPresent()) {
            throw new RuntimeException("asd");
        }

//        currentCustomer.get().setId(id);
        currentCustomer.get().setName(request.name);
        currentCustomer.get().setEmail(request.email);
        currentCustomer.get().setAge(request.age);
        customerRepository.save(currentCustomer.get());
    }
}

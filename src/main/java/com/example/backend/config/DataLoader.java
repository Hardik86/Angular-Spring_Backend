package com.example.backend.config;

import com.example.backend.dao.CustomerRepository;
import com.example.backend.dao.DivisionRepository;
import com.example.backend.entities.Customer;
import com.example.backend.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public DataLoader(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() > 1) {
            return;
        }

        Division california = divisionRepository.findById(4L).orElseThrow();
        Division texas = divisionRepository.findById(42L).orElseThrow();
        Division florida = divisionRepository.findById(9L).orElseThrow();
        Division newYork = divisionRepository.findById(31L).orElseThrow();
        Division illinois = divisionRepository.findById(12L).orElseThrow();

        Customer customer1 = new Customer();
        customer1.setFirstName("Priya");
        customer1.setLastName("Patel");
        customer1.setAddress("456 Ocean Ave");
        customer1.setPostal_code("90210");
        customer1.setPhone("(310)555-0101");
        customer1.setDivision(california);

        Customer customer2 = new Customer();
        customer2.setFirstName("Wei");
        customer2.setLastName("Chen");
        customer2.setAddress("789 Ranch Road");
        customer2.setPostal_code("75001");
        customer2.setPhone("(214)555-0202");
        customer2.setDivision(texas);

        Customer customer3 = new Customer();
        customer3.setFirstName("Anika");
        customer3.setLastName("Sharma");
        customer3.setAddress("321 Beach Blvd");
        customer3.setPostal_code("33139");
        customer3.setPhone("(305)555-0303");
        customer3.setDivision(florida);

        Customer customer4 = new Customer();
        customer4.setFirstName("Kenji");
        customer4.setLastName("Tanaka");
        customer4.setAddress("654 Broadway");
        customer4.setPostal_code("10012");
        customer4.setPhone("(212)555-0404");
        customer4.setDivision(newYork);

        Customer customer5 = new Customer();
        customer5.setFirstName("Mei");
        customer5.setLastName("Wong");
        customer5.setAddress("987 Lake Shore Dr");
        customer5.setPostal_code("60611");
        customer5.setPhone("(312)555-0505");
        customer5.setDivision(illinois);

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);
    }
}
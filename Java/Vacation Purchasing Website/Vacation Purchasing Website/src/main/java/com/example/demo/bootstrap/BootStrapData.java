package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository){
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (customerRepository.count() < 6) {
            Customer Clark = new Customer();
            Clark.setFirstName("Clark");
            Clark.setLastName("Kent");
            Clark.setAddress("152 Kryptonite Lane");
            Clark.setPostal_code("90210");
            Clark.setPhone("1234567890");

            Customer Bruce = new Customer();
            Bruce.setFirstName("Bruce");
            Bruce.setLastName("Wayne");
            Bruce.setAddress("609 Gotham Street");
            Bruce.setPostal_code("08302");
            Bruce.setPhone("0987654321");

            Customer Scuba = new Customer();
            Scuba.setFirstName("Scuba");
            Scuba.setLastName("Steve");
            Scuba.setAddress("101 Underwater Ave");
            Scuba.setPostal_code("45632");
            Scuba.setPhone("1237893456");

            Customer Peter = new Customer();
            Peter.setFirstName("Peter");
            Peter.setLastName("Parker");
            Peter.setAddress("413 York Blvd");
            Peter.setPostal_code("08332");
            Peter.setPhone("9872340923");

            Customer James = new Customer();
            James.setFirstName("James");
            James.setLastName("Howlett");
            James.setAddress("509 Fist Blade Road");
            James.setPostal_code("08069");
            James.setPhone("7864351275");

            customerRepository.save(Clark);
            customerRepository.save(Bruce);
            customerRepository.save(Scuba);
            customerRepository.save(Peter);
            customerRepository.save(James);
        }
    }
}

package it.cgmconsulting.cerullo.service;

import it.cgmconsulting.cerullo.entity.Customer;
import it.cgmconsulting.cerullo.payload.response.FilmRentResponse;
import it.cgmconsulting.cerullo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;



    public Optional<Customer> findById(long id){
        return customerRepository.findById(id);

    }

    public List<FilmRentResponse> getFilmRentByCustomer(long customerId){
        return customerRepository.getFilmRentByCustomer(customerId);
    }
}

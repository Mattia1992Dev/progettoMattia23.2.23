package it.cgmconsulting.cerullo.service;

import it.cgmconsulting.cerullo.entity.Rental;
import it.cgmconsulting.cerullo.entity.RentalId;
import it.cgmconsulting.cerullo.payload.response.CustomerStoreResponse;
import it.cgmconsulting.cerullo.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.cerullo.payload.response.RentalResponse;
import it.cgmconsulting.cerullo.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    RentalRepository rentalRepository;

    public void save(Rental r){
        rentalRepository.save(r);
    }

    public Optional<Rental> findById(RentalId rentalId) {
        return rentalRepository.findById(rentalId);
    }

    public List<CustomerStoreResponse> getCountCostumersFromStore(String storeName){
        return rentalRepository.getCountCostumersFromStore(storeName);
    }

    public List<RentalResponse> getRentalCustomer(Long customerId, Long inventoryId){
        return rentalRepository.getRentalCustomer(customerId,inventoryId);
    }

    public List<FilmMaxRentResponse> getMaxRentedFilm(){
        return rentalRepository.getMaxRentedFilm();
    }

}

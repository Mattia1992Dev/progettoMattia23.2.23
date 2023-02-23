package it.cgmconsulting.cerullo.service;

import it.cgmconsulting.cerullo.entity.Genre;
import it.cgmconsulting.cerullo.entity.Store;
import it.cgmconsulting.cerullo.payload.response.CustomerStoreResponse;
import it.cgmconsulting.cerullo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public void save(Store s){
        storeRepository.save(s);
    }

    public Optional<Store> findById(long id) {
        return storeRepository.findById(id);
    }

    public Optional<Store> findByStoreName(String storeName){
        return storeRepository.findByStoreName(storeName);
    }

    public long getCountRentalsDateRange(long storeId, String dateStart, String dateEnd){
        return storeRepository.getCountRentalsDateRange(storeId, dateStart, dateEnd);
    }
}

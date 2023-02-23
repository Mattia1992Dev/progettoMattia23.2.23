package it.cgmconsulting.cerullo.service;

import it.cgmconsulting.cerullo.entity.Inventory;
import it.cgmconsulting.cerullo.payload.response.FilmStoreInventoryResponse;
import it.cgmconsulting.cerullo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public void save(Inventory i){
        inventoryRepository.save(i);
    }

    public boolean existsById(long id){
        return inventoryRepository.existsById(id);
    }

    public Optional<FilmStoreInventoryResponse> getInventoryDetails(long inventoryId){
        return inventoryRepository.getInventoryDetails(inventoryId);
    }
}

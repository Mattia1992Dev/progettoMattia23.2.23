package it.cgmconsulting.cerullo.controller;

import it.cgmconsulting.cerullo.entity.Film;
import it.cgmconsulting.cerullo.entity.Inventory;
import it.cgmconsulting.cerullo.entity.Store;
import it.cgmconsulting.cerullo.service.FilmService;
import it.cgmconsulting.cerullo.service.InventoryService;
import it.cgmconsulting.cerullo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@Validated
public class StoreController {

    @Autowired
    FilmService filmService;

    @Autowired
    StoreService storeService;

    @Autowired
    InventoryService inventoryService;

    /**
     * (ADD FILM TO STORE)
     * Chiamata di tipo Put.
     * Prima di salvare la risorsa sull Inventory si verifica che il film e lo store esistino.
     * @Param @PathVariable long storeId
     * @Param @PathVariable long filmId
     * **/
    @PutMapping("/add-film-to-store/{storeId}/{filmId}")
    public ResponseEntity addFilmToStore( @PathVariable long storeId, @PathVariable long filmId ) {

        Optional<Film> f = filmService.findById(filmId);
        if (f.isEmpty())
            return new ResponseEntity("Film not found", HttpStatus.NOT_FOUND);

        Optional<Store> s= storeService.findById(storeId);
        if (s.isEmpty())
            return new ResponseEntity("Store not found", HttpStatus.NOT_FOUND);

        Inventory i = new Inventory(new Store(storeId), new Film(filmId));
        inventoryService.save(i);
        return new ResponseEntity<>("The film with id " +filmId +  " has been added to store: "+ s.get().getStoreName() , HttpStatus.OK);
    }


    /**
     * (COUNT RENTALS IN RANGESTORE)
     * Chiamata di tipo Get.
     * Conteggio dei noleggi in un determinato store in un determinato arco temporale.
     * La corretta sintassi per inviare i parametri dateStart e dateEnd è la seguente: yyyy-mm-dd (ES. 2022-03-12)
     * @Param @PathVariable long storeId
     * @Param @PathVariable String dateStart
     * @Param @PathVariable String dateEnd
     * **/
    @GetMapping("/-by-count-rentals-in-date-rangestore/{storeId}")
    public ResponseEntity<?>getCountRentalsDateRange(@PathVariable long storeId, @RequestParam String dateStart, @RequestParam String dateEnd ){
        Optional<Store> s= storeService.findById(storeId);
        if (s.isEmpty())
            return new ResponseEntity("Store not found", HttpStatus.NOT_FOUND);

        long num= storeService.getCountRentalsDateRange(storeId,dateStart,dateEnd);
        if (num==0){
            return new ResponseEntity<>("-Correct sintax start and end date yyyy-mm-dd \n- no films between this range",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("CountRentalsDateRange: "+num,HttpStatus.OK);
    }


}

package it.cgmconsulting.cerullo.controller;

import it.cgmconsulting.cerullo.entity.*;
import it.cgmconsulting.cerullo.payload.request.RentalRequest;
import it.cgmconsulting.cerullo.payload.response.FilmStoreInventoryResponse;
import it.cgmconsulting.cerullo.payload.response.RentalResponse;
import it.cgmconsulting.cerullo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class RentalController {

    @Autowired
    RentalService rentalService;

    @Autowired
    StoreService storeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    FilmService filmService;


    /**
     * (ADD UPDATE RENTAL)
     * Chiamata di tipo Put.
     * Nel metodo si aggiunge una tupla rental e in caso di restituzione avviene aggiornamento del record con una update.
     * Effettuati diversi controlli commentati nel metodo.
     * **/
    @PutMapping("/add-update-rental")
    public ResponseEntity<?> addUpdateRental(@Valid @RequestBody RentalRequest request) {

        //verifico presenza customerId
        Optional<Customer> c = customerService.findById(request.getCustomerId());
        if (c.isEmpty())
            return new ResponseEntity("Customer not found", HttpStatus.NOT_FOUND);

        //verifico presenza di inventory
        if (!inventoryService.existsById(request.getInventoryId()))
            return new ResponseEntity("Inventory not found", HttpStatus.NOT_FOUND);

        // creo Lista rental per vedere lo storico del CustomerId
        List <RentalResponse> rental= rentalService.getRentalCustomer(request.getCustomerId(), request.getInventoryId());

        // se lista vuota vuol dire che non esiste storico con customerId e inventoryId da eventualmente modificare
        if (rental.isEmpty()){
            Optional<FilmStoreInventoryResponse> i = inventoryService.getInventoryDetails(request.getInventoryId());

            // Si contano i film usciti e quelli riguardanti l'inventario dello storeId selezionato
            long inventarioFilm = filmService.getFilmByIdSQL(i.get().getFilmId(), i.get().getStoreId());
            long filmUsciti = filmService.getFilmStoreNullByIdSQL(i.get().getFilmId(), i.get().getStoreId());

            // se i film usciti sono uguali a quelli dell'inventario non posso affittare un altro film da quello storeId
            if (inventarioFilm <= filmUsciti)
                return new ResponseEntity<>("There are not film available in store ", HttpStatus.OK);

            // Istanzio nuovo rental e lo salvo con data return a NULL
            RentalId rD= new RentalId(new Customer(request.getCustomerId()),new Inventory(request.getInventoryId()), LocalDateTime.now());
            Rental r= new Rental(rD,null);
            rentalService.save(r);
            return new ResponseEntity<>("Rental create for customer: "+ request.getCustomerId(),HttpStatus.OK);
        }

        //Se rental esiste con RentalReturn a NULL restituisco il film
        if(rental.get(0).getRentalReturn()==null){
            RentalId rD= new RentalId(new Customer(rental.get(0).getCustomerId()),new Inventory(rental.get(0).getInventoryId()), rental.get(0).getStartRental());
            Rental r= new Rental(rD,LocalDateTime.now());
            rentalService.save(r);
            return new ResponseEntity<>("Film restored by customer: " +request.getCustomerId() , HttpStatus.OK);
        }

        // Ricavo filmId e storeId dell'Inventory
        Optional<FilmStoreInventoryResponse> i = inventoryService.getInventoryDetails(request.getInventoryId());

        long inventarioFilm = filmService.getFilmByIdSQL(i.get().getFilmId(), i.get().getStoreId());
        long filmUsciti = filmService.getFilmStoreNullByIdSQL(i.get().getFilmId(), i.get().getStoreId());
        if (inventarioFilm <= filmUsciti)
            return new ResponseEntity<>("There are not film available in store ", HttpStatus.OK);


        //controllare primo elemento della lista se RentalReturnValorizzato vuol dire che customer ordina un altra volta e non resituisce
        if (rental.get(0).getRentalReturn()!=null){

            // creo Rental di un nuovo Customer
            RentalId rD= new RentalId(new Customer(request.getCustomerId()),new Inventory(request.getInventoryId()), LocalDateTime.now());
            Rental r= new Rental(rD,null);
            rentalService.save(r);
            return new ResponseEntity<>("Rental create for customer: " + request.getCustomerId(),HttpStatus.OK);
            }

        List <RentalResponse> rentals= rentalService.getRentalCustomer(request.getCustomerId(), request.getInventoryId());
        return new ResponseEntity<>(rentals,HttpStatus.OK);
    }

    /**
     * (COUNT CUSTOMERS BY STORE)
     * Chiamata di tipo Get.
     * Vengono contati i customer di un singolo store.
     * @Param @PathVariable String storeName.
     * **/
    @GetMapping("/count-customers-by-store/{storeName}")
    public ResponseEntity <?>getCountCostumersFromStore(@PathVariable String storeName ){
        // Verifico che lo store esista
        Optional<Store> s= storeService.findByStoreName(storeName);
        if (s.isEmpty())
            return new ResponseEntity("Store not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(rentalService.getCountCostumersFromStore(storeName), HttpStatus.OK);
    }


}

package it.cgmconsulting.cerullo.repository;

import it.cgmconsulting.cerullo.entity.Customer;
import it.cgmconsulting.cerullo.payload.response.FilmRentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    @Query(value = "SELECT new it.cgmconsulting.cerullo.payload.response.FilmRentResponse("
            + "f.filmId,"
            + "f.title,"
            + "s.storeName "
            + ") FROM Rental AS r "
            + "INNER JOIN Inventory AS i ON r.rentalId.inventory.inventoryId=i.inventoryId "
            + "INNER JOIN Film AS f ON i.film.filmId=f.filmId "
            + "INNER JOIN Store AS s ON i.store.storeId = s.storeId "
            + "WHERE r.rentalId.customer.customerId= :customerId "
    )
    List<FilmRentResponse> getFilmRentByCustomer(@Param("customerId") long customerId);
}

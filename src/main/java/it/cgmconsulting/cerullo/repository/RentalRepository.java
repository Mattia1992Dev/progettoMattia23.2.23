package it.cgmconsulting.cerullo.repository;

import it.cgmconsulting.cerullo.entity.Rental;
import it.cgmconsulting.cerullo.entity.RentalId;
import it.cgmconsulting.cerullo.payload.response.CustomerStoreResponse;
import it.cgmconsulting.cerullo.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.cerullo.payload.response.RentalResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, RentalId> {



    @Query(value = "SELECT new it.cgmconsulting.cerullo.payload.response.CustomerStoreResponse("
            + "r.rentalId.inventory.store.storeName , "
            + "(COUNT(DISTINCT r.rentalId.customer.customerId)) "
            + ") FROM Rental r "
            + "INNER JOIN Inventory AS i ON r.rentalId.inventory.inventoryId=i.inventoryId "
            + "WHERE r.rentalId.inventory.store.storeName= :storeName "
    )
    List<CustomerStoreResponse> getCountCostumersFromStore(@Param("storeName") String storeName);



    @Query(value = "SELECT new it.cgmconsulting.cerullo.payload.response.RentalResponse("
            + "r.rentalId.customer.customerId, "
            + "r.rentalId.inventory.inventoryId," +
            "r.rentalId.rentalDate," +
            "r.rentalReturn "
            + ") FROM Rental r "
            + "WHERE r.rentalId.customer.customerId = :customerId  AND r.rentalId.inventory.inventoryId = :inventoryId"
            + " ORDER BY r.rentalId.rentalDate DESC"
    )
    List<RentalResponse> getRentalCustomer(@Param("customerId") Long customerId, @Param("inventoryId") Long inventoryId);


    @Query(value = "SELECT new it.cgmconsulting.cerullo.payload.response.FilmMaxRentResponse("
            + "f.filmId, "
            + "f.title,"
            + "(COUNT(i.film.filmId)) AS totalRent "
            + ") FROM Rental r "
            + "INNER JOIN Inventory AS i ON r.rentalId.inventory.inventoryId=i.inventoryId "
            + "INNER JOIN Film AS f ON i.film.filmId=f.filmId "
            + "GROUP BY i.film.filmId "
            + "ORDER BY totalRent DESC "

    )
    List<FilmMaxRentResponse> getMaxRentedFilm();


}

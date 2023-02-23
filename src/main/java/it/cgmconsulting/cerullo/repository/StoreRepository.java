package it.cgmconsulting.cerullo.repository;

import it.cgmconsulting.cerullo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {

    Optional<Store> findByStoreName(String storeName);


    @Query(value = "SELECT COUNT(r.customer_id) " +
            "FROM rental r " +
            "JOIN inventory i ON r.inventory_id = i.inventory_id " +
            "WHERE i.store_id = :storeId AND r.rental_date BETWEEN :dateStart AND :dateEnd "
            ,nativeQuery = true
    )
    long getCountRentalsDateRange(@Param("storeId") Long filmId, @Param("dateStart") String dateStart,@Param("dateEnd") String dateEnd);
}

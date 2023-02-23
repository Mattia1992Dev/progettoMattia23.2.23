package it.cgmconsulting.cerullo.repository;

import it.cgmconsulting.cerullo.entity.Inventory;
import it.cgmconsulting.cerullo.payload.response.FilmStoreInventoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsById(long id);

    @Query(value = "SELECT new it.cgmconsulting.cerullo.payload.response.FilmStoreInventoryResponse("
            + "i.film.filmId,"
            + "i.store.storeId"
            + ") FROM Inventory AS i "
            + "WHERE i.inventoryId= :inventoryId "
    )
    Optional<FilmStoreInventoryResponse> getInventoryDetails(@Param("inventoryId") long inventoryId);
}

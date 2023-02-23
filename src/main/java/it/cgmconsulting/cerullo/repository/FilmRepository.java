package it.cgmconsulting.cerullo.repository;

import it.cgmconsulting.cerullo.entity.Film;
import it.cgmconsulting.cerullo.payload.response.FilmResponseDetails;
import it.cgmconsulting.cerullo.payload.response.StoreNameResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {


    @Query(value = "SELECT new it.cgmconsulting.cerullo.payload.response.StoreNameResponse("
            + "f.filmId,"
            + "f.title,"
            + "s.storeName,"
            + "(COUNT(f.filmId)) AS disp "
            + ") FROM Film AS f "
            + "INNER JOIN Inventory AS i ON i.film.filmId=f.filmId "
            + "INNER JOIN Store AS s ON s.storeId=i.store.storeId "
            + "WHERE f.filmId= :filmId "
            + "GROUP BY s.storeName "
    )
    List<StoreNameResponse> getFilmInStore(@Param("filmId") long filmId);

    @Query(value = "SELECT new it.cgmconsulting.cerullo.payload.response.FilmResponseDetails("
            + "f.filmId,"
            + "f.title,"
            + "f.description,"
            + "f.releaseYear,"
            + "l.languageName "
            + ") FROM Film AS f "
            + "INNER JOIN Inventory AS i ON i.film.filmId=f.filmId "
            + "INNER JOIN Language AS l ON l.languageId=f.language.languageId "
            + "WHERE l.languageId= :languageId "
            + "GROUP BY f.filmId "
    )
    List<FilmResponseDetails> getFilmByLanguage(@Param("languageId") long filmId);


    @Query(value = "SELECT COUNT(f.film_id) " +
            "FROM film AS f " +
            "JOIN inventory AS i ON (f.film_id=i.film_id) " +
            "JOIN store AS s ON (s.store_id=i.store_id) " +
            "WHERE f.film_id= :filmId AND s.store_id= :storeId "
            ,nativeQuery = true
    )
    Long getFilmByIdSQL( @Param("filmId") Long filmId, @Param("storeId") Long storeId);

    @Query(value = "SELECT COUNT(f.film_id) " +
            "FROM film AS f " +
            "JOIN inventory AS i ON f.film_id = i.film_id " +
            "JOIN rental AS r ON i.inventory_id = r.inventory_id " +
            "JOIN store AS s ON s.store_id = i.store_id " +
            "WHERE f.film_id = :filmId AND s.store_id= :storeId AND r.rental_return IS NULL",nativeQuery = true
    )
    Long getFilmStoreNullByIdSQL( @Param("filmId") Long filmId, @Param("storeId") Long storeId);






}

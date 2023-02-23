package it.cgmconsulting.cerullo.repository;

import it.cgmconsulting.cerullo.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = "SELECT f.title AS title_film " +
            "FROM film_staff fs " +
            "JOIN film f ON fs.film_id = f.film_id " +
            "JOIN staff s ON fs.staff_id = s.staff_id " +
            "WHERE s.staff_id IN (:staffId) AND fs.role_id = 1 " +
            "ORDER BY title_film",nativeQuery = true
    )
    List<String > getFilmByActor( @Param("staffId") int [] staffId);








}

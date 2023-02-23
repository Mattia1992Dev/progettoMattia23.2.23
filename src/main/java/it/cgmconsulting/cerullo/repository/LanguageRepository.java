package it.cgmconsulting.cerullo.repository;

import it.cgmconsulting.cerullo.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Long> {
}

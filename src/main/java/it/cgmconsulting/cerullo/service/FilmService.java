package it.cgmconsulting.cerullo.service;

import it.cgmconsulting.cerullo.entity.Film;
import it.cgmconsulting.cerullo.payload.response.FilmResponseDetails;
import it.cgmconsulting.cerullo.payload.response.StoreNameResponse;
import it.cgmconsulting.cerullo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public void save(Film f){
        filmRepository.save(f);
    }

    public Optional<Film> findById(long id){
        return filmRepository.findById(id);

    }

    public List<StoreNameResponse> getFilmInStore(long filmId){
        return filmRepository.getFilmInStore(filmId);
    }

    public List<FilmResponseDetails> getFilmByLanguage(long filmLanguage) {
        return filmRepository.getFilmByLanguage(filmLanguage);
    }

    public Long getFilmByIdSQL(long filmId, long storeId){
        return filmRepository.getFilmByIdSQL(filmId, storeId);
    }

    public Long getFilmStoreNullByIdSQL(long filmId, long storeId){
        return filmRepository.getFilmStoreNullByIdSQL(filmId, storeId);
    }
}

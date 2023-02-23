package it.cgmconsulting.cerullo.service;

import it.cgmconsulting.cerullo.entity.Genre;
import it.cgmconsulting.cerullo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public void save(Genre g){
        genreRepository.save(g);
    }

    public Optional<Genre> findById(long id){
        return genreRepository.findById(id);

    }
}

package it.cgmconsulting.cerullo.controller;

import it.cgmconsulting.cerullo.entity.Customer;
import it.cgmconsulting.cerullo.entity.Film;
import it.cgmconsulting.cerullo.entity.Genre;
import it.cgmconsulting.cerullo.entity.Language;
import it.cgmconsulting.cerullo.payload.request.FilmRequest;
import it.cgmconsulting.cerullo.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.cerullo.payload.response.StoreNameResponse;
import it.cgmconsulting.cerullo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class FilmController {

    @Autowired
    FilmService filmService;

    @Autowired
    LanguageService languageService;

    @Autowired
    GenreService genreService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RentalService rentalService;


    /**
     * (UPDATE DEL FILM)
     * Chiamata di tipo Patch.
     * Nel metodo si verifica che il film esista.
     * Viene verificato se esiste la lingua e il genere prima di eventuale inserimento.
     * @Param @RequestBody [it.cgmconsulting.cerullo.payload.request.FilmRequest.class]
     * @Param @PathVariable long filmId
     * **/
    @PatchMapping("/update-film/{filmId}")
    @Transactional
    public ResponseEntity updateFilm(@Valid @RequestBody FilmRequest request, @PathVariable long filmId ) {

        Optional<Film> f = filmService.findById(filmId);
        if (f.isEmpty())
            return new ResponseEntity("Film not found", HttpStatus.NOT_FOUND);

        f.get().setTitle(request.getTitle());
        f.get().setDescription(request.getDescription());
        f.get().setReleaseYear(request.getReleaseYear());
        // Se language è diverso da zero verifico se esiste prima di salvarlo

        if (request.getLanguageId()!=0){
            Optional<Language> l = languageService.findById(request.getLanguageId());
            if (l.isEmpty())
                return new ResponseEntity<>("Language not found", HttpStatus.BAD_REQUEST);
            f.get().setLanguage(new Language(request.getLanguageId(),l.get().getLanguageName()));
        }

        // Se genre è diverso da zero verifico se esiste prima di salvarlo
        if (request.getGenreId()!=0)
        {
            Optional<Genre> g = genreService.findById(request.getGenreId());
            if (g.isEmpty())
                return new ResponseEntity<>("Genre not found", HttpStatus.BAD_REQUEST);
            f.get().setGenre(new Genre(request.getGenreId(), g.get().getGenreName()));
        }

        return new ResponseEntity<>(f, HttpStatus.OK);
    }


    /**
     * (FIND FILM IN STORE)
     * Chiamata di tipo Get.
     * Nel metodo si verifica che il film esista. In caso esista il film si verifica che quest'ultimo sia
     * presente almeno in uno store.
     * In caso positivo viene inviata una response con i seguenti parametri:
     * film_id, title, store_name, inventario
     * **/
    @GetMapping("/find-film-in-store/{filmId}")
    public ResponseEntity<?> getFilmInStore(@PathVariable long filmId){
        Optional<Film> f = filmService.findById(filmId);
        if (f.isEmpty())
            return new ResponseEntity("Film not found", HttpStatus.NOT_FOUND);

        List <StoreNameResponse> film= filmService.getFilmInStore(filmId);

        // in base alla lista verifico se il film è presente in almeno uno store
        if (film.isEmpty())
            return new ResponseEntity<>("Film not present in stores", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(film,HttpStatus.OK);
    }


    /**
     * (FIND FILM BY LANGUAGE)
     * Chiamata di tipo Get.
     * Nel metodo si verifica che la lingua esista.
     * In caso positivo viene inviata una response con i seguenti parametri:
     * film_id, title, description, release_year, language_name
     * **/
    @GetMapping("/find-films-by-language/{languageId}")
    public ResponseEntity<?> getFilmByLanguage(@PathVariable long languageId){
        Optional<Language> f = languageService.findById(languageId);
        if (f.isEmpty())
            return new ResponseEntity("Language not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(filmService.getFilmByLanguage(languageId), HttpStatus.OK);

    }

    /**
     * (FIND FILMS RENT BY ONE CUSTOMER)
     * Chiamata di tipo Get.
     * Nel metodo si verifica che il customer esista.
     * In caso positivo viene inviata una response con i seguenti parametri:
     * film_id, title, store_name
     * **/
    @GetMapping("/find-all-films-rent-by-one-customer/{customerId}")
    public ResponseEntity<?> getFilmRentByCustomer(@PathVariable long customerId){
        Optional<Customer> c = customerService.findById(customerId);
        if (c.isEmpty())
            return new ResponseEntity("Customer not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(customerService.getFilmRentByCustomer(customerId),HttpStatus.OK);

    }


    /**
     * (FIND FILM WITH MAX NUMBER OF RENT)
     * Chiamata di tipo Get.
     * Nel metodo si verifica che se ci sono più film con lo stesso numero (massimo) di noleggi.
     * Viene inviata una response con i seguenti parametri:
     * film_id, title, numero di noleggi totale
     * **/
    @GetMapping("/find-film-with-max-number-of-rent")
    public ResponseEntity<?> getMaxRentedFilm() {


        List<FilmMaxRentResponse> l = rentalService.getMaxRentedFilm();
        if(!l.isEmpty()){
        List<FilmMaxRentResponse> list = new ArrayList<>();

        // Se ci sono elementi con lo stesso numero massimo di total rented gli aggrego alla lista chiamata list

        list.add(new FilmMaxRentResponse(l.get(0).getFilmId(), l.get(0).getTitle(), l.get(0).getNumberTotalRented()));
        for (int i=0;i<l.size()-1;i++){

            if ((l.get(i).getNumberTotalRented()) == (l.get(i + 1).getNumberTotalRented())){
                list.add(new FilmMaxRentResponse(l.get(i+1).getFilmId(), l.get(i+1).getTitle(), l.get(i+1).getNumberTotalRented()));
            }
            else return new ResponseEntity<>(list, HttpStatus.OK);
        }}


             return new ResponseEntity<>("No film rented", HttpStatus.NOT_FOUND);


    }
}


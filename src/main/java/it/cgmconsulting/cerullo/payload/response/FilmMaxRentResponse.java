package it.cgmconsulting.cerullo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmMaxRentResponse extends FilmResponse {


    private long numberTotalRented;

    public FilmMaxRentResponse(long filmId, String title, long numberTotalRented) {
        super(filmId, title);
        this.numberTotalRented = numberTotalRented;
    }

}

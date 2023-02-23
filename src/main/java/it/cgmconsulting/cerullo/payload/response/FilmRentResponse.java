package it.cgmconsulting.cerullo.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmRentResponse extends FilmResponse {


    private String storeName;

    public FilmRentResponse(long filmId, String title, String storeName) {
        super(filmId, title);
        this.storeName = storeName;
    }

    public FilmRentResponse(String storeName) {
        this.storeName = storeName;
    }
}

package it.cgmconsulting.cerullo.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmResponseDetails extends FilmResponse{

    private String description;

    private short releaseYear;

    private String language;

    public FilmResponseDetails(long filmId, String title, String description, short releaseYear, String language) {
        super(filmId, title);
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
    }

    public FilmResponseDetails(String description, short releaseYear, String language) {
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
    }
}

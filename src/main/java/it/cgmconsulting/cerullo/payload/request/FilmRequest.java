package it.cgmconsulting.cerullo.payload.request;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
public class FilmRequest {

    @NotBlank @Size(min = 3, max = 100)
    private String title;

    @NotBlank @Size(min = 3, max = 65535)
    private String description;

    @Max(32767)
    private short releaseYear;

    private long languageId;

    private long genreId;



}

package it.cgmconsulting.cerullo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmStoreInventoryResponse {

    private long filmId;

    private long storeId;

}

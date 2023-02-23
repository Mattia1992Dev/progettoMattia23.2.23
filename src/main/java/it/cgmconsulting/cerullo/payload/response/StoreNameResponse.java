package it.cgmconsulting.cerullo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class StoreNameResponse extends FilmResponse{

    private String storeName;

    private long inventario;

    public StoreNameResponse(long filmId, String title, String storeName, long inventario) {
        super(filmId, title);
        this.storeName = storeName;
        this.inventario = inventario;
    }

    public StoreNameResponse(String storeName, long inventario) {
        this.storeName = storeName;
        this.inventario = inventario;
    }
}

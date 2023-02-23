package it.cgmconsulting.cerullo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalResponse {

    private long customerId;

    private long inventoryId;

    private LocalDateTime startRental;

    private LocalDateTime rentalReturn;


}

package it.cgmconsulting.cerullo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rental {

    @EmbeddedId
    private RentalId rentalId;

    private LocalDateTime rentalReturn;


    public Rental(RentalId rentalId, LocalDateTime rentalReturn) {
        this.rentalId = rentalId;
        this.rentalReturn = rentalReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rental rental)) return false;
        return Objects.equals(rentalId, rental.rentalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId);
    }
}

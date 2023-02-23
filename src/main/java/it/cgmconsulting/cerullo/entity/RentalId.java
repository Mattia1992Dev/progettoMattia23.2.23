package it.cgmconsulting.cerullo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inventory_id", nullable = false)
    private Inventory inventory;

    @CreationTimestamp
    private LocalDateTime rentalDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentalId rentalId)) return false;
        return Objects.equals(customer, rentalId.customer) && Objects.equals(inventory, rentalId.inventory) && Objects.equals(rentalDate, rentalId.rentalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, inventory, rentalDate);
    }
}

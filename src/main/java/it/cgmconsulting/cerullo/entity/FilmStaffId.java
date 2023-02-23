package it.cgmconsulting.cerullo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmStaffId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="film_id", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_id", nullable = false)
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmStaffId that)) return false;
        return Objects.equals(film, that.film) && Objects.equals(staff, that.staff) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, staff, role);
    }
}

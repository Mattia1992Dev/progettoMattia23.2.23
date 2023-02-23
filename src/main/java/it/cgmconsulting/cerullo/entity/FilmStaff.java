package it.cgmconsulting.cerullo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FilmStaff {

    @EmbeddedId
    private FilmStaffId filmStaffId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmStaff filmStaff)) return false;
        return Objects.equals(filmStaffId, filmStaff.filmStaffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmStaffId);
    }
}

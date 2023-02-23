package it.cgmconsulting.cerullo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long languageId;

    @Column(length = 20, nullable = false, unique = true)
    private String languageName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language language)) return false;
        return languageId == language.languageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId);
    }
}

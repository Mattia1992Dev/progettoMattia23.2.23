package it.cgmconsulting.cerullo.service;
import it.cgmconsulting.cerullo.entity.Language;
import it.cgmconsulting.cerullo.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;
    public void save(Language l){
        languageRepository.save(l);
    }

    public Optional<Language> findById(long id){
        return languageRepository.findById(id);

    }
}

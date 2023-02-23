package it.cgmconsulting.cerullo.service;

import it.cgmconsulting.cerullo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public List<String > getFilmByActor( int [] staffId){
        return staffRepository.getFilmByActor( staffId);
    }
}

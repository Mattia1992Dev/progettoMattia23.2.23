package it.cgmconsulting.cerullo.controller;

import it.cgmconsulting.cerullo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StaffController {

    @Autowired
    StaffService staffService;

    /**
     * (FIND FILMS BY ACTORS)
     * Chiamata di tipo Get.
     * @Param @RequestParam int [] staffId.
     * Nella response torna una lista con i film in ordine alfabetico.
     * Nella query si è verificato che gli ID passati abbiano il role di actor.
     * **/
    @GetMapping("/find-films-by-actors")
    public ResponseEntity <?> getFilmByActor(@RequestParam int [] staffId ){
        return new ResponseEntity<>(staffService.getFilmByActor(staffId), HttpStatus.OK);
    }
}

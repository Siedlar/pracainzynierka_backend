package pl.siedlarski.restfulworkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.siedlarski.restfulworkout.dao.CwiczeniaRepository;
import pl.siedlarski.restfulworkout.dao.EkwipunekRepository;
import pl.siedlarski.restfulworkout.dao.PartieRepository;
import pl.siedlarski.restfulworkout.dao.TrudnoscRepository;
import pl.siedlarski.restfulworkout.entity.*;
import pl.siedlarski.restfulworkout.payload.MessageResponse;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cwiczenia")
public class CwiczeniaController {
    @Autowired
    CwiczeniaRepository cwiczeniaRepository;
    @Autowired
    EkwipunekRepository ekwipunekRepository;
    @Autowired
    PartieRepository partieRepository;
    @Autowired
    TrudnoscRepository trudnoscRepository;

    @GetMapping("/getCwiczenia")
    public ResponseEntity<?> getCwiczenia(Principal principal){
        List<Cwiczenie> listaCwiczen=cwiczeniaRepository.findAll();
        return ResponseEntity.ok().body(listaCwiczen);
    }
    @GetMapping("/getPartie")
    public ResponseEntity<?> getPartie(Principal principal){
        List<Partia> listaPartii=partieRepository.findAll();
        return ResponseEntity.ok().body(listaPartii);
    }
    @GetMapping("/getTrudnosc")
    public ResponseEntity<?> getTrudnosc(Principal principal){
        List<Trudnosc> listaTrudnosc=trudnoscRepository.findAll();
        return ResponseEntity.ok().body(listaTrudnosc);
    }
    @GetMapping("/getEkwipunek")
    public ResponseEntity<?> getEkwipunek(Principal principal){
        List<Ekwipunek> listaEkwipunek=ekwipunekRepository.findAll();
        return ResponseEntity.ok().body(listaEkwipunek);
    }
}

package pl.siedlarski.restfulworkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.siedlarski.restfulworkout.dao.*;
import pl.siedlarski.restfulworkout.entity.*;
import pl.siedlarski.restfulworkout.payload.MessageResponse;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/trening")
public class TreningController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RekordRepository rekordRepository;
    @Autowired
    TypyTreninguRepository typyTreninguRepository;
    @Autowired
    HistoriaTreninguRepository historiaTreninguRepository;
    @Autowired
    HistoriaCwiczenRepository historiaCwiczenRepository;
    @Autowired
    JednoCwiczenieRepository jednoCwiczenieRepository;
    @PostMapping("/dodajTrening")
    public ResponseEntity<?> savePomiar(Principal principal,@RequestBody HistoriaTreningu historiaTreningu) {
        User user = userRepository.findByUsername(principal.getName()).get();
        historiaTreningu.setUser(user);
        System.out.println("DODAWANIE TRENINGU");
        System.out.println(historiaTreningu);
        for(HistoriaCwiczen historiaCwiczen:historiaTreningu.getHistoriaCwiczen()){
            for(JednoCwiczenie jednoCwiczenie:historiaCwiczen.getJednoCwiczenie()){
                jednoCwiczenie.setHistoriaCwiczen(historiaCwiczen);
            }

        }


       historiaTreninguRepository.save(historiaTreningu);
        updateRekordy(historiaTreningu);

 return ResponseEntity.ok().body(new MessageResponse("GITARA SIEMA"));
    }
    @GetMapping("/getTreningi")
    public ResponseEntity<?> getTreningi(Principal principal){
        User user=userRepository.findByUsername(principal.getName()).get();
        List<HistoriaTreningu> historiaTreningu=historiaTreninguRepository.findByUser(user);

        return ResponseEntity.ok().body(historiaTreningu);
    }
    @GetMapping("/getTypyTreningu")
    public ResponseEntity<?> getTypyTreningu(Principal principal){
        System.out.println("siema");
        List<TypTreningu> typTreningu=typyTreninguRepository.findAll();
        System.out.println(typTreningu);
        return ResponseEntity.ok().body(typTreningu);
    }
    @PostMapping("/deleteTreningi")
    public ResponseEntity<?> deletePomiar(Principal principal,@RequestBody HistoriaTreningu historiaTreningu){
        System.out.println(historiaTreningu);
        rekordRepository.deleteByHistoriaTreningu(historiaTreningu);
        historiaTreninguRepository.delete(historiaTreningu);

        return ResponseEntity.ok().body(new MessageResponse("Udalo sie usunac pomiar"));
    }


    @GetMapping("/getRekordy")
    public ResponseEntity<?> getRekordy(Principal principal){
        User user=userRepository.findByUsername(principal.getName()).get();
       List<Rekord> listaRekordow= rekordRepository.findByUser(user);
       if(listaRekordow.isEmpty()){
           return ResponseEntity.badRequest().body(new MessageResponse("Nie masz jeszcze zapisanych rekordow"));
       }
        return ResponseEntity.ok().body(listaRekordow);
    }

    public void updateRekordy(HistoriaTreningu historiaTreningu){
        for(HistoriaCwiczen historiaCwiczen:historiaTreningu.getHistoriaCwiczen()){
            Rekord rekord= rekordRepository.findByUserAndCwiczenie(historiaTreningu.getUser(), historiaCwiczen.getCwiczenie());
            if(rekord == null){
                rekord=new Rekord();
                rekord.setUser(historiaTreningu.getUser());
                rekord.setCwiczenie(historiaCwiczen.getCwiczenie());
             rekord.setMaks_czas_trwania(historiaCwiczen.getCzasTrwaniaCwiczenia());
           for(JednoCwiczenie jednoCwiczenie:historiaCwiczen.getJednoCwiczenie()){
               rekord.setMaks_czas(jednoCwiczenie.getCzas());
               rekord.setMaks_ciezar(jednoCwiczenie.getCiezar());
               rekord.setMaks_kilometry(jednoCwiczenie.getKilometry());
               rekord.setMaks_seria(jednoCwiczenie.getSeria());
               rekord.setMaks_powtorzenia(jednoCwiczenie.getPowtorzenia());
           }
           rekord.setHistoriaTreningu(historiaTreningu);
           rekordRepository.save(rekord);
            }
            if(rekord.getMaks_czas_trwania() < historiaCwiczen.getCzasTrwaniaCwiczenia()){
                rekord.setMaks_czas_trwania(historiaCwiczen.getCzasTrwaniaCwiczenia());
            }

            for(JednoCwiczenie jednoCwiczenie:historiaCwiczen.getJednoCwiczenie()){
                if(jednoCwiczenie.getCiezar() != null){
                    if(rekord.getMaks_ciezar() < jednoCwiczenie.getCiezar()){
                        rekord.setMaks_ciezar(jednoCwiczenie.getCiezar());
                    }
                }

if(jednoCwiczenie.getCzas()!= null){
    if(rekord.getMaks_czas() < jednoCwiczenie.getCzas()){
        rekord.setMaks_czas(jednoCwiczenie.getCzas());
    }
}

        if(jednoCwiczenie.getKilometry() != null ){
            if(rekord.getMaks_kilometry()< jednoCwiczenie.getKilometry()){
                rekord.setMaks_kilometry(jednoCwiczenie.getKilometry());
            }
        }
             if(jednoCwiczenie.getPowtorzenia() != null){
                 if(rekord.getMaks_powtorzenia()< jednoCwiczenie.getPowtorzenia()){
                     rekord.setMaks_powtorzenia(jednoCwiczenie.getPowtorzenia());
                 }
             }
              if(jednoCwiczenie.getSeria() !=null){
                  if(rekord.getMaks_seria()<jednoCwiczenie.getSeria()){
                      rekord.setMaks_seria(jednoCwiczenie.getSeria());
                  }
              }


            }



            rekord.setHistoriaTreningu(historiaTreningu);
            rekordRepository.save(rekord);

            }


    }
}

package pl.siedlarski.restfulworkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.siedlarski.restfulworkout.dao.PomiarRepository;
import pl.siedlarski.restfulworkout.dao.UserRepository;
import pl.siedlarski.restfulworkout.entity.User;
import pl.siedlarski.restfulworkout.entity.UserInfo;
import pl.siedlarski.restfulworkout.entity.Wymiar;
import pl.siedlarski.restfulworkout.payload.ChangePassword;
import pl.siedlarski.restfulworkout.payload.MessageResponse;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pomiar")
public class PomiarController {
@Autowired
    PomiarRepository pomiarRepository;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/savePomiar")
    public ResponseEntity<?> savePomiar(Principal principal, @RequestBody Wymiar wymiar){
        System.out.println(wymiar);
        User user=userRepository.findByUsername(principal.getName()).get();
        wymiar.setUser(user);
        pomiarRepository.save(wymiar);
        return ResponseEntity.ok().body(new MessageResponse("Wymiar został dodany!!!"));
    }
    @GetMapping("/getPomiary")
    public ResponseEntity<?> getPomiary(Principal principal){
        User user=userRepository.findByUsername(principal.getName()).get();
    List<Wymiar> wymiar=pomiarRepository.findByUser(user);
    if(wymiar.isEmpty()){
        return ResponseEntity.badRequest().body(new MessageResponse("Nie masz zadnych pomiarow"));

    }
        return ResponseEntity.ok().body(wymiar);
    }
    @PostMapping("/deletePomiar")
    public ResponseEntity<?> deletePomiar(Principal principal,@RequestBody Wymiar wymiar){
      pomiarRepository.delete(wymiar);
        return ResponseEntity.ok().body(new MessageResponse("Udalo sie usunac pomiar"));
    }
}

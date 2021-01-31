package pl.siedlarski.restfulworkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.siedlarski.restfulworkout.dao.PomiarRepository;
import pl.siedlarski.restfulworkout.dao.UserRepository;
import pl.siedlarski.restfulworkout.entity.User;
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
        User user=userRepository.findByUsername(principal.getName()).get();
        wymiar.setUser(user);
        pomiarRepository.save(wymiar);
        return ResponseEntity.ok().body(new MessageResponse("Wymiar został dodany!!!"));
    }
    @GetMapping("/getPomiary")
    public ResponseEntity<?> getPomiary(Principal principal){
        User user=userRepository.findByUsername(principal.getName()).get();
    List<Wymiar> wymiar=pomiarRepository.findByUser(user);
        System.out.println( wymiar.get(0));
        return ResponseEntity.ok().body(wymiar);
    }
}

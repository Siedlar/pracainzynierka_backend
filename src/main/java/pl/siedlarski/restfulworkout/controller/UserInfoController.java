package pl.siedlarski.restfulworkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.siedlarski.restfulworkout.dao.ImageRepository;
import pl.siedlarski.restfulworkout.dao.UserInfoRepository;
import pl.siedlarski.restfulworkout.dao.UserRepository;
import pl.siedlarski.restfulworkout.entity.ImageModel;
import pl.siedlarski.restfulworkout.entity.User;
import pl.siedlarski.restfulworkout.entity.UserInfo;
import pl.siedlarski.restfulworkout.payload.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import static pl.siedlarski.restfulworkout.util.FileCompressor.compressBytes;
import static pl.siedlarski.restfulworkout.util.FileCompressor.decompressBytes;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/data")
public class UserInfoController {
    @Autowired
    ImageRepository imageRepository;
@Autowired
UserRepository userRepository;
@Autowired
    PasswordEncoder passwordEncoder;
@Autowired
    UserInfoRepository userInfoRepository;
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(Principal principal){
        userRepository.deleteByUsername(principal.getName());
        return ResponseEntity.ok().body(new MessageResponse("Dziękujemy za skorzystanie z naszej aplikacji. Mamy nadzieję że jeszcze kiedyś do nas wrócisz !!!"));
    }
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(Principal principal,@RequestBody ChangePassword password){
        userRepository.updateUserSetPasswordforUsername(principal.getName(),passwordEncoder.encode(password.getPassword()));
        return ResponseEntity.ok().body(new MessageResponse("Hasło zostało zmienione !!!"));
    }
    @PostMapping("/changeEmail")
    public ResponseEntity<?> changeEmail(Principal principal,@RequestBody ChangeEmail email){
        System.out.println(email.getEmail());
   if (     userRepository.existsByEmail(email.getEmail())){
       return ResponseEntity.badRequest().body(new MessageResponse("Podany adres email jest już w użyciu , prosimy podać inny !!!"));

   }
        userRepository.updateUserSetEmailforUsername(email.getEmail(),principal.getName());
        return ResponseEntity.ok().body(new MessageResponse("Adres email został zaaktualizowany!!!"));
    }
    @PostMapping("/changeLogin")
    public ResponseEntity<?> changeLogin(Principal principal,@RequestBody ChangeLogin login){
        if (     userRepository.existsByUsername(login.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Podana nazwa użytkownika jest już w użyciu , prosimy podać inny !!!"));

        }
        userRepository.updateUserSetLoginforUsername(principal.getName(),login.getUsername());
        return ResponseEntity.ok().body(new MessageResponse("Nazwa użytkownika została zaaktualizowana!!!"));
    }
    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(Principal principal){
        User user=userRepository.findByUsername(principal.getName()).get();
    UserInfo userInfo= user.getUserInfo();
      if (userInfo == null ){
 userInfo= new UserInfo();
         return ResponseEntity.badRequest().body(userInfo);
      }
            System.out.println(userInfo);

        return ResponseEntity.ok().body(userInfo);
    }
    @PostMapping("/saveUserInfo")
    public ResponseEntity<?> saveUserInfo(Principal principal,@RequestBody UserInfo userInfo){
        User user=userRepository.findByUsername(principal.getName()).get();
        userInfo.setUser(user);
        userInfoRepository.save(userInfo);
        user.setUserInfo(userInfo);
        userRepository.save(user);
        return ResponseEntity.ok().body(new MessageResponse("Udało się zapisać informacje"));
    }
    @PostMapping("/uploadImage")
    public ResponseEntity<?>  uplaodImage(Principal principal,@RequestParam("imageFile") MultipartFile file) throws IOException {
        User user=userRepository.findByUsername(principal.getName()).get();
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        img.setUser(user);
        imageRepository.save(img);
        user.setImageModel(img);
        userRepository.save(user);
        System.out.println("SIEMA");
        return ResponseEntity.ok().body(new MessageResponse("ALLES CLARE"));
    }
    @GetMapping("/getImage")
    public ResponseEntity<?> getImage(Principal principal) throws IOException {
        User user=userRepository.findByUsername(principal.getName()).get();
        if(user.getImageModel()==null){
            return ResponseEntity.badRequest().body(new MessageResponse("Uzytkownik nie ma jeszcze zdjecia profilowego"));
        }
        ImageModel retrievedImage = user.getImageModel();
        ImageModel img = new ImageModel(decompressBytes(retrievedImage.getPicByte()));
        return ResponseEntity.ok().body(img);
    }

}

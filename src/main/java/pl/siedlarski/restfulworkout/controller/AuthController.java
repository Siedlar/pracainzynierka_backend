package pl.siedlarski.restfulworkout.controller;


import oracle.net.ns.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.siedlarski.restfulworkout.dao.PasswordResetTokenRepository;
import pl.siedlarski.restfulworkout.dao.RoleRepository;
import pl.siedlarski.restfulworkout.dao.UserRepository;
import pl.siedlarski.restfulworkout.entity.ERole;
import pl.siedlarski.restfulworkout.entity.PasswordResetToken;
import pl.siedlarski.restfulworkout.entity.Role;
import pl.siedlarski.restfulworkout.entity.User;
import pl.siedlarski.restfulworkout.payload.*;
import pl.siedlarski.restfulworkout.security.jwt.JwtUtils;
import pl.siedlarski.restfulworkout.security.services.UserDetailsImpl;
import pl.siedlarski.restfulworkout.security.services.UserServiceReset;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private UserServiceReset userServiceReset;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getCredentials());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Błąd : Nazwa użytkownika jest już zajęta"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Błąd : Email jest już zajęty!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Rola nie znaleziona"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Rola nie znaleziona"));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Rola nie znaleziona"));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Rola nie znaleziona"));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Użytkownik został zarejestrowany"));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetRequest resetRequest) throws Exception {
        if (!userRepository.existsByEmail(resetRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Błąd : W bazie nie znajduję się użytkownik o podanym adresie email!"));
        }
        User user = userRepository.findByEmail(resetRequest.getEmail()).get();
        String token = UUID.randomUUID().toString();
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        tokenRepository.save(myToken);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("krystian.siedlarski@gmail.com");
        simpleMailMessage.setTo(resetRequest.getEmail());
        simpleMailMessage.setSubject("Zresetuj swoje hasło");
        simpleMailMessage.setText("Zresetuj swoje hasło pod adresem: http://localhost:4200/changePassword?token=" + token);
        emailSender.send(simpleMailMessage);
        return ResponseEntity.ok(new MessageResponse("Link do resetowania hasła został wysłany na podany adres email. Sprawdz swoją pocztę."));
    }
    @Transactional
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordPayload changePasswordPayload) throws Exception {
        final String result = userServiceReset.validatePasswordResetToken(changePasswordPayload.getToken());
        if (result == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Wprowadzony token jest błedny, spróbuj go wygenerować ponownie!!!"));
        }

        PasswordResetToken passwordResetToken = tokenRepository.findByToken(changePasswordPayload.getToken());
       User user=passwordResetToken.getUser();
       user.setPassword(encoder.encode(changePasswordPayload.getPassword()));
        if (user !=null) {
            userRepository.save(user);
            tokenRepository.deleteAllExpiredSince(new Date());
            return ResponseEntity.ok().body(new MessageResponse("Twoje hasło zostało zmienione."));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Twoje hasło zostało zmienione."));
    }
}




package pl.siedlarski.restfulworkout.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.siedlarski.restfulworkout.dao.PasswordResetTokenRepository;
import pl.siedlarski.restfulworkout.entity.PasswordResetToken;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
@Transactional
public class UserServiceReset {
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        System.out.println(passwordTokenRepository);
        System.out.println(passToken.getToken());
        if(passToken ==null){
            return null;
        }
        if(!isTokenExpired(passToken)){
        return "Token aktualny";
        }else{
            return null;
        }
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        System.out.println(cal);
        System.out.println(passToken.getExpiryDate());
        return passToken.getExpiryDate().before(cal.getTime());
    }
}

package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    private long userinfo_id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private int wzrost;
    private String city;
    private String ulica;
    private String kraj;
    private String phoneNumber;
    private String notatka;
    @OneToOne(mappedBy = "userInfo")
    private User user;
    public int getWzrost() {
        return wzrost;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWzrost(int wzrost) {
        this.wzrost = wzrost;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }


    public long getUserinfo_id() {
        return userinfo_id;
    }

    public void setUserinfo_id(long userinfo_id) {
        this.userinfo_id = userinfo_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
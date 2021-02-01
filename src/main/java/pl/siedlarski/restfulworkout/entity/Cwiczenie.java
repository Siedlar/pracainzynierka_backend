package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cwiczenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nazwa_cwiczenia;
    private String url_film;
    @OneToOne
    @JoinColumn(name="id")
    private Trudnosc trudnosc;
    @OneToMany(mappedBy="user")
    private List<Ekwipunek> ekwipunek;
    private String url_zdjecia;
    @OneToOne
    @JoinColumn(name="id")
    private Partia partia;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa_cwiczenia() {
        return nazwa_cwiczenia;
    }

    public void setNazwa_cwiczenia(String nazwa_cwiczenia) {
        this.nazwa_cwiczenia = nazwa_cwiczenia;
    }

    public String getUrl_film() {
        return url_film;
    }

    public void setUrl_film(String url_film) {
        this.url_film = url_film;
    }

    public Trudnosc getTrudnosc() {
        return trudnosc;
    }

    public void setTrudnosc(Trudnosc trudnosc) {
        this.trudnosc = trudnosc;
    }


    public List<Ekwipunek> getEkwipunek() {
        return ekwipunek;
    }

    public void setEkwipunek(List<Ekwipunek> ekwipunek) {
        this.ekwipunek = ekwipunek;
    }

    public String getUrl_zdjecia() {
        return url_zdjecia;
    }

    public void setUrl_zdjecia(String url_zdjecia) {
        this.url_zdjecia = url_zdjecia;
    }

    public Partia getPartia() {
        return partia;
    }

    public void setPartia(Partia partia) {
        this.partia = partia;
    }
}

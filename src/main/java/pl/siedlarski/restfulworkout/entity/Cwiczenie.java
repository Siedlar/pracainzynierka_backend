package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cwiczenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cwiczenie_id;
    private String nazwa_cwiczenia;
    private String url_film;
    @OneToOne
    @JoinColumn(name="trudnosc_id")
    private Trudnosc trudnosc;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            joinColumns = { @JoinColumn(name = "cwiczenie_id") },
            inverseJoinColumns = { @JoinColumn(name = "ekwipunek_id") }
    )
    private List<Ekwipunek> ekwipunek;
    private String url_zdjecia;
    @OneToOne
    @JoinColumn(name="partia_id")
    private Partia partia;
    private String wskazowki;

    public void setCwiczenie_id(long cwiczenie_id) {
        this.cwiczenie_id = cwiczenie_id;
    }

    public String getWskazowki() {
        return wskazowki;
    }

    public void setWskazowki(String wskazowki) {
        this.wskazowki = wskazowki;
    }

    public long getCwiczenie_id() {
        return cwiczenie_id;
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

    @Override
    public String toString() {
        return "Cwiczenie{" +
                "cwiczenie_id=" + cwiczenie_id +
                ", nazwa_cwiczenia='" + nazwa_cwiczenia + '\'' +
                ", url_film='" + url_film + '\'' +
                ", trudnosc=" + trudnosc +
                ", ekwipunek=" + ekwipunek +
                ", url_zdjecia='" + url_zdjecia + '\'' +
                ", partia=" + partia +
                ", wskazowki='" + wskazowki + '\'' +
                '}';
    }
}

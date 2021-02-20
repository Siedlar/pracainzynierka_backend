package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Wymiar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate data;
    private float waga;
    private int biceps;
    private int udo;
    private int talia;
    private int brzuch;
    private int przedramie;
    private int lydka;
    private int szyja;
    private int klatka;
    @ManyToOne( optional = false)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getWaga() {
        return waga;
    }

    public void setWaga(float waga) {
        this.waga = waga;
    }

    public int getBiceps() {
        return biceps;
    }

    public void setBiceps(int biceps) {
        this.biceps = biceps;
    }

    public int getUdo() {
        return udo;
    }

    public void setUdo(int udo) {
        this.udo = udo;
    }

    public int getTalia() {
        return talia;
    }

    public void setTalia(int talia) {
        this.talia = talia;
    }

    public int getBrzuch() {
        return brzuch;
    }

    public void setBrzuch(int brzuch) {
        this.brzuch = brzuch;
    }

    public int getPrzedramie() {
        return przedramie;
    }

    public void setPrzedramie(int przedramie) {
        this.przedramie = przedramie;
    }

    public int getLydka() {
        return lydka;
    }

    public void setLydka(int lydka) {
        this.lydka = lydka;
    }

    public int getSzyja() {
        return szyja;
    }

    public void setSzyja(int szyja) {
        this.szyja = szyja;
    }

    public int getKlatka() {
        return klatka;
    }

    public void setKlatka(int klatka) {
        this.klatka = klatka;
    }


    public void setUser(User user) {
        this.user = user;
    }
}
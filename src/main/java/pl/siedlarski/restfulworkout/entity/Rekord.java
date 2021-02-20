package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;

@Entity
public class Rekord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rekord_id;
    private Float maks_ciezar;
    private Integer maks_czas_trwania;
    private Integer maks_seria;
    private Integer maks_powtorzenia;
    private Integer maks_czas;
    private Integer maks_kilometry;


    public Integer getMaks_czas_trwania() {
        return maks_czas_trwania;
    }

    public void setMaks_czas_trwania(Integer maks_czas_trwania) {
        this.maks_czas_trwania = maks_czas_trwania;
    }

    @OneToOne
    @JoinColumn(name="cwiczenie_id")

    private Cwiczenie cwiczenie;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="historiatreningu_id")
    private HistoriaTreningu historiaTreningu;



    public void setHistoriaTreningu(HistoriaTreningu historiaTreningu) {
        this.historiaTreningu = historiaTreningu;
    }

    @ManyToOne( optional = false)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Cwiczenie getCwiczenie() {
        return cwiczenie;
    }

    public long getRekord_id() {
        return rekord_id;
    }

    public void setRekord_id(long rekord_id) {
        this.rekord_id = rekord_id;
    }

    public Float getMaks_ciezar() {
        return maks_ciezar;
    }

    public void setMaks_ciezar(Float maks_ciezar) {
        this.maks_ciezar = maks_ciezar;
    }

    public Integer getMaks_seria() {
        return maks_seria;
    }

    public void setMaks_seria(Integer maks_seria) {
        this.maks_seria = maks_seria;
    }

    public Integer getMaks_powtorzenia() {
        return maks_powtorzenia;
    }

    public void setMaks_powtorzenia(Integer maks_powtorzenia) {
        this.maks_powtorzenia = maks_powtorzenia;
    }

    public Integer getMaks_czas() {
        return maks_czas;
    }

    public void setMaks_czas(Integer maks_czas) {
        this.maks_czas = maks_czas;
    }

    public Integer getMaks_kilometry() {
        return maks_kilometry;
    }

    public void setMaks_kilometry(Integer maks_kilometry) {
        this.maks_kilometry = maks_kilometry;
    }



    public void setCwiczenie(Cwiczenie cwiczenie) {
        this.cwiczenie = cwiczenie;
    }



    public void setUser(User user) {
        this.user = user;
    }
}
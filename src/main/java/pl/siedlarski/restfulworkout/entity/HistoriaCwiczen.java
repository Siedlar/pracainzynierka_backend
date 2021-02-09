package pl.siedlarski.restfulworkout.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class HistoriaCwiczen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long historiacwiczen_id;
    @OneToOne
    @JoinColumn(name="cwiczenie_id")
    private Cwiczenie cwiczenie;
    private int czasTrwaniaCwiczenia;
    @OneToMany(mappedBy="historiaCwiczen",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<JednoCwiczenie> jednoCwiczenie;
    @JsonIgnore
    @ManyToMany(mappedBy = "historiaCwiczen",cascade = CascadeType.ALL)
    private List<HistoriaTreningu> historiaTreningu;

    public List<JednoCwiczenie> getJednoCwiczenie() {
        return jednoCwiczenie;
    }

    public void setJednoCwiczenie(List<JednoCwiczenie> jednoCwiczenie) {
        this.jednoCwiczenie = jednoCwiczenie;
    }

    public long getHistoriacwiczen_id() {
        return historiacwiczen_id;
    }

    public void setHistoriacwiczen_id(long historiacwiczen_id) {
        this.historiacwiczen_id = historiacwiczen_id;
    }

    public Cwiczenie getCwiczenie() {
        return cwiczenie;
    }

    public void setCwiczenie(Cwiczenie cwiczenie) {
        this.cwiczenie = cwiczenie;
    }

    public int getCzasTrwaniaCwiczenia() {
        return czasTrwaniaCwiczenia;
    }

    public void setCzasTrwaniaCwiczenia(int czasTrwaniaCwiczenia) {
        this.czasTrwaniaCwiczenia = czasTrwaniaCwiczenia;
    }

    public List<HistoriaTreningu> getHistoriaTreningu() {
        return historiaTreningu;
    }

    public void setHistoriaTreningu(List<HistoriaTreningu> historiaTreningu) {
        this.historiaTreningu = historiaTreningu;
    }

    @Override
    public String toString() {
        return "HistoriaCwiczen{" +
                "historiacwiczen_id=" + historiacwiczen_id +
                ", cwiczenie=" + cwiczenie +
                ", czasTrwaniaCwiczenia=" + czasTrwaniaCwiczenia +
                ", jednoCwiczenie=" + jednoCwiczenie +
                ", historiaTreningu=" + historiaTreningu +
                '}';
    }
}

package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ekwipunek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ekwipunek_id;
    private String ekwipunek;

    @ManyToMany(mappedBy = "ekwipunek")
    private List<Cwiczenie> cwiczenie;

    public long getEkwipunek_id() {
        return ekwipunek_id;
    }

    public void setEkwipunek_id(long ekwipunek_id) {
        this.ekwipunek_id = ekwipunek_id;
    }

    public void setCwiczenie(List<Cwiczenie> cwiczenie) {
        this.cwiczenie = cwiczenie;
    }



    public String getEkwipunek() {
        return ekwipunek;
    }

    public void setEkwipunek(String ekwipunek) {
        this.ekwipunek = ekwipunek;
    }
}

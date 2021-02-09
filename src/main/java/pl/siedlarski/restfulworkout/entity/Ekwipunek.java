package pl.siedlarski.restfulworkout.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ekwipunek {
    @Id
    private long ekwipunek_id;
    private String ekwipunek;

    public void setEkwipunek_id(long ekwipunek_id) {
        this.ekwipunek_id = ekwipunek_id;
    }
    @JsonIgnore
    @ManyToMany(mappedBy = "ekwipunek")
    private List<Cwiczenie> cwiczenie;

    public long getEkwipunek_id() {
        return ekwipunek_id;
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

    public List<Cwiczenie> getCwiczenie() {
        return cwiczenie;
    }
}

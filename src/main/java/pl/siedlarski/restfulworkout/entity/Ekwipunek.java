package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;

@Entity
public class Ekwipunek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ekwipunek;
    @ManyToOne( optional = false)
    @JoinColumn(name="id", nullable=false)
    private Cwiczenie cwiczenie;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEkwipunek() {
        return ekwipunek;
    }

    public void setEkwipunek(String ekwipunek) {
        this.ekwipunek = ekwipunek;
    }
}

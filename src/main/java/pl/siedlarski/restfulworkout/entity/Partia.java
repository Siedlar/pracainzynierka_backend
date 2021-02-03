package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;

@Entity
public class Partia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long partia_id;
    private String partia;

    public long getPartia_id() {
        return partia_id;
    }

    public void setPartia_id(long partia_id) {
        this.partia_id = partia_id;
    }

    public String getPartia() {
        return partia;
    }

    public void setPartia(String partia) {
        this.partia = partia;
    }
}

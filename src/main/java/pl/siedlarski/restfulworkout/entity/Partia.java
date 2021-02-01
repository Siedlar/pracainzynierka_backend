package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;

@Entity
public class Partia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String partia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPartia() {
        return partia;
    }

    public void setPartia(String partia) {
        this.partia = partia;
    }
}

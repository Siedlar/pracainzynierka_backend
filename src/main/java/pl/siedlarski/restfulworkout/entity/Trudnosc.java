package pl.siedlarski.restfulworkout.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Trudnosc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trudnosc_id;
    private String trudnosc;

    public long getTrudnosc_id() {
        return trudnosc_id;
    }

    public void setTrudnosc_id(long trudnosc_id) {
        this.trudnosc_id = trudnosc_id;
    }

    public String getTrudnosc() {
        return trudnosc;
    }

    public void setTrudnosc(String trudnosc) {
        this.trudnosc = trudnosc;
    }

    @Override
    public String toString() {
        return "Trudnosc{" +
                "trudnosc_id=" + trudnosc_id +
                ", trudnosc='" + trudnosc + '\'' +
                '}';
    }
}
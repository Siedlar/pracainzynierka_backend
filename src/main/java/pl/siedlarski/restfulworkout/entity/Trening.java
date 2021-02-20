package pl.siedlarski.restfulworkout.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trening_id;
    @OneToOne
    @JoinColumn(name="typtreningu_id")
    private TypTreningu typTreningu;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "trening_id"),
            inverseJoinColumns = @JoinColumn(name = "cwiczenie_id"))
    private List<Cwiczenie> cwiczenie;

    public long getTrening_id() {
        return trening_id;
    }

    public void setTrening_id(long trening_id) {
        this.trening_id = trening_id;
    }

    public TypTreningu getTypTreningu() {
        return typTreningu;
    }

    public void setTypTreningu(TypTreningu typTreningu) {
        this.typTreningu = typTreningu;
    }

    public List<Cwiczenie> getCwiczenie() {
        return cwiczenie;
    }

    public void setCwiczenie(List<Cwiczenie> cwiczenie) {
        this.cwiczenie = cwiczenie;
    }
}
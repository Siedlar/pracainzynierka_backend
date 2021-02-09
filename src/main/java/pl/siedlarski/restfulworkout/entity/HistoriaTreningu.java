package pl.siedlarski.restfulworkout.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class HistoriaTreningu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long historiatreningu_id;
    private LocalDate dataTreningu;
    private Integer czasTrwania;
    private String notatka;
    @OneToOne
    @JoinColumn(name="typtreningu_id")
    private TypTreningu typTreningu;
    @JsonIgnore
    @ManyToOne( optional = false)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy="historiaTreningu")
    private List<Rekord> rekord;

    public void setRekord(List<Rekord> rekord) {
        this.rekord = rekord;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = { @JoinColumn(name = "historiatreningu_id") },
            inverseJoinColumns = { @JoinColumn(name = "historiacwiczen_id") }
    )
    private List<HistoriaCwiczen> historiaCwiczen;

    public User getUser() {
        return user;
    }

    public List<HistoriaCwiczen> getHistoriaCwiczen() {
        return historiaCwiczen;
    }

    public void setHistoriaCwiczen(List<HistoriaCwiczen> historiaCwiczen) {
        this.historiaCwiczen = historiaCwiczen;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }


    public TypTreningu getTypTreningu() {
        return typTreningu;
    }

    public void setTypTreningu(TypTreningu typTreningu) {
        this.typTreningu = typTreningu;
    }

    public long getHistoriatreningu_id() {
        return historiatreningu_id;
    }

    public void setHistoriatreningu_id(long historiatreningu_id) {
        this.historiatreningu_id = historiatreningu_id;
    }

    public LocalDate getDataTreningu() {
        return dataTreningu;
    }

    public void setDataTreningu(LocalDate dataTreningu) {
        this.dataTreningu = dataTreningu;
    }

    public Integer getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(Integer czasTrwania) {
        this.czasTrwania = czasTrwania;
    }



    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "HistoriaTreningu{" +
                "historiatreningu_id=" + historiatreningu_id +
                ", dataTreningu=" + dataTreningu +
                ", czasTrwania=" + czasTrwania +
                ", notatka='" + notatka + '\'' +
                ", typTreningu=" + typTreningu +
                ", user=" + user +
                ", historiaCwiczen=" + historiaCwiczen +
                '}';
    }
}

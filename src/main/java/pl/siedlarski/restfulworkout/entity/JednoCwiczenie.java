package pl.siedlarski.restfulworkout.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class JednoCwiczenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jednocwiczenie_id;
    @Column(nullable = true)
    private Integer  seria;
    @Column(nullable = true)
    private Integer  powtorzenia;
    @Column(nullable = true)
    private Integer  czas;
    @Column(nullable = true)
    private Integer  kilometry;
    @Column(nullable = true)
    private Float  ciezar;




    @JsonIgnore
    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="historiacwiczen_id")
    private HistoriaCwiczen historiaCwiczen;


    public long getJednocwiczenie_id() {
        return jednocwiczenie_id;
    }

    public void setJednocwiczenie_id(long jednocwiczenie_id) {
        this.jednocwiczenie_id = jednocwiczenie_id;
    }

    public Integer getSeria() {
        return seria;
    }

    public void setSeria(Integer seria) {
        this.seria = seria;
    }

    public Integer getPowtorzenia() {
        return powtorzenia;
    }

    public void setPowtorzenia(Integer powtorzenia) {
        this.powtorzenia = powtorzenia;
    }

    public Integer getCzas() {
        return czas;
    }

    public void setCzas(Integer czas) {
        this.czas = czas;
    }

    public Integer getKilometry() {
        return kilometry;
    }

    public void setKilometry(Integer kilometry) {
        this.kilometry = kilometry;
    }



    public void setHistoriaCwiczen(HistoriaCwiczen historiaCwiczen) {
        this.historiaCwiczen = historiaCwiczen;
    }

    public Float getCiezar() {
        return ciezar;
    }

    public void setCiezar(Float ciezar) {
        this.ciezar = ciezar;
    }
}
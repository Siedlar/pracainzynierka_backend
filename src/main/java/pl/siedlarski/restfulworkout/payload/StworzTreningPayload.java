package pl.siedlarski.restfulworkout.payload;

import pl.siedlarski.restfulworkout.entity.Partia;
import pl.siedlarski.restfulworkout.entity.Trudnosc;
import pl.siedlarski.restfulworkout.entity.TypTreningu;

import java.util.List;

public class StworzTreningPayload {
    private TypTreningu typTreningu;
    private Trudnosc trudnosc;
    private Integer split;

    public TypTreningu getTypTreningu() {
        return typTreningu;
    }

    public void setTypTreningu(TypTreningu typTreningu) {
        this.typTreningu = typTreningu;
    }

    public Trudnosc getTrudnosc() {
        return trudnosc;
    }

    public void setTrudnosc(Trudnosc trudnosc) {
        this.trudnosc = trudnosc;
    }

    public Integer getSplit() {
        return split;
    }

    public void setSplit(Integer split) {
        this.split = split;
    }

    @Override
    public String toString() {
        return "StworzTreningPayload{" +
                "typTreningu=" + typTreningu +
                ", trudnosc=" + trudnosc +
                ", split=" + split +
                '}';
    }
}

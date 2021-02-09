package pl.siedlarski.restfulworkout.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypTreningu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typtreningu_id;
    private String typTreningu;

    public long getTyptreningu_id() {
        return typtreningu_id;
    }

    public void setTyptreningu_id(long typtreningu_id) {
        this.typtreningu_id = typtreningu_id;
    }

    public String getTypTreningu() {
        return typTreningu;
    }

    public void setTypTreningu(String typTreningu) {
        this.typTreningu = typTreningu;
    }

    @Override
    public String toString() {
        return "TypTreningu{" +
                "typtreningu_id=" + typtreningu_id +
                ", typTreningu='" + typTreningu + '\'' +
                '}';
    }
}

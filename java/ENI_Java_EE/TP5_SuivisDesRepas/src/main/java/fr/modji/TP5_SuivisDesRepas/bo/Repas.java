package fr.modji.TP5_SuivisDesRepas.bo;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

public class Repas {
    private int id;
    private String date;
    private String heure;
    private List<Aliments> aliments;

    public Repas(String date, String heure, List<Aliments> aliments) {
        this.date = date;
        this.heure = heure;
        this.aliments = aliments;
    }

    public Repas(int id, String date, String heure) {
        this.id = id;
        this.date = date;
        this.heure = heure;
    }

    public Repas(String date, String heure) {
        this.date = date;
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public List<Aliments> getAliments() {
        return aliments;
    }

    public void setAliments(List<Aliments> aliments) {
        this.aliments = aliments;
    }
}

package fr.modji.TP5_SuivisDesRepas.bll.bo;

import java.sql.Time;
import java.util.Date;

public class Repas {
    private int id;
    private Date date;
    private Time heure;

    public Repas(int id, Date date, Time heure) {
        this.id = id;
        this.date = date;
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getHeure() {
        return heure;
    }
}

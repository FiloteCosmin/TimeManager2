package com.example.filote_cosmin.timemanager.Model;

import java.util.Date;

/**
 * Created by Filote Cosmin on 7/5/2017.
 */

public class Proiect {

    private int id;
    private String nume;
    private int ore;
    private Date data;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Proiect(int id,String nume,Date data ,int ore) {
        this.nume = nume;
        this.ore = ore;
        this.id=id;
        this.data=data;
    }

    public Proiect(){
        this.nume="";
        this.ore=0;
    }
}

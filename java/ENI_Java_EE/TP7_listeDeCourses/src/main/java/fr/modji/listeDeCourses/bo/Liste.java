package fr.modji.listeDeCourses.bo;

import java.util.ArrayList;
import java.util.List;

public class Liste {
    private int id;
    private String nom;
    private List<Item> itemList;

    public Liste(int id, String nom, List<Item> itemList) {
        this.id = id;
        this.nom = nom;
        this.itemList = itemList;
    }

    public Liste(String nom, List<Item> itemList) {
        this.nom = nom;
        this.itemList = itemList;
    }

    public Liste(String nom) {
        this.nom = nom;
    }

    public void addItem(Item item){
        if (this.itemList == null){
            this.itemList = new ArrayList<>();
        }
        this.itemList.add(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}

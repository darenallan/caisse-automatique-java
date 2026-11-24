package org.example;

public class Article {

    // Attributs privés
    private int code_barre;
    private String designation;
    private double prix;
    private String horaire_scan;

    // Constructeur par défaut
    public Article() {
        this.code_barre = 0;
        this.designation = "";
        this.prix = 0.0;
        this.horaire_scan = "00-00-00";
    }

    // Getters
    public int getCode_barre() { return code_barre; }
    public String getDesignation() { return designation; }
    public double getPrix() { return prix; }
    public String getHoraire_scan() { return horaire_scan; }

    // Setters
    public void setCode_barre(int code_barre) { this.code_barre = code_barre; }
    public void setDesignation(String designation) { this.designation = designation; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setHoraire_scan(String horaire_scan) { this.horaire_scan = horaire_scan; }

    // Pour afficher un article sur le ticket

    public String afficher() {
        return designation + " - " + prix + " DH";
    }
}
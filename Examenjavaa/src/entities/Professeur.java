package entities;

import java.util.List;

public class Professeur {
    private int id_professeur;
    private String nom;
    private String prenom;
    private String telephone;
     List<Cours> cours;
    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public Professeur() {
    }

    public int getId_professeur() {
        return id_professeur;
    }

    public void setId_professeur(int id_professeur) {
        this.id_professeur = id_professeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

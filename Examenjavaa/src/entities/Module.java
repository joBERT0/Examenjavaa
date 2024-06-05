package entities;
import java.util.List;
public class Module {
    private int id_module;
    private String nom_module;
    List<Cours>cours;
    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public Module() {
    }

    public int getId_module() {
        return id_module;
    }

    public void setId_module(int id_module) {
        this.id_module = id_module;
    }

    public String getNom_module() {
        return nom_module;
    }

    public void setNom_module(String nom_module) {
        this.nom_module = nom_module;
    }
}

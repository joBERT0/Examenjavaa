package services;

import java.util.List;

import repositories.*;
import entities.*;
public class CoursService {
     CoursRepository coursRepository= new CoursRepository();

    public void ajouterClasse(Cours cours){
        coursRepository.insert(cours);
    }
    public List<Cours>listerCours(){
        return coursRepository.selectALL();
    }
   
}

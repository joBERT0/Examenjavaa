package services;
import repositories.ModuleRepository;
import java.util.List;
import entities.Module;
public class ModuleService {
   ModuleRepository moduleRepository= new ModuleRepository();

    public void ajouterClasse(Module module){
        moduleRepository.insert(module);
    }
    public List<Module>listerModule(){
        return moduleRepository.selectALL();
    }
    public Module findClasseByNom_module(String nom_module){
        return moduleRepository.selectProfesseurByNom_Module(nom_module);
    }
}

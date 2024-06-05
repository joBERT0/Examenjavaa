
import java.util.List;
import java.util.Scanner;
import entities.Module;
import services.ModuleService;
import entities.Cours;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class View {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc=new Scanner(System.in);
        ModuleService moduleService=new ModuleService();


        do {
            System.out.println("1-Ajouter un module ");
            System.out.println("2-Lister les modules");
            System.out.println("3-Créer un cours");
            System.out.println("4-Lister tous les cours");
            System.out.println("5-Lister les cours d'un module et d'un professeur");
            System.out.println("6-Quitter");
            choix=sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("Entrer le nom du module");
                    String nom_module=sc.nextLine();
                    Module module =new Module();
                    module= moduleService.findClasseByNom_module(nom_module);
                    if (module==null) {
                        System.out.println("le nom de module n'existe pas ");
                        System.out.println("Entrez un nom de module");
                        nom_module=sc.nextLine();
                        module =new Module();
                        module.setNom_module(nom_module);
                        moduleService.ajouterClasse(module);
                    }else{
                        System.out.println("Le nom du modules existes déjà");
                    }
                    break;
                case 2:
                    System.out.println("Voici la liste des modules :");
                    List<Module> modules= moduleService.listerModule();
                    for (Module mo: modules){
                        System.out.println(mo.getId_module()+" :"+ mo.getNom_module());
                    }
                case 3:
                    Cours cours=new Cours();
                    System.out.println("Veuillez saisir la date du cours (format: AAAA-MM-JJ) :");
                    String dateInput = sc.nextLine();

                        try {
                            LocalDate date = LocalDate.parse(dateInput);
                            cours.setDate(date);
                            System.out.println("Date enregistrée : " + cours.getDate());
                        } catch (DateTimeParseException e) {
                            System.out.println("Format de date invalide. Veuillez utiliser le format AAAA-MM-JJ.");
                        }
                    sc.nextLine();
                    
                        System.out.println("Veuillez saisir l'heure de début du cours (format: HH:MM) :");
                        String startTimeInput = sc.nextLine();

                        try {
                            LocalTime startTime = LocalTime.parse(startTimeInput);
                            cours.setStartTime(startTime);
                            System.out.println("Heure de début enregistrée : " + cours.getStartTime());
                        } catch (DateTimeParseException e) {
                            System.out.println("Format de l'heure de début invalide. Veuillez utiliser le format HH:MM.");
                            return; // Sortir du programme si l'heure de début est invalide
                        }
                        System.out.println("Veuillez saisir l'heure de fin du cours (format: HH:MM) :");
                        String endTimeInput = sc.nextLine();

                        try {
                            LocalTime endTime = LocalTime.parse(endTimeInput);
                            if (startTimeInput.compareTo(endTimeInput) < 0) {
                                cours.setEndTime(endTime);
                                System.out.println("Heure de fin enregistrée : " + cours.getEndTime());
                            } else {
                                System.out.println("L'heure de fin doit être après l'heure de début.");
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Format de l'heure de fin invalide. Veuillez utiliser le format HH:MM.");
                        }
                          break;
                default:
                    break;
            }
        } while (choix!=6);
 }
} 


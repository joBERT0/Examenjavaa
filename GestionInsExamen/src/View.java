import java.util.Scanner;
import entities.Inscription;
import entities.Niveau;
import entities.Professeur;
import entities.ProfesseurClasse;
import services.EtudiantService;
import services.ClasseService;
import services.InscriptionService;
import services.ProfesseurService;
import entities.Etudiant;
import entities.Filiere;
import java.util.ArrayList;
import java.util.List;
import entities.Classe;
public class View {
    public static void main(String[] args) throws Exception {
        int choix;
        Scanner sc=new Scanner(System.in);
        EtudiantService etudiantService=new EtudiantService();
        ClasseService classeService=new ClasseService();
        InscriptionService inscriptionService= new InscriptionService();
        ProfesseurService ProfesseurService =new ProfesseurService();
        do {
            System.out.println("1-Ajouter des classes");
            System.out.println("2-Lister des classes");
            System.out.println("3-Ajouter des professeurs et leur affecter des classes");
            System.out.println("4-Lister les professeurs");
            System.out.println("5-Faire une inscription ou une réinscription");
            System.out.println("6-Lister les étudiants inscrits dans une classe");
            System.out.println("7-Quitter");
             choix=sc.nextInt();
             sc.nextLine();
             switch (choix) {
                 case 1:
                    int choixNiv;
                    do {
                        System.out.println("Choisissez un niveau 1-L1; 2-L2; 3-L3) ");
                        choixNiv= sc.nextInt();
                    } while (choixNiv<1 || choixNiv>3);
                    Niveau niveau = Niveau.values()[choixNiv - 1];

                    int choixFil;
                    do {
                        System.out.println("Choisissez une filière 1-GLRS 2-IAGE 3-ETSE 4-TTL 5-MAIE;");
                        choixFil = sc.nextInt();
                    } while (choixFil<1 || choixFil>7);
                    Filiere filiere = Filiere.values()[choixFil - 1];

                    Classe classe= new Classe();
                    classe.setNiveau(niveau);
                    classe.setFiliere(filiere);
                    classeService.ajouterClasse(classe);
                        break;
                case 2:
                    System.out.println("Voici nos classes :");
                    List<Classe> classes= classeService.listerClasse();
                    for(Classe cl: classes){
                        System.out.println(cl.getNiveau()+ "" + cl.getFiliere());
                    
                    }
                    break;
                case 3:
                    Professeur professeur=new Professeur();
                    System.out.println("Veuillez entrer le nci");
                    professeur.setNci(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Veuillez entrer le nom Complet");
                    professeur.setNomComplet(sc.nextLine());
                    System.out.println("Veuillez entrer le Grade");
                    professeur.setGrade(sc.nextLine());
                        classes = classeService.listerClasse();
                        int response=2;
                        List<ProfesseurClasse> ListeClasseProf = new ArrayList<>();
                        do {
                            for(Classe cl: classes){
                                System.out.println(cl.getNiveau()+ "" + cl.getFiliere());
                            }
                            System.out.println("veuillez selectionner la classe à laquelle vous vous voulez affecetr un professeur");
                            int id_Classe=sc.nextInt();
                            classe=classeService.findClasseById(id_Classe);
                            if(classe!=null){
                                ProfesseurClasse professeurClasse=new ProfesseurClasse();
                                professeurClasse.setProfesseur(professeur);
                                    int inside=0;
                                if(ListeClasseProf.size()>0){
                                    for (ProfesseurClasse p: ListeClasseProf){
                                        if(p.getClasse().getId_classe()==classe.getId_classe()){
                                            inside = 1;
                                            System.out.println("Classe déjà donnée au professeur");
                                        }
                                    }
                                    if(inside==0){
                                        ListeClasseProf.add(professeurClasse);

                                    }else{
                                        ListeClasseProf.add(professeurClasse);
                                    }
                                }
                            }else{
                                System.out.println("Cet Id n'existe pas ");
                            }
                            System.out.println("Voulez-vous continuer 1-OUI 2-NON");
                            response=sc.nextInt();
                        } while (response==1);
                        if(professeur.getProfesseurClasses().size()<1){
                            System.out.println("le professeur doit avoir au moins une classe");
                        }else{
                            ProfesseurService.ajouterProfesseur(professeur);
                        }
                break;
                case 4:
                    System.out.println("Voici la liste des professeurs :");
                    List<Professeur> professeurs= ProfesseurService.listerProfesseurs();
                    for (Professeur pc: professeurs){
                        System.out.println(pc.getGrade() +" :"+ pc.getNomComplet());
                    }
                break;
                case 5:
                    System.out.println("Entrer le matricule de l'etudiant");
                    String matricule=sc.nextLine();
                    Etudiant etudiant =new Etudiant();
                    etudiant= etudiantService.rechercherEtudiantParMatricule(matricule);
                    if (etudiant==null) {
                        System.out.println("Etudiant n'existe pas.Veuillez l'inscrire");
                        System.out.println("Entrer le matricule de l'etudiant");
                        matricule=sc.nextLine();
                        System.out.println("Veuillez entrer le nom complet de l'etudiant");
                        String nomComplet=sc.nextLine();
                        System.out.println("Veuillez entrer le tuteur de l'etudiant");
                        String tuteur=sc.nextLine();
                        etudiant =new Etudiant();
                        etudiant.setMatricule(matricule);
                        etudiant.setNomComplet(nomComplet);
                        etudiant.setTuteur(tuteur);
                        etudiantService.insertEtudiant(etudiant);
                    }else{
                        System.out.println("La matricule existe deja.Reinscrivez l'etudiant");
                    }
                    Inscription inscription=new Inscription();
                    System.out.println("Veuillez choisir l'année");
                    String anneeScolaire=sc.nextLine();
                    System.out.println("Les classes sont les suivantes");
                    List <Classe> classes2= classeService.listerClasse();
                    for(Classe cl:classes2){
                        System.out.println(cl.getId_classe()+""+cl.getNiveau()+""+cl.getFiliere());
                    }
                    int id_Classe=sc.nextInt();
                    Classe classe2=new Classe();
                    classe2=classeService.findClasseById(id_Classe);
                    inscription.setClasse(classe2);
                    inscription.setAnneScolaire(anneeScolaire);
                    inscription.setEtudiant(etudiant);
                    inscriptionService.faireInscription(inscription);
                break;

                case 6:
                    System.out.println("Saisir l'année d'inscription");
                    anneeScolaire=sc.nextLine();
                    List<Inscription> inscriptions=inscriptionService.listerInscriptionParAnnee(anneeScolaire);
                    for (Inscription inscript : inscriptions) {
                        System.out.println("Matricule: "+inscript.getEtudiant().getMatricule()+"\n Nom complet : "+inscript.getEtudiant().getNomComplet()+"\n Tuteur : "+inscript.getEtudiant().getNomComplet());
                        
                    }
                    System.out.println("Voulez vous filtrez cette liste par classe oui/non");
                    String reponse=sc.nextLine();
                    if(reponse.equalsIgnoreCase("oui")){
                        System.out.println("les classes sont les suivantes :");
                        classes= classeService.listerClasse();
                        for(Classe cl: classes){
                            System.out.println(cl.getId_classe()+"-"+cl.getNiveau()+""+ cl.getFiliere());
                        }
                        id_Classe=sc.nextInt();
                        inscriptions= inscriptionService.listerInscriptionParAnnee(anneeScolaire,id_Classe);
                        for (Inscription inscript : inscriptions) {
                            System.out.println("Matricule: "+inscript.getEtudiant().getMatricule()+"\n Nom complet : "+inscript.getEtudiant().getNomComplet()+"\n Tuteur : "+inscript.getEtudiant().getNomComplet());
                            
                        }

                    }
                break;
             
                default:
                    break;
             }

        } while (choix!=7);
    
    }
}

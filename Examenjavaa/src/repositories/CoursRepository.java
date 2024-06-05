package repositories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import repositories.CoursRepository;
import entities.Cours;
public class CoursRepository extends Database{
    private final String SQL_INSERT= "INSERT INTO `cours` ( `date`,`heure_debut`,`heure_fin`,`id_module`, `id_professeur`) VALUES ( ?, ?)";
    private final String SQL_SELECT= "SELECT * from `cours`";
     public void insert(Cours cours){
        try {
            openConnexion();
            initPreparedStatement(SQL_INSERT);
            statement.setDate(1,Date.valueOf(cours.getDate()));
            statement.setTime(2,Time.valueOf(cours.getHeure_debut()));
            statement.setTime(2,Time.valueOf(cours.getHeure_debut()));
            statement.setDouble(2, cours.getModule().getId_module());
            statement.setDouble(3, cours.getProfesseur().getId_professeur());
             executeUpdate();
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
 }
  public List<Cours> selectALL(){
        List <Cours> courss=new ArrayList<>();
        try{
            openConnexion();
            initPreparedStatement(SQL_SELECT);
            ResultSet rs=executeSelect();
            while(rs.next()){
                Cours cours=new Cours();
                cours.setDate(rs.getDate("date").toLocalDate());
                cours.setHeure_debut(rs.getTime("heure_debut").toLocalTime());
                cours.setHeure_debut(rs.getTime("heure_fin").toLocalTime());         
                
            }
            rs.close();
            closeConnexion();
        }
        catch(SQLException e){
            System.out.println("Erreur de connexion à la BD");
        }
        return courss;
      }
    
}

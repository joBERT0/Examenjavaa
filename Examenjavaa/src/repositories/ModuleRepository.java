package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Module;
public class ModuleRepository extends Database {
    private final String SQL_SELECT_ALL_PROFESSEUR="SELECT * FROM `module`";
    private final String SQL_INSERT="INSERT INTO `module`(`nom_module`) VALUES (?,?)";
    private final String SQL_SELECT_BY_MATRICULE="SELECT * FROM `module` where nom_module like ? ";

    public void insert(Module module){
        try {
            openConnexion();
            initPreparedStatement(SQL_INSERT);
             statement.setString(1,module.getNom_module());
             executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Module selectProfesseurByNom_Module(String nom_module){
        Module module= null;
        try{
           openConnexion();
           initPreparedStatement(SQL_SELECT_BY_MATRICULE);
            statement.setString(1,nom_module);
            ResultSet rs=executeSelect();
            if(rs.next()){
                 module=new Module();
                 module.setNom_module(rs.getString("nom_module"));

            }
            statement.close();
            rs.close();
            conn.close();

        }catch (SQLException e) {
            System.out.println("Erreur de connexion à la BD");
        }
        return module;
    }

      public List<Module> selectALL(){
        List <Module> modules=new ArrayList<>();
        try{
            openConnexion();
            initPreparedStatement(SQL_SELECT_ALL_PROFESSEUR);
            ResultSet rs=executeSelect();
            while(rs.next()){
                Module module= new Module();
                module.setNom_module(rs.getString("nom_module"));
                modules.add(module);
            }
            rs.close();
            closeConnexion();
        }
        catch(SQLException e){
            System.out.println("Erreur de connexion à la BD");
        }
        return modules;
      }
}

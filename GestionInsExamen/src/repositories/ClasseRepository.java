package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Classe;
import entities.Filiere;
import entities.Niveau;

public class ClasseRepository extends Database {
    private final String SQL_SELECT_CLASSE="SELECT * FROM `classe`";
    private final String SQL_INSERT_CLASSE="INSERT INTO `classe`(`id_classe`,`niveau`,`filiere`) VALUES (?,?)";
    private final String SQL_SELECT_BY_ID_CLASSE="SELECT * FROM `classe` where `id_classe` like ? ";

    public List <Classe> selectAll(){
        List<Classe> classes = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            openConnexion();
            initPreparedStatement(SQL_SELECT_CLASSE);
            rs = executeSelect();
        
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setId_classe(rs.getInt("id_classe"));
                int niveau = rs.getInt("niveau");
                int filiere = rs.getInt("filiere");
                classe.setNiveau(Niveau.values()[niveau]);
                classe.setFiliere(Filiere.values()[filiere]);
                classes.add(classe);
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la BD: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture du ResultSet: " + e.getMessage());
                }
            }
            closeConnexion();
        }
        
        return classes;
        
    }
    public void insertClasse(Classe classe) {
        try {
            openConnexion();
            initPreparedStatement(SQL_INSERT_CLASSE);
            statement.setInt(1, classe.getId_classe());
            statement.setObject(2, classe.getNiveau());
            statement.setObject(3, classe.getFiliere());
            executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public Classe selectClasseById(int id_classe) {
        Classe classe = null;
        ResultSet rs = null;
        
        try {
            openConnexion();
            initPreparedStatement(SQL_SELECT_BY_ID_CLASSE);
            statement.setInt(1, id_classe);
            rs = executeSelect();
            
            if (rs.next()) {
                classe = new Classe();
                classe.setId_classe(rs.getInt("id_classe"));
                int niveau = rs.getInt("niveau");
                int filiere = rs.getInt("filiere");
                classe.setNiveau(Niveau.values()[niveau]);
                classe.setFiliere(Filiere.values()[filiere]);
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la BD: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture du ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture du PreparedStatement: " + e.getMessage());
                }
            }
            closeConnexion();
        }
        
        return classe;
    }
    
}

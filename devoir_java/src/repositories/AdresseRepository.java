package repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import core.Database;
import entities.Adresse;
import entities.Client;

public class AdresseRepository extends Database {
    private final String SQL_INSERT = "INSERT INTO `adresse` (`ville`, `quartier`, `num_villa`, `id_client`) VALUES (?, ?, ?, ?)";
    private final String SQL_SELECT_ALL = "SELECT a.*, c.nom_complet_client FROM adresse a INNER JOIN client c ON a.id_client = c.id_client";
    
    // Insérer une nouvelle adresse dans la base de données
    public void insert(Adresse adresse) {
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, adresse.getVille());
            statement.setString(2, adresse.getQuartier());
            statement.setString(3, adresse.getNumVilla());
            statement.setInt(4, adresse.getClient().getId());
            executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'adresse: " + e.getMessage());
        }
    }
    
    // Sélectionner toutes les adresses avec le nom du client associé
    public ArrayList<Adresse> selectAllWithClientName() {
        ArrayList<Adresse> adresses = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Adresse adresse = new Adresse();
                Client client = new Client();
                
                adresse.setId(rs.getInt("id_adresse"));
                adresse.setVille(rs.getString("ville"));
                adresse.setQuartier(rs.getString("quartier"));
                adresse.setNumVilla(rs.getString("num_villa"));
                
                // Informations du client associé
                client.setId(rs.getInt("id_client"));
                client.setNomComplet(rs.getString("nom_complet_client"));
                adresse.setClient(client);
                
                adresses.add(adresse);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des adresses: " + e.getMessage());
        }
        return adresses;
    }
    
    // Vous pouvez ajouter d'autres méthodes ici si nécessaire
    
}

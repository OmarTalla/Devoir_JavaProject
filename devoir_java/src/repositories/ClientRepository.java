package repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import core.Database;
import entities.Client;

public class ClientRepository extends Database {
    private final String SQL_SELECT_ALL="select * from client  ";
    private final String SQL_SELECT_BY_TEL="select * from client where telephone_client like ?";
    private final String SQL_INSERT="INSERT INTO `client` (`nom_complet_client`, `telephone_client`, `email_client`) VALUES (?,?,?) ";
 
   
      public void insert(Client client){
         
          try {
             ouvrirConnexion();
             initPrepareStatement(SQL_INSERT);
             statement.setString(1, client.getNomComplet());
             statement.setString(2, client.getTelephone());
             statement.setString(3, client.getEmail());
              executeUpdate();
             fermerConnexion();
          } catch (Exception e) 
           {
            System.out.println("Erreur de chargement du Driver");
           }
          }




      public ArrayList<Client>  selectAll(){
        ArrayList<Client> clients=new ArrayList<>();
    try {
        ouvrirConnexion();
         initPrepareStatement(SQL_SELECT_ALL);
         ResultSet rs= executeSelect();
         while (rs.next()) {
             //rs une ligne de la requete ==> a une agence
             Client client=new Client();
             client.setId(rs.getInt("id_client"));
             client.setNomComplet(rs.getString("nom_complet_client"));
             client.setTelephone(rs.getString("telephone_client"));
             client.setEmail(rs.getString("email_client"));
             clients.add(client);
         }
         rs.close();
        fermerConnexion();
    } catch (Exception e) {
         System.out.println("Erreur de chargement du Driver");
    }
       return clients;
     }

     public Client selectByTelephone(String telephone){
     
      Client client=null;
  try {
         ouvrirConnexion();
        initPrepareStatement(SQL_SELECT_BY_TEL);
        statement.setString(1, telephone);
        ResultSet rs= executeSelect();
       if (rs.next()) {
           //rs une ligne de la requete ==> a une agence
            client=new Client();
            client.setId(rs.getInt("id_client"));
            client.setNomComplet(rs.getString("nom_complet_client"));
            client.setTelephone(rs.getString("telephone_client"));
            client.setEmail(rs.getString("email_client"));
       }
       rs.close();
       conn.close();
  } catch (Exception e) {
       System.out.println("Erreur de chargement du Driver");
  }
     return client;
   }
}

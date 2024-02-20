import java.util.List;
import java.util.Scanner;

import entities.Client;
import entities.Adresse;
import services.ClientService;
import services.AdresseService;

public class View {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        AdresseService adresseService = new AdresseService();

        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n========= Menu =========");
            System.out.println("1. Créer un Client");
            System.out.println("2. Lister les Clients");
            System.out.println("3. Ajouter une adresse et lui associer un client");
            System.out.println("4. Lister les adresses en affichant le nom du client");
            System.out.println("5. Quitter");
            System.out.print("Sélectionnez une option: ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le reste de la ligne après le nombre

            switch (choix) {
                case 1:
                    Client client;
                    System.out.println("Entrer le numero de Telephone");
                    String telephone=scanner.nextLine();   
                    client =clientService. rechercherClientParTelephone(telephone);
                    if(client==null){
                        client=new Client();
                        client.setTelephone(telephone);
                        System.out.println("Entrer le Nom et Prenom");
                        client.setNomComplet(scanner.nextLine());
                        System.out.println("Entrer Email");
                        client.setEmail(scanner.nextLine());
                        clientService.addClient(client);
                }else{
                  System.out.println("Ce numero telephone existe deja");
                }
                    break;
                case 2:
                    List<Client> clients = clientService.listerClients();
                    for (Client cl: clients) {
                        System.out.println("Nom et Prenom: "+ cl.getNomComplet());
                        System.out.println("Telephone : "+ cl.getTelephone());
                        System.out.println("Email : "+ cl.getEmail());
                        System.out.println("==================================");
              }
                    break;
                    case 3: // Ajouter une adresse et l'associer à un client
                        System.out.println("Entrer le numéro de téléphone du client pour associer l'adresse:");
                        String tel = scanner.nextLine(); // Correction ici pour utiliser la bonne variable
                        Client clientAssocie = clientService.rechercherClientParTelephone(tel);
                        if (clientAssocie != null) {
                            Adresse adresse = new Adresse();
                            System.out.println("Entrer la ville:");
                            adresse.setVille(scanner.nextLine());
                            System.out.println("Entrer le quartier:");  
                            adresse.setQuartier(scanner.nextLine());
                            System.out.println("Entrer le numéro de la villa:");
                            adresse.setNumVilla(scanner.nextLine());
                        
                        
                        adresse.setClient(clientAssocie); 
                        adresseService.addAdresse(adresse, tel); 
                
                        System.out.println("Adresse ajoutée et associée au client avec succès!");
                    } else {
                        System.out.println("Aucun client trouvé avec ce numéro de téléphone.");
                    }
                    break;
                
                case 4: // Lister les adresses en affichant le nom du client
                    List<Adresse> adresses = adresseService.listerAdresses();
                    if (!adresses.isEmpty()) {
                        for (Adresse adr : adresses) {
                            Client cl = adr.getClient();
                            System.out.println("Adresse ID: " + adr.getId() + ", Ville: " + adr.getVille() + ", Quartier: " + adr.getQuartier() + ", Numéro de Villa: " + adr.getNumVilla());
                            if (cl != null) {
                                System.out.println("Nom du Client: " + cl.getNomComplet());
                            } else {
                                System.out.println("Cette adresse n'est associée à aucun client.");
                            }
                            System.out.println("-----------------------------------");
                        }
                    } else {
                        System.out.println("Il n'y a pas d'adresses à afficher.");
                    }
                    break;
                case 5:
                    System.out.println("Vous avez quitté le programme.");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        } while (choix != 5);

        scanner.close();
    }
}

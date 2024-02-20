package services;

import java.util.List;
import entities.Adresse;
import entities.Client;
import repositories.AdresseRepository;
import repositories.ClientRepository;

public class AdresseService {
    private AdresseRepository adresseRepository = new AdresseRepository();
    private ClientRepository clientRepository = new ClientRepository();

    public void addAdresse(Adresse adresse, String telephone) {
        adresseRepository.insert(adresse);
    }

    public List<Adresse> listerAdresses() {
        return adresseRepository.selectAllWithClientName();
    }


    public Client trouverClientParTelephone(String telephone) {
        return clientRepository.selectByTelephone(telephone);
    }
}
    
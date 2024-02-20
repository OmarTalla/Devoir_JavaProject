package entities;

public class Adresse {
    private int id;
    private String ville;
    private String quartier;
    private String numVilla;
    private Client client; // Association to Client
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getQuartier() {
        return quartier;
    }
    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }
    public String getNumVilla() {
        return numVilla;
    }
    public void setNumVilla(String numVilla) {
        this.numVilla = numVilla;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}

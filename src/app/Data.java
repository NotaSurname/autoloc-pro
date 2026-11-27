package app;

import clients.Client;
import clients.Entreprise;
import clients.Particulier;
import location.AgenceLocation;
import vehicules.*;

import java.util.ArrayList;

public class Data {
    private AgenceLocation agence;

    public Data() {
        this.agence = new AgenceLocation(50, 50, 100);
    }

    public AgenceLocation getAgence() {
        return agence;
    }

    public void load() {
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Particulier("Dupont", "Marie", "PERM001", "0601020304", 1995, true));
        clients.add(new Entreprise("Martin", "Paul", "PERM002", "0605060708", 1980,
                "TransExpress SARL", "12345678900012"));

        Voiture clio = new Voiture("AB-123-CD", "Renault", "Clio", 45.0, 5, "Essence", false);
        clio.setKilometrage(3500);

        Voiture golf = new Voiture("EF-456-GH", "Volkswagen", "Golf", 55.0, 5, "Diesel", true);
        golf.setKilometrage(12500);

        Moto s1000rr = new Moto("IJ-789-KL", "BMW", "S1000RR", 60.0, 999);
        s1000rr.setKilometrage(2000);

        Utilitaire master = new Utilitaire("MN-012-OP", "Renault", "Master", 80.0, 12.0, 1500.0);
        master.setKilometrage(7000);

        VehiculeLuxe porsche = new VehiculeLuxe("QR-345-ST", "Porsche", "GT3 RS", 250.0, 2, "Essence",
                true, false, new String[]{"GPS", "Sièges chauffants", "Pack circuit"});
        porsche.setKilometrage(1500);

        ArrayList<Vehicule> flotte = new ArrayList<>();
        flotte.add(clio);
        flotte.add(golf);
        flotte.add(s1000rr);
        flotte.add(master);
        flotte.add(porsche);

        for (Vehicule v : flotte) {
            agence.ajouterVehicule(v);
        }
        for (Client c : clients) {
            agence.ajouterClient(c);
        }

        System.out.println();
        System.out.println("--- Véhicules ---");
        for (int i = 0; i < flotte.size(); i++) {
            System.out.println((i + 1) + ". " + flotte.get(i));
        }

        System.out.println();
        System.out.println("--- Clients ---");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i));
        }
    }
}

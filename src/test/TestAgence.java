package test;

import clients.*;
import location.*;
import vehicules.*;

public class TestAgence {
    public static void main(String[] args) {
        AgenceLocation agence = new AgenceLocation(10, 10, 10);

        System.out.println("\n--- Ajout des véhicules ---");
        agence.ajouterVehicule(new Voiture("AA-111-BB","Peugeot","308",45.0,5,"Essence",false));
        agence.ajouterVehicule(new Moto("AA-222-BB","Yamaha","MT-07",30.0,689));
        agence.ajouterVehicule(new Utilitaire("AA-333-BB","Renault","Master",60.0,12.0,1200));
        agence.ajouterVehicule(new VehiculeLuxe("AA-444-BB","Mercedes","Classe S",250.0,4,"Essence",true,
                true, new String[]{"WiFi", "Bar"}));
        agence.ajouterVehicule(new Voiture("AA-111-BB","Renault","Clio",40.0,5,"Diesel",false)); // doublon refusé

        System.out.println("\n--- Ajout des clients ---");
        agence.ajouterClient(new Particulier("Dupont","Jean","P001","0601020304",1990,true));
        agence.ajouterClient(new Entreprise("Martin","Paul","P002","0605060708",1985,"TransCo","12345678900011"));
        agence.ajouterClient(new Particulier("Durand","Marie","P001","0611111111",1995,false)); // doublon refusé

        agence.afficherFlotte();

        System.out.println("\n--- Locations ---");
        Client jean = agence.rechercherClientParPermis("P001");
        Client paul = agence.rechercherClientParPermis("P002");
        ContratLocation c1 = agence.louerVehicule(jean, agence.rechercherParImmatriculation("AA-111-BB"), 10, 3);
        ContratLocation c2 = agence.louerVehicule(paul, agence.rechercherParImmatriculation("AA-333-BB"), 12, 5);
        agence.louerVehicule(paul, agence.rechercherParImmatriculation("AA-111-BB"), 15, 2); // déjà loué
        System.out.println(c1);
        System.out.println(c2);

        agence.afficherVehiculesDisponibles();

        agence.afficherStatistiques();

        System.out.println("\n--- Retour des véhicules ---");
        agence.retournerVehicule("AA-111-BB");
        agence.retournerVehicule("AA-333-BB");
        agence.retournerVehicule("AA-222-BB");

        agence.afficherStatistiques();
        agence.compterVehiculesParCategorie();

        agence.rechercherParImmatriculation("AA-222-BB").setKilometrage(12000);
        agence.afficherVehiculesAEntretenir();
        agence.effectuerEntretien("AA-222-BB");

        agence.afficherCatalogueParCategorie();

        agence.afficherHistoriqueClient("P002");
    }
}

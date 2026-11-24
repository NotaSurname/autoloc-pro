package test;

import vehicules.Vehicule;

public class TestVehicules {
    public static void main(String[] args) {
        System.out.println("--- Tableau de 3 véhicules ---");
        Vehicule[] tolle = {
                new Vehicule("AB-123-CD", "Renault", "Clio", 35.0),
                new Vehicule("AB-123-CD", "Porsche", "GT3RS", 45.0),
                new Vehicule("EF-456-GH", "Lamborghini", "Urus", 75.0),
        };

        for (Vehicule v : tolle) {
            System.out.println(v);
        }

        Vehicule v1 = tolle[0];
        Vehicule v2 = tolle[1];
        Vehicule v3 = tolle[2];

        System.out.println("v1 == v2 : " + (v1 == v2));
        System.out.println("v1.equals(v2) : " + v1.equals(v2));
        System.out.println("Même hashCode : " + (v1.hashCode() == v2.hashCode()));

        System.out.println("v1.equals(v3) : " + v1.equals(v3));
        System.out.println("v1.equals(null) : " + v1.equals(null));

        v1.setMarque("Peugeot").setModele("308").setKilometrage(1500).setTarifJournalier(40.0).setDisponible(false);
        System.out.println("Immatriculation : " + v1.getImmatriculation());
        System.out.println("Marque : " + v1.getMarque());
        System.out.println("Modèle : " + v1.getModele());
        System.out.println("Kilométrage : " + v1.getKilometrage());
        System.out.println(v1);

        v1.setKilometrage(-100);
        v1.setTarifJournalier(-10.0);

        Vehicule defaut = new Vehicule();
        System.out.println(defaut);
        Vehicule copie = new Vehicule(v1);
        System.out.println(copie);
        System.out.println("copie.equals(v1) : " + copie.equals(v1));
        Vehicule copieNull = new Vehicule(null);
        System.out.println(copieNull);
        System.out.println("Compteur : " + Vehicule.getCompteurVehicules());
    }
}

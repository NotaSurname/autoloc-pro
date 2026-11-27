package test;

import vehicules.*;

public class TestVehicules {
    public static void main(String[] args) {
        System.out.println("--- Tableau de 3 véhicules ---");
        Vehicule[] tolle = {
                new Voiture("AB-123-CD", "Renault", "Clio", 35.0, 5, "Essence", false),
                new Voiture("AB-123-CD", "Porsche", "GT3RS", 45.0, 2, "Essence", true),
                new Voiture("EF-456-GH", "Lamborghini", "Urus", 75.0, 5, "Essence", true),
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

        v1.setMarque("Peugeot").setModele("308").setKilometrage(1500).setTarifJournalier(40.0).setEtat(EtatVehicule.LOUE);
        System.out.println("Immatriculation : " + v1.getImmatriculation());
        System.out.println("Marque : " + v1.getMarque());
        System.out.println("Modèle : " + v1.getModele());
        System.out.println("Kilométrage : " + v1.getKilometrage());
        System.out.println(v1);

        v1.setKilometrage(-100);
        v1.setTarifJournalier(-10.0);

        Vehicule defaut = new Voiture();
        System.out.println(defaut);
        Vehicule copie = new Voiture((Voiture) v1);
        System.out.println(copie);
        System.out.println("copie.equals(v1) : " + copie.equals(v1));
        Vehicule copieNull = new Voiture(null);
        System.out.println(copieNull);
        System.out.println("Compteur : " + Vehicule.getCompteurVehicules());

        System.out.println("\n\n\n");
        Vehicule[] flotte = {
                new Voiture("AA-111-BB","Peugeot","308",45.0,5,"Essence",false),
                new Moto("AA-222-BB","Yamaha","MT-07",30.0,689),
                new Utilitaire("AA-333-BB","Renault","Master",60.0,12.0,1200),
                new VehiculeLuxe("AA-444-BB","Mercedes","Classe S",250.0,4,"Essence",true,
                        true, new String[]{"WiFi","Bar"})
        };
        for (Vehicule v : flotte) {
            System.out.println(v);
            if (v instanceof Moto) {
                Moto m = (Moto) v;
                System.out.println("Permis A2/A requis : " + m.necessitePermisA2OuA());
            }
        }
    }
}

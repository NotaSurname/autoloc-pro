package test;

import interfaces.Maintenable;
import vehicules.*;

public class TestVehicules {
    public static void testVehicule() {
        Vehicule[] flotte = {
                new Voiture("AA-111-BB","Peugeot","308",45.0,5,"Essence",false),
                new Moto("AA-222-BB","Yamaha","MT-07",30.0,689),
                new Utilitaire("AA-333-BB","Renault","Master",60.0,12.0,1200),
                new VehiculeLuxe("AA-444-BB","Mercedes","Classe S",250.0,4,"Essence",true,
                        true, new String[]{"WiFi","Bar"})
        };

        System.out.println("\n--- Véhicules nécessitant un entretien ---");
        flotte[0].setKilometrage(12000);
        flotte[2].setKilometrage(15000);
        flotte[3].setKilometrage(20000);
        for (Vehicule v : flotte) {
            if (v instanceof Maintenable) {
                Maintenable m = (Maintenable) v;
                if (m.necessiteEntretien()) {
                    System.out.println("Entretien requis : " + v);
                    m.effectuerEntretien();
                }
            }
        }
    }
    public static void testAll() {
        testVehicule();
    }
}

package test;

import vehicules.Vehicule;

import java.util.Scanner;

public class TestVehicules {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Marque : ");
        String marque = scanner.nextLine();
        System.out.println("Modèle : ");
        String modele = scanner.nextLine();

        Vehicule v1 = new Vehicule();
        v1.afficherInfos();
        Vehicule v2 = new Vehicule("AB-123-CD", marque, modele, 35.0);
        v2.afficherInfos();
        v2.rouler(250);
        System.out.print("Après 250 km : ");
        v2.afficherInfos();
        Vehicule v3 = new Vehicule(v2);
        v3.afficherInfos();

        scanner.close();
    }
}

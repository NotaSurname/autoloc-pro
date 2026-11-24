package test;

import clients.Client;

import java.time.Year;

public class TestClients {
    public static void main(String[] args) {
        Client c1 = new Client("Dupont", "Jean", "019837", "0707070707", 2007);
        c1.afficherInfos();
        System.out.println("Age : " + c1.calculerAge(Year.now().getValue()));
        Client c2 = new Client(c1);
        c2.afficherInfos();
        System.out.println("Age : " + c2.calculerAge(Year.now().getValue()));
        Client c3 = new Client();
        Client c4 = new Client("Martin", "Luc", "019837", "0606060606", 1990);
        System.out.println("Compteur : " + Client.getCompteurClients());

        Client[] clients = {c1, c2, c3, c4};
        for (Client c : clients) {
            System.out.println(c);
        }

        System.out.println("c1 == c4 : " + (c1 == c4));
        System.out.println("c1.equals(c4) : " + c1.equals(c4));
        System.out.println("Même hashCode : " + (c1.hashCode() == c4.hashCode()));
        System.out.println("c1.equals(c2) : " + c1.equals(c2));

        System.out.println("c1.equals(c3) : " + c1.equals(c3));
        System.out.println("c1.equals(null) : " + c1.equals(null));

        c2.setNom("Durand").setPrenom("Marie").setTelephone("0101010101").setAnneeNaissance(1995);
        System.out.println("Nom : " + c2.getNom());
        System.out.println("Prénom : " + c2.getPrenom());
        System.out.println("Téléphone : " + c2.getTelephone());
        System.out.println("Année de naissance : " + c2.getAnneeNaissance());
        System.out.println("Numéro de permis : " + c2.getNumeroPermis());
        System.out.println("c1 non modifié : " + c1.getNom());

        c2.setAnneeNaissance(-5);

        Client c5 = new Client(null);
        System.out.println(c5);
        System.out.println("Compteur : " + Client.getCompteurClients());
    }
}

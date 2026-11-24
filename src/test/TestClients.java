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
    }
}

package vehicules;

import interfaces.Maintenable;

public class Moto extends Vehicule implements Maintenable {
    private int cylindree;
    private int kmProchainEntretien = KM_ENTRE_ENTRETIENS;

    public Moto(String immat, String marque, String modele, double tarif, int cylindree) {
        super(immat, marque, modele, tarif);
        this.cylindree = cylindree;
        System.out.println("Constructeur Moto appelé");
    }

    public int getCylindree() {
        return cylindree;
    }

    public Moto setCylindree(int cylindree) {
        if (cylindree < 0) {
            System.out.println("ERREUR : La cylindrée ne peut pas être négative.");
            return this;
        }
        this.cylindree = cylindree;
        return this;
    }

    public boolean necessitePermisA2OuA() {
        return cylindree > 125;
    }

    @Override
    public double calculerTarifLocation(int nbJours) {
        return tarifJournalier * nbJours;
    }

    @Override
    public String getCategorie() {
        return "Moto";
    }

    @Override
    public String toString() {
        return String.format("%s - %d cm3 - Permis %s", super.toString(), cylindree, necessitePermisA2OuA() ? "A2/A requis" : "B/A1");
    }

    @Override
    public void planifierEntretien(int kmProchainEntretien) {
        if (kmProchainEntretien < 0) {
            System.out.println("ERREUR : Le kilométrage du prochain entretien ne peut pas être négatif.");
            return;
        }
        this.kmProchainEntretien = kmProchainEntretien;
    }

    @Override
    public boolean necessiteEntretien() {
        return kilometrage >= kmProchainEntretien;
    }

    @Override
    public void effectuerEntretien() {
        setEtat(EtatVehicule.EN_MAINTENANCE);
        journaliser("entretien effectué à " + kilometrage + " km");
        this.kmProchainEntretien = kilometrage + KM_ENTRE_ENTRETIENS;
        setEtat(EtatVehicule.DISPONIBLE);
    }
}

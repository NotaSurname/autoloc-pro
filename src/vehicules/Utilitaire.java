package vehicules;

import interfaces.Maintenable;

public class Utilitaire extends Vehicule implements Maintenable {
    private double volumeChargement;
    private double capaciteCharge;
    private int kmProchainEntretien = KM_ENTRE_ENTRETIENS;

    public Utilitaire(String immat, String marque, String modele, double tarif, double volume, double capacite){
        super(immat, marque, modele, tarif);
        this.volumeChargement = volume;
        this.capaciteCharge = capacite;
        System.out.println("Constructeur Utilitaire appelé");
    }

    public double getVolumeChargement() {
        return volumeChargement;
    }

    public double getCapaciteCharge() {
        return capaciteCharge;
    }

    public Utilitaire setVolumeChargement(double volumeChargement) {
        if (volumeChargement < 0) {
            System.out.println("ERREUR : Le volume de chargement ne peut pas être négatif.");
            return this;
        }
        this.volumeChargement = volumeChargement;
        return this;
    }

    public Utilitaire setCapaciteCharge(double capaciteCharge) {
        if (capaciteCharge < 0) {
            System.out.println("ERREUR : La capacité de charge ne peut pas être négative.");
            return this;
        }
        this.capaciteCharge = capaciteCharge;
        return this;
    }

    public boolean peutTransporter(double poidsKg) {
        return poidsKg <= capaciteCharge;
    }

    @Override
    public double calculerTarifLocation(int nbJours) {
        return tarifJournalier * nbJours + 20;
    }

    @Override
    public String getCategorie() {
        return "Utilitaire";
    }

    @Override
    public String toString() {
        return String.format("%s - %.1f m3 - %.0f kg max",
                super.toString(), volumeChargement, capaciteCharge);
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

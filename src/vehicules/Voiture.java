package vehicules;

import interfaces.Maintenable;

public class Voiture extends Vehicule implements Maintenable {
    private int nombrePlaces;
    private String typeCarburant;
    private boolean boiteAutomatique;
    private int kmProchainEntretien = KM_ENTRE_ENTRETIENS;

    public Voiture() {
        super();
        this.nombrePlaces = 5;
        this.typeCarburant = "N/A";
        this.boiteAutomatique = false;
        System.out.println("Constructeur par défaut Voiture appelé");
    }

    public Voiture(Voiture c) {
        super(c);
        if (c != null) {
            this.nombrePlaces = c.nombrePlaces;
            this.typeCarburant = c.typeCarburant;
            this.boiteAutomatique = c.boiteAutomatique;
            this.kmProchainEntretien = c.kmProchainEntretien;
        } else {
            this.nombrePlaces = 5;
            this.typeCarburant = "N/A";
            this.boiteAutomatique = false;
        }
        System.out.println("Constructeur de copie Voiture appelé");
    }

    public Voiture(String immat, String marque, String modele, double tarif, int places, String carburant,boolean auto) {
        super(immat, marque, modele, tarif);
        this.nombrePlaces = places;
        this.typeCarburant = carburant;
        this.boiteAutomatique = auto;
        System.out.println("Constructeur Voiture appelé");
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public boolean getBoiteAutomatique() {
        return boiteAutomatique;
    }

    public Voiture setNombrePlaces(int nombrePlaces) {
        if (nombrePlaces < 0) {
            System.out.println("ERREUR : Le nombre de place ne peut pas être négatif");
            return this;
        }
        this.nombrePlaces = nombrePlaces;
        return this;
    }

    public Voiture setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
        return this;
    }

    public Voiture setBoiteAutomatique(boolean boiteAutomatique) {
        this.boiteAutomatique = boiteAutomatique;
        return this;
    }

    @Override
    public double calculerTarifLocation(int nbJours) {
        return (nbJours < 7) ? tarifJournalier * nbJours : (tarifJournalier * nbJours) * 0.9;
    }

    @Override
    public String getCategorie() {
        return "Voiture";
    }

    @Override
    public String toString() {
        return String.format("%s - %d places - %s - %s",
                super.toString(), nombrePlaces, typeCarburant, boiteAutomatique ? "Automatique" : "Manuel"
        );
    }

    public int getKmProchainEntretien() {
        return kmProchainEntretien;
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

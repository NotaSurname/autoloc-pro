package vehicules;

import clients.Client;
import interfaces.Assurable;
import interfaces.Reservable;

public abstract class Vehicule implements Assurable, Reservable {
    protected static int compteurVehicules;
    protected static final String DEVISE = "EUR";

    protected String immatriculation;
    protected String marque;
    protected String modele;
    protected int kilometrage;
    protected double tarifJournalier;
    protected EtatVehicule etat;

    private double franchise;
    private boolean assure;

    public Vehicule() {
        this.immatriculation = "N/A";
        this.marque = "N/A";
        this.modele = "N/A";
        this.etat = EtatVehicule.DISPONIBLE;
        this.kilometrage = 0;
        this.tarifJournalier = 0.0;
        System.out.println("Constructeur par défaut appelé");
        compteurVehicules++;
    }

    public Vehicule(String immatriculation, String marque, String modele, double tarifJournalier) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.tarifJournalier = tarifJournalier;
        this.kilometrage = 0;
        this.etat = EtatVehicule.DISPONIBLE;
        System.out.println("Constructeur d'initialisation appelé");
        compteurVehicules++;
    }

    protected Vehicule(Vehicule c) {
        if (c == null) {
            System.out.println("ERREUR : le véhicule à copier est null, valeurs par défaut utilisées");
            this.immatriculation = "N/A";
            this.marque = "N/A";
            this.modele = "N/A";
            this.kilometrage = 0;
            this.tarifJournalier = 0.0;
            this.etat = EtatVehicule.DISPONIBLE;
        } else {
            this.immatriculation = c.immatriculation;
            this.marque = c.marque;
            this.modele = c.modele;
            this.kilometrage = c.kilometrage;
            this.tarifJournalier = c.tarifJournalier;
            this.etat = c.etat;
            this.franchise = c.franchise;
            this.assure = c.assure;
        }
        System.out.println("Constructeur de copie appelé");
        compteurVehicules++;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public double getTarifJournalier() {
        return tarifJournalier;
    }

    public EtatVehicule getEtat() {
        return etat;
    }

    public boolean isDisponible() {
        return etat == EtatVehicule.DISPONIBLE;
    }

    public static int getCompteurVehicules() {
        return compteurVehicules;
    }

    public Vehicule setMarque(String marque){
        this.marque = marque;
        return this;
    }

    public Vehicule setModele(String modele) {
        this.modele = modele;
        return this;
    }

    public Vehicule setKilometrage(int kilometrage) {
        if (kilometrage < 0) {
            System.out.println("ERREUR : Le kilometrage ne peut pas être négatif.");
            return this;
        }
        this.kilometrage = kilometrage;
        return this;
    }

    public Vehicule setTarifJournalier(double tarifJournalier) {
        if (tarifJournalier < 0) {
            System.out.println("ERREUR : Le tarif journalier ne peut pas être négatif.");
            return this;
        }
        this.tarifJournalier = tarifJournalier;
        return this;
    }

    public Vehicule setEtat(EtatVehicule etat) {
        if (etat == null) {
            System.out.println("ERREUR : L'état ne peut pas être null.");
            return this;
        }
        this.etat = etat;
        return this;
    }

    public void rouler(int km) {
        this.kilometrage += km;
    }

    public abstract double calculerTarifLocation(int nbJours);

    public abstract String getCategorie();

    protected void journaliser(String action) {
        System.out.println("[LOG] " + getCategorie() + " " + immatriculation + " : " + action);
    }

    public double getFranchise() {
        return franchise;
    }

    @Override
    public double calculerPrimeAssurance() {
        return TAUX_BASE_ASSURANCE * tarifJournalier;
    }

    @Override
    public boolean estAssure() {
        return assure;
    }

    @Override
    public void souscrireAssurance(double montantFranchise) {
        if (montantFranchise < 0) {
            System.out.println("ERREUR : La franchise ne peut pas être négative.");
            return;
        }
        this.franchise = montantFranchise;
        this.assure = true;
    }

    @Override
    public boolean estDisponiblePourReservation() {
        return etat == EtatVehicule.DISPONIBLE;
    }

    @Override
    public void reserver(Client client, int dureeJours) {
        if (!estDisponiblePourReservation()) {
            System.out.println("ERREUR : Le véhicule " + immatriculation + " n'est pas disponible (" + etat + ").");
            return;
        }
        this.etat = EtatVehicule.LOUE;
    }

    @Override
    public void libererReservation() {
        this.etat = EtatVehicule.DISPONIBLE;
    }

    public void afficherInfos() {
        System.out.println("Vehicule{immatriculation='" + immatriculation + "', marque='" + marque
                + "', modele='" + modele + "', km=" + kilometrage + ", tarif=" + tarifJournalier
                + ", etat=" + etat + "}");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s - %d km - %.2f %s/jour - %s",
                immatriculation, marque, modele, kilometrage, tarifJournalier, DEVISE,
                etat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicule autre = (Vehicule) obj;
        return this.immatriculation.equals(autre.immatriculation);
    }

    @Override
    public int hashCode() {
        return immatriculation.hashCode();
    }
}

package vehicules;

public class Vehicule {
    private static int compteurVehicules;
    public static final String DEVISE = "EUR";

    private String immatriculation;
    private String marque;
    private String modele;
    private int kilometrage;
    private double tarifJournalier;
    private boolean disponible;

    public Vehicule() {
        this.immatriculation = "N/A";
        this.marque = "N/A";
        this.modele = "N/A";
        this.disponible = true;
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
        this.disponible = true;
        System.out.println("Constructeur d'initialisation appelé");
        compteurVehicules++;
    }

    public Vehicule(Vehicule c) {
        if (c == null) {
            System.out.println("ERREUR : le véhicule à copier est null, valeurs par défaut utilisées");
            this.immatriculation = "N/A";
            this.marque = "N/A";
            this.modele = "N/A";
            this.kilometrage = 0;
            this.tarifJournalier = 0.0;
            this.disponible = true;
        } else {
            this.immatriculation = c.immatriculation;
            this.marque = c.marque;
            this.modele = c.modele;
            this.kilometrage = c.kilometrage;
            this.tarifJournalier = c.tarifJournalier;
            this.disponible = c.disponible;
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

    public boolean getDisponible() {
        return disponible;
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

    public Vehicule setDisponible(boolean disponible) {
        this.disponible = disponible;
        return this;
    }

    public void rouler(int km) {
        this.kilometrage += km;
    }

    public void afficherInfos() {
        System.out.println("Vehicule{immatriculation='" + immatriculation + "', marque='" + marque
                + "', modele='" + modele + "', km=" + kilometrage + ", tarif=" + tarifJournalier
                + ", disponible=" + disponible + "}");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s - %d km - %.2f %s/jour - %s",
                immatriculation, marque, modele, kilometrage, tarifJournalier, DEVISE,
                disponible ? "DISPONIBLE" : "INDISPONIBLE");
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

package vehicules;

public class Vehicule {
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
    }

    public Vehicule(String immatriculation, String marque, String modele, double tarifJournalier) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.tarifJournalier = tarifJournalier;
        this.kilometrage = 0;
        this.disponible = true;
        System.out.println("Constructeur d'initialisation appelé");
    }

    public Vehicule(Vehicule c) {
        if (c == null) {
            System.out.println("Erreur : le véhicule à copier est null, valeurs par défaut utilisées");
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
    }

    public void rouler(int km) {
        this.kilometrage += km;
    }

    public void afficherInfos() {
        System.out.println("Vehicule{immatriculation='" + immatriculation + "', marque='" + marque
                + "', modele='" + modele + "', km=" + kilometrage + ", tarif=" + tarifJournalier
                + ", disponible=" + disponible + "}");
    }
}

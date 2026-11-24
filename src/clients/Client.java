package clients;

public class Client {
    private static int compteurClients;

    private String nom;
    private String prenom;
    private String numeroPermis;
    private String telephone;
    private int anneeNaissance;

    public Client() {
        this.nom = "N/A";
        this.prenom = "N/A";
        this.numeroPermis = "N/A";
        this.telephone = "N/A";
        this.anneeNaissance = 2000;
        System.out.println("Constructeur par défaut appelé");
        compteurClients++;
    }

    public Client(String nom, String prenom, String numeroPermis, String telephone, int anneeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroPermis = numeroPermis;
        this.telephone = telephone;
        this.anneeNaissance = anneeNaissance;
        System.out.println("Constructeur d'initialisation appelé");
        compteurClients++;
    }

    public Client(Client c) {
        if (c == null) {
            System.out.println("Erreur : le client à copier est null, valeurs par défaut utilisées");
            this.nom = "N/A";
            this.prenom = "N/A";
            this.numeroPermis = "N/A";
            this.telephone = "N/A";
            this.anneeNaissance = 2000;
        } else {
            this.nom = c.nom;
            this.prenom = c.prenom;
            this.numeroPermis = c.numeroPermis;
            this.telephone = c.telephone;
            this.anneeNaissance = c.anneeNaissance;
        }
        System.out.println("Constructeur de copie appelé");
        compteurClients++;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroPermis() {
        return numeroPermis;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }

    public static int getCompteurClients() {
        return compteurClients;
    }

    public Client setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public Client setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Client setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Client setAnneeNaissance(int anneeNaissance) {
        if (anneeNaissance < 0) {
            System.out.println("ERREUR : La date de naissance ne peut pas être négative.");
            return this;
        }
        this.anneeNaissance = anneeNaissance;
        return this;
    }

    public int calculerAge(int anneeCourante) {
        return anneeCourante - this.anneeNaissance;
    }

    public void afficherInfos() {
        System.out.println("Client{nom='" + nom + "', prenom='" + prenom + "', numeroPermis='" + numeroPermis
                + "', telephone='" + telephone + "', anneeNaissance=" + anneeNaissance + "}");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s - Tel : %s - Né(e) en %d",
                numeroPermis, prenom, nom, telephone, anneeNaissance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client autre = (Client) obj;
        return this.numeroPermis.equals(autre.numeroPermis);
    }

    @Override
    public int hashCode() {
        return numeroPermis.hashCode();
    }
}

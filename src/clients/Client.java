package clients;

public class Client {
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

    }

    public Client(String nom, String prenom, String numeroPermis, String telephone, int anneeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroPermis = numeroPermis;
        this.telephone = telephone;
        this.anneeNaissance = anneeNaissance;
        System.out.println("Constructeur d'initialisation appelé");
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

    }

    public int calculerAge(int anneeCourante) {
        return anneeCourante - this.anneeNaissance;
    }

    public void afficherInfos() {
        System.out.println("Client{nom='" + nom + "', prenom='" + prenom + "', numeroPermis='" + numeroPermis
                + "', telephone='" + telephone + "', anneeNaissance=" + anneeNaissance + "}");
    }
}

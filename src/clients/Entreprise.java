package clients;

public class Entreprise extends Client {
    private String raisonSociale;
    private String siret;

    public Entreprise() {
        super();
        this.raisonSociale = "N/A";
        this.siret = "N/A";
        System.out.println("Constructeur Entreprise appelé");
    }

    public Entreprise(String nom, String prenom, String numeroPermis, String telephone, int anneeNaissance, String raisonSociale, String siret) {
        super(nom, prenom, numeroPermis, telephone, anneeNaissance);
        this.raisonSociale = raisonSociale;
        this.siret = siret;
        System.out.println("Constructeur Entreprise appelé");
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public String getSiret() {
        return siret;
    }

    public Entreprise setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    @Override
    public double calculerRemise() {
        return 0.10;
    }

    @Override
    public String toString() {
        return String.format("%s - Entreprise %s - SIRET : %s",
                super.toString(), raisonSociale, siret);
    }
}

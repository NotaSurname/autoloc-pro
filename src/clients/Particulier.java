package clients;

public class Particulier extends Client {
    private boolean estAssurePlusUnAn;

    public Particulier() {
        super();
        this.estAssurePlusUnAn = false;
        System.out.println("Constructeur Particulier appelé");
    }

    public Particulier(String nom, String prenom, String numeroPermis, String telephone, int anneeNaissance, boolean estAssurePlusUnAn) {
        super(nom, prenom, numeroPermis, telephone, anneeNaissance);
        this.estAssurePlusUnAn = estAssurePlusUnAn;
        System.out.println("Constructeur Particulier appelé");
    }

    public boolean getEstAssurePlusUnAn() {
        return estAssurePlusUnAn;
    }

    public Particulier setEstAssurePlusUnAn(boolean estAssurePlusUnAn) {
        this.estAssurePlusUnAn = estAssurePlusUnAn;
        return this;
    }

    @Override
    public double calculerRemise() {
        return 0.05;
    }

    @Override
    public String toString() {
        return String.format("%s - Particulier - Assuré depuis plus d'un an : %s",
                super.toString(), estAssurePlusUnAn ? "Oui" : "Non");
    }
}

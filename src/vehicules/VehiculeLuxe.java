package vehicules;

public class VehiculeLuxe extends Voiture {
    private boolean chauffeurInclus;
    private String[] options;

    public VehiculeLuxe(String immat, String marque, String modele, double tarif, int places, String carburant, boolean auto, boolean chauffeur, String[] options) {
        super(immat, marque, modele, tarif, places, carburant, auto);
        this.chauffeurInclus = chauffeur;
        this.options = options;
        System.out.println("Constructeur VehiculeLuxe appelé");
    }

    public boolean getChauffeurInclus() {
        return chauffeurInclus;
    }

    public String[] getOptions() {
        return options;
    }

    public VehiculeLuxe setChauffeurInclus(boolean chauffeurInclus) {
        this.chauffeurInclus = chauffeurInclus;
        return this;
    }

    public VehiculeLuxe setOptions(String[] options) {
        this.options = options;
        return this;
    }

    @Override
    public double calculerTarifLocation(int nbJours) {
        double tarif = super.calculerTarifLocation(nbJours) * 1.5;
        if (chauffeurInclus) {
            tarif += 80 * nbJours;
        }
        return tarif;
    }

    @Override
    public String getCategorie() {
        return "VehiculeLuxe";
    }

    @Override
    public String toString() {
        return String.format("%s - Chauffeur : %s - Options : %s",
                super.toString(), chauffeurInclus ? "Oui" : "Non",
                (options == null || options.length == 0) ? "aucune" : String.join(", ", options));
    }
}

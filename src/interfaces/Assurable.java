package interfaces;

public interface Assurable {
    double TAUX_BASE_ASSURANCE = 0.03;
    double calculerPrimeAssurance();
    boolean estAssure();
    void souscrireAssurance(double montantFranchise);
}

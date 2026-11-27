package interfaces;

public interface Maintenable {
    int KM_ENTRE_ENTRETIENS = 10000;
    void planifierEntretien(int kmProchainEntretien);
    boolean necessiteEntretien();
    void effectuerEntretien();
}

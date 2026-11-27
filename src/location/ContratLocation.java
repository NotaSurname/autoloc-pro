package location;

import clients.Client;
import vehicules.Vehicule;

public class ContratLocation {
    private Client client;
    private Vehicule vehicule;
    private int dateDebut;
    private int dureeJours;
    private boolean cloture;

    public ContratLocation(Client c, Vehicule v, int dateDebut, int dureeJours) {
        this.client = c;
        this.vehicule = v;
        this.dateDebut = dateDebut;
        this.dureeJours = dureeJours;
        vehicule.reserver(client, dureeJours);
    }

    public Client getClient() {
        return client;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public int getDateDebut() {
        return dateDebut;
    }

    public int getDureeJours() {
        return dureeJours;
    }

    public boolean isCloture() {
        return cloture;
    }

    public double calculerMontantTotal() {
        return vehicule.calculerTarifLocation(dureeJours) * (1 - client.calculerRemise());
    }

    public void cloturerContrat() {
        this.cloture = true;
        vehicule.libererReservation();
    }

    @Override
    public String toString() {
        return String.format("Contrat %s - %s | jour %d, %d jour(s) | %.2f EUR | %s",
                client.getNumeroPermis(), vehicule.getImmatriculation(), dateDebut, dureeJours,
                calculerMontantTotal(), cloture ? "CLÔTURÉ" : "EN COURS");
    }
}

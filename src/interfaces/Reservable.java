package interfaces;

import clients.Client;

public interface Reservable {
    boolean estDisponiblePourReservation();
    void reserver(Client client, int dureeJours);
    void libererReservation();
}

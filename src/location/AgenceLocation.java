package location;

import clients.*;
import vehicules.*;
import interfaces.*;

import java.util.ArrayList;

public class AgenceLocation {
    private ArrayList<Vehicule> flotte;
    private ArrayList<Client> clients;
    private ArrayList<ContratLocation> contrats;
    private int capaciteVehicules;
    private int capaciteClients;
    private int capaciteContrats;

    public AgenceLocation(int capV, int capC, int capCo) {
        this.flotte = new ArrayList<>(capV);
        this.clients = new ArrayList<>(capC);
        this.contrats = new ArrayList<>(capCo);
        this.capaciteVehicules = capV;
        this.capaciteClients = capC;
        this.capaciteContrats = capCo;
    }

    public int getNbVehicules() {
        return flotte.size();
    }

    public int getNbClients() {
        return clients.size();
    }

    public int getNbContrats() {
        return contrats.size();
    }

    public Vehicule rechercherParImmatriculation(String immat) {
        if (immat == null) return null;
        for (Vehicule v : flotte) {
            if (v.getImmatriculation().equals(immat)) {
                return v;
            }
        }
        return null;
    }

    public Client rechercherClientParPermis(String numeroPermis) {
        if (numeroPermis == null) return null;
        for (Client c : clients) {
            if (c.getNumeroPermis().equals(numeroPermis)) {
                return c;
            }
        }
        return null;
    }

    public boolean ajouterVehicule(Vehicule v) {
        if (v == null) {
            System.out.println("ERREUR : Le véhicule ne peut pas être null.");
            return false;
        }
        if (flotte.size() >= capaciteVehicules) {
            System.out.println("ERREUR : La flotte est pleine.");
            return false;
        }
        if (flotte.contains(v)) {
            System.out.println("ERREUR : Le véhicule " + v.getImmatriculation() + " existe déjà.");
            return false;
        }
        flotte.add(v);
        return true;
    }

    public boolean ajouterClient(Client c) {
        if (c == null) {
            System.out.println("ERREUR : Le client ne peut pas être null.");
            return false;
        }
        if (clients.size() >= capaciteClients) {
            System.out.println("ERREUR : La liste des clients est pleine.");
            return false;
        }
        if (rechercherClientParPermis(c.getNumeroPermis()) != null) {
            System.out.println("ERREUR : Le client " + c.getNumeroPermis() + " existe déjà.");
            return false;
        }
        clients.add(c);
        return true;
    }

    public ContratLocation louerVehicule(Client c, Vehicule v, int dateDebut, int duree) {
        if (c == null) {
            System.out.println("ERREUR : Client invalide.");
            return null;
        }
        if (v == null) {
            System.out.println("ERREUR : Véhicule invalide.");
            return null;
        }
        if (!v.estDisponiblePourReservation()) {
            System.out.println("ERREUR : Le véhicule " + v.getImmatriculation()
                    + " n'est pas disponible (" + v.getEtat() + ").");
            return null;
        }
        if (dateDebut < 1 || dateDebut > 365) {
            System.out.println("ERREUR : Date de début (1-365) invalide.");
            return null;
        }
        if (duree <= 0) {
            System.out.println("ERREUR : Durée invalide.");
            return null;
        }
        if (contrats.size() >= capaciteContrats) {
            System.out.println("ERREUR : La liste des contrats est pleine.");
            return null;
        }
        ContratLocation contrat = new ContratLocation(c, v, dateDebut, duree);
        contrats.add(contrat);
        return contrat;
    }

    public boolean retournerVehicule(String immat) {
        for (ContratLocation contrat : contrats) {
            if (!contrat.isCloture() && contrat.getVehicule().getImmatriculation().equals(immat)) {
                contrat.cloturerContrat();
                return true;
            }
        }
        System.out.println("ERREUR : Aucun contrat en cours pour le véhicule " + immat + ".");
        return false;
    }

    public void afficherFlotte() {
        System.out.println("=== Flotte (" + flotte.size() + " véhicule(s)) ===");
        for (Vehicule v : flotte) {
            System.out.println(v);
        }
    }

    public void afficherVehiculesDisponibles() {
        System.out.println("=== Véhicules disponibles ===");
        int compteur = 0;
        for (Vehicule v : flotte) {
            if (v.isDisponible()) {
                System.out.println(v);
                compteur++;
            }
        }
        if (compteur == 0) {
            System.out.println("Aucun véhicule disponible.");
        }
    }

    public void afficherVehiculesAEntretenir() {
        System.out.println("=== Véhicules à entretenir ===");
        int compteur = 0;
        for (Vehicule v : flotte) {
            if (v instanceof Maintenable && ((Maintenable) v).necessiteEntretien()) {
                System.out.println(v);
                compteur++;
            }
        }
        if (compteur == 0) {
            System.out.println("Aucun véhicule à entretenir.");
        }
    }

    public boolean effectuerEntretien(String immat) {
        Vehicule v = rechercherParImmatriculation(immat);
        if (v == null) {
            System.out.println("ERREUR : Véhicule " + immat + " introuvable.");
            return false;
        }
        if (!(v instanceof Maintenable)) {
            System.out.println("ERREUR : Le véhicule " + immat + " n'est pas maintenable.");
            return false;
        }
        if (v.getEtat() == EtatVehicule.LOUE) {
            System.out.println("ERREUR : Le véhicule " + immat + " est actuellement loué.");
            return false;
        }
        ((Maintenable) v).effectuerEntretien();
        return true;
    }

    public void afficherCatalogueParCategorie() {
        System.out.println("=== Catalogue par catégorie ===");
        ArrayList<String> categories = new ArrayList<>();
        for (Vehicule v : flotte) {
            if (!categories.contains(v.getCategorie())) {
                categories.add(v.getCategorie());
            }
        }
        for (String cat : categories) {
            System.out.println("--- " + cat + " ---");
            for (Vehicule v : flotte) {
                if (v.getCategorie().equals(cat)) {
                    System.out.println("  " + v);
                }
            }
        }
    }

    public void afficherHistoriqueClient(String numeroPermis) {
        Client c = rechercherClientParPermis(numeroPermis);
        if (c == null) {
            System.out.println("ERREUR : Client " + numeroPermis + " introuvable.");
            return;
        }
        System.out.println("=== Historique de " + c.getPrenom() + " " + c.getNom() + " ===");
        int compteur = 0;
        for (ContratLocation contrat : contrats) {
            if (contrat.getClient().equals(c)) {
                System.out.println(contrat);
                compteur++;
            }
        }
        if (compteur == 0) {
            System.out.println("Aucun contrat pour ce client.");
        }
    }

    public void afficherStatistiques() {
        double chiffreAffaires = 0.0;
        for (ContratLocation contrat : contrats) {
            if (contrat.isCloture()) {
                chiffreAffaires += contrat.calculerMontantTotal();
            }
        }

        int nbLoues = 0;
        int nbAReviser = 0;
        for (Vehicule v : flotte) {
            if (v.getEtat() == EtatVehicule.LOUE) {
                nbLoues++;
            }
            if (v instanceof Maintenable && ((Maintenable) v).necessiteEntretien()) {
                nbAReviser++;
            }
        }
        double tauxOccupation = flotte.isEmpty() ? 0.0 : (double) nbLoues / flotte.size() * 100;

        System.out.println("=== Statistiques ===");
        System.out.printf("Chiffre d'affaires (contrats clôturés) : %.2f EUR%n", chiffreAffaires);
        System.out.printf("Taux d'occupation : %.1f %% (%d/%d)%n", tauxOccupation, nbLoues, flotte.size());
        System.out.println("Véhicules à réviser : " + nbAReviser);
    }

    public void compterVehiculesParCategorie() {
        int nbVoitures = 0;
        int nbLuxe = 0;
        int nbMotos = 0;
        int nbUtilitaires = 0;
        for (Vehicule v : flotte) {
            if (v instanceof VehiculeLuxe) {
                nbLuxe++;
            } else if (v instanceof Voiture) {
                nbVoitures++;
            } else if (v instanceof Moto) {
                nbMotos++;
            } else if (v instanceof Utilitaire) {
                nbUtilitaires++;
            }
        }
        System.out.println("=== Véhicules par catégorie ===");
        System.out.println("Voitures : " + nbVoitures);
        System.out.println("Véhicules de luxe : " + nbLuxe);
        System.out.println("Motos : " + nbMotos);
        System.out.println("Utilitaires : " + nbUtilitaires);
    }
}

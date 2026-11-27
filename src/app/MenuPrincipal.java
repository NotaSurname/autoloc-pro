package app;

import clients.Client;
import clients.Entreprise;
import clients.Particulier;
import location.AgenceLocation;
import location.ContratLocation;
import interfaces.Maintenable;
import vehicules.*;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private AgenceLocation agence;

    public MenuPrincipal(AgenceLocation agence) {
        this.agence = agence;
    }

    public void start() {
        int choix;

        do {
            afficherMenu();
            choix = lireEntier("Votre choix : ");

            switch (choix) {
                case 1:
                    ajouterVehicule();
                    break;
                case 2:
                    ajouterClient();
                    break;
                case 3:
                    louerVehicule();
                    break;
                case 4:
                    retournerVehicule();
                    break;
                case 5:
                    agence.afficherFlotte();
                    break;
                case 6:
                    agence.afficherVehiculesDisponibles();
                    break;
                case 7:
                    agence.afficherVehiculesAEntretenir();
                    break;
                case 8:
                    effectuerEntretien();
                    break;
                case 9:
                    agence.afficherStatistiques();
                    break;
                case 10:
                    agence.afficherCatalogueParCategorie();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez entrer un nombre entre 0 et 10.");
            }

            if (choix != 0) {
                pause();
            }
        } while (choix != 0);

        scanner.close();
    }

    private void afficherMenu() {
        System.out.println();
        System.out.println("========== AUTOLOC PRO ==========");
        System.out.println("1. Ajouter un véhicule");
        System.out.println("2. Ajouter un client");
        System.out.println("3. Louer un véhicule");
        System.out.println("4. Retourner un véhicule");
        System.out.println("5. Afficher la flotte complète");
        System.out.println("6. Afficher les véhicules disponibles");
        System.out.println("7. Afficher les véhicules à entretenir");
        System.out.println("8. Effectuer l'entretien d'un véhicule");
        System.out.println("9. Afficher les statistiques de l'agence");
        System.out.println("10. Afficher le catalogue par catégorie");
        System.out.println("0. Quitter");
    }

    private void ajouterVehicule() {
        System.out.println("--- Ajouter un véhicule ---");
        System.out.println("1. Voiture");
        System.out.println("2. Moto");
        System.out.println("3. Utilitaire");
        System.out.println("4. Véhicule de luxe");
        int type = lireEntier("Type de véhicule : ");

        if (type < 1 || type > 4) {
            System.out.println("Type invalide, retour au menu.");
            return;
        }

        String immat = lireTexte("Immatriculation : ");
        String marque = lireTexte("Marque : ");
        String modele = lireTexte("Modèle : ");
        double tarif = lireDouble("Tarif journalier (EUR) : ");

        Vehicule v;
        switch (type) {
            case 1: {
                int places = lireEntier("Nombre de places : ");
                String carburant = lireTexte("Type de carburant : ");
                boolean auto = lireDeuxChoix("Boîte automatique ? (o/n) : ", "o", "n");
                v = new Voiture(immat, marque, modele, tarif, places, carburant, auto);
                break;
            }
            case 2: {
                int cylindree = lireEntier("Cylindrée (cm3) : ");
                v = new Moto(immat, marque, modele, tarif, cylindree);
                break;
            }
            case 3: {
                double volume = lireDouble("Volume de chargement (m3) : ");
                double capacite = lireDouble("Capacité de charge (kg) : ");
                v = new Utilitaire(immat, marque, modele, tarif, volume, capacite);
                break;
            }
            default: {
                int places = lireEntier("Nombre de places : ");
                String carburant = lireTexte("Type de carburant : ");
                boolean auto = lireDeuxChoix("Boîte automatique ? (o/n) : ", "o", "n");
                boolean chauffeur = lireDeuxChoix("Chauffeur inclus ? (o/n) : ", "o", "n");
                String[] options = lireTexte("Options (séparées par des virgules) : ").split(",");
                v = new VehiculeLuxe(immat, marque, modele, tarif, places, carburant, auto, chauffeur, options);
                break;
            }
        }

        if (agence.ajouterVehicule(v)) {
            System.out.println("Véhicule ajouté : " + v);
        }
    }

    private void ajouterClient() {
        System.out.println("--- Ajouter un client ---");
        boolean estParticulier = lireDeuxChoix("Particulier ou Entreprise ? (p/e) : ", "p", "e");
        String nom = lireTexte("Nom : ");
        String prenom = lireTexte("Prénom : ");
        String numeroPermis = lireTexte("Numéro du permis : ");
        String telephone = lireTexte("Numéro de téléphone : ");
        int anneeNaissance = lireEntier("Année de naissance : ");

        Client c;
        if (estParticulier) {
            boolean assure = lireDeuxChoix("Assuré depuis plus d'un an ? (o/n) : ", "o", "n");
            c = new Particulier(nom, prenom, numeroPermis, telephone, anneeNaissance, assure);
        } else {
            String raisonSociale = lireTexte("Raison sociale : ");
            String siret = lireTexte("SIRET : ");
            c = new Entreprise(nom, prenom, numeroPermis, telephone, anneeNaissance, raisonSociale, siret);
        }

        if (agence.ajouterClient(c)) {
            System.out.println("Client ajouté : " + c);
        }
    }

    private void louerVehicule() {
        System.out.println("--- Louer un véhicule ---");
        String numeroPermis = lireTexte("Numéro du permis du client : ");
        Client c = agence.rechercherClientParPermis(numeroPermis);
        if (c == null) {
            System.out.println("ERREUR : Client " + numeroPermis + " introuvable.");
            return;
        }
        System.out.println("Client : " + c);

        agence.afficherVehiculesDisponibles();
        String immat = lireTexte("Immatriculation du véhicule : ");
        Vehicule v = agence.rechercherParImmatriculation(immat);
        if (v == null) {
            System.out.println("ERREUR : Véhicule " + immat + " introuvable.");
            return;
        }
        if (!v.estDisponiblePourReservation()) {
            System.out.println("ERREUR : Le véhicule " + immat + " n'est pas disponible (" + v.getEtat() + ").");
            return;
        }

        int dateDebut = lireEntier("Jour de début (1-365) : ");
        int duree = lireEntier("Durée (en jours) : ");

        ContratLocation contrat = agence.louerVehicule(c, v, dateDebut, duree);
        if (contrat != null) {
            System.out.println("Location enregistrée : " + contrat);
        }
    }

    public void retournerVehicule() {
        System.out.println("--- Retourner un véhicule ---");
        String immat = lireTexte("Immatriculation du véhicule : ");
        Vehicule v = agence.rechercherParImmatriculation(immat);
        if (v == null) {
            System.out.println("ERREUR : Véhicule " + immat + " introuvable.");
            return;
        }
        if (v.getEtat() != EtatVehicule.LOUE) {
            System.out.println("ERREUR : Le véhicule " + immat + " n'est pas en location (" + v.getEtat() + ").");
            return;
        }

        int km = lireEntier("Kilomètres parcourus pendant la location : ");
        while (km < 0) {
            System.out.println("Le nombre de kilomètres ne peut pas être négatif.");
            km = lireEntier("Kilomètres parcourus pendant la location : ");
        }

        if (agence.retournerVehicule(immat)) {
            v.rouler(km);
            System.out.println("Véhicule retourné : " + v);
            if (v instanceof Maintenable && ((Maintenable) v).necessiteEntretien()) {
                System.out.println("ATTENTION : ce véhicule nécessite un entretien.");
            }
        }
    }

    public void effectuerEntretien() {
        System.out.println("--- Effectuer un entretien ---");
        String immat = lireTexte("Immatriculation du véhicule : ");
        Vehicule v = agence.rechercherParImmatriculation(immat);
        if (v == null ) {
            System.out.println("ERREUR : Véhicule " + immat + " introuvable.");
            return;
        }
        if (agence.effectuerEntretien(immat)) {
            System.out.println("Entretien effectué avec succès");
        }
    }

    private void pause() {
        System.out.print("Appuyer sur ENTREE pour continuer...");
        scanner.nextLine();
    }

    private int lireEntier(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Saisie invalide, veuillez entrer un nombre entier.");
            scanner.next();
            System.out.print(message);
        }
        int valeur = scanner.nextInt();
        scanner.nextLine();
        return valeur;
    }

    private double lireDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Saisie invalide, veuillez entrer un nombre.");
            scanner.next();
            System.out.print(message);
        }
        double valeur = scanner.nextDouble();
        scanner.nextLine();
        return valeur;
    }

    private String lireTexte(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private boolean lireDeuxChoix(String message, String choix1, String choix2) {
        String reponse = lireTexte(message).toLowerCase();
        while (!reponse.equals(choix1) && !reponse.equals(choix2)) {
            System.out.println("Répondez par " + choix1 + " ou " + choix2 + ".");
            reponse = lireTexte(message).toLowerCase();
        }
        return reponse.equals(choix1);
    }
}

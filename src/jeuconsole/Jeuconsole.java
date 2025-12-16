package jeuconsole;

import java.util.Scanner;

public class Jeuconsole {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue dans le Jeu de Combat !");

        String nomJ1 = demanderTexteNonVide(sc, "Joueur 1, entre ton nom : ");
        String nomJ2 = demanderTexteNonVide(sc, "Joueur 2, entre ton nom : ");

        Joueur joueur1 = creerJoueur(sc, nomJ1);
        Joueur joueur2 = creerJoueur(sc, nomJ2);

        System.out.println("\nLes équipes sont prêtes !");
        joueur1.afficherEquipe();
        System.out.println();
        joueur2.afficherEquipe();

        int tour = 1;
        while (joueur1.aEncoreDesPersonnagesVivants() && joueur2.aEncoreDesPersonnagesVivants()) {
            System.out.println("\n--- TOUR " + tour + " ---");

            // Tour joueur 1
            jouerUnTour(sc, joueur1, joueur2);
            if (!joueur2.aEncoreDesPersonnagesVivants()) {
                break;
            }

            // Tour joueur 2
            jouerUnTour(sc, joueur2, joueur1);

            tour++;
        }

        // Fin du jeu
        if (joueur1.aEncoreDesPersonnagesVivants()) {
            System.out.println("\nVictoire de " + joueur1.getNom()
                    + " ! Tous les personnages de " + joueur2.getNom() + " sont éliminés.");
        } else {
            System.out.println("\nVictoire de " + joueur2.getNom()
                    + " ! Tous les personnages de " + joueur1.getNom() + " sont éliminés.");
        }

        sc.close();
    }

    // ------------------ Méthodes utilitaires ------------------

    private static Joueur creerJoueur(Scanner sc, String nomJoueur) {
        System.out.println("\n" + nomJoueur + ", crée ton équipe :");
        System.out.println("Rappel : chaque personnage commence avec 100 HP et 20 d'attaque.");

        Personnage[] equipe = new Personnage[3];
        for (int i = 0; i < 3; i++) {
            String nomPerso = demanderTexteNonVide(sc, "Nom du personnage " + (i + 1) + " : ");
            equipe[i] = new Personnage(nomPerso, 100, 20);
        }

        return new Joueur(nomJoueur, equipe);
    }

    private static void jouerUnTour(Scanner sc, Joueur attaquant, Joueur defenseur) {
        System.out.println("\nTour de " + attaquant.getNom());

        System.out.println("Choisis ton personnage attaquant :");
        attaquant.afficherEquipe();
        int idxAttaquant = choisirIndexPersonnageVivant(sc, attaquant);

        System.out.println("Choisis la cible chez " + defenseur.getNom() + " :");
        defenseur.afficherEquipe();
        int idxCible = choisirIndexPersonnageVivant(sc, defenseur);

        Personnage pAttaquant = attaquant.getEquipe()[idxAttaquant];
        Personnage pCible = defenseur.getEquipe()[idxCible];

        System.out.println();
        pAttaquant.attaquer(pCible);

        System.out.println("\nEtat des équipes après l'attaque :");
        attaquant.afficherEquipe();
        System.out.println();
        defenseur.afficherEquipe();
    }

    private static int choisirIndexPersonnageVivant(Scanner sc, Joueur joueur) {
        while (true) {
            System.out.print("Entre le numéro (1-3) : ");
            String input = sc.nextLine().trim();

            int choix;
            try {
                choix = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Tape 1, 2 ou 3.");
                continue;
            }

            if (choix < 1 || choix > 3) {
                System.out.println("Choix hors limite. Tape 1, 2 ou 3.");
                continue;
            }

            int index = choix - 1;
            Personnage p = joueur.getEquipe()[index];

            if (p == null) {
                System.out.println("Cet emplacement est vide. Choisis un autre.");
                continue;
            }
            if (!p.estVivant()) {
                System.out.println("Ce personnage est KO. Choisis un personnage vivant.");
                continue;
            }

            return index;
        }
    }

    private static String demanderTexteNonVide(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String texte = sc.nextLine().trim();
            if (!texte.isEmpty()) {
                return texte;
            }
            System.out.println("Tu dois entrer un texte non vide.");
        }
    }
}

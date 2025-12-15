package jeuconsole;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Jeuconsole {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue dans le Jeu de Combat !");

        // Phase 1 : Création des joueurs
        System.out.print("Joueur 1, entre ton nom : ");
        String nomJ1 = sc.nextLine().trim();

        System.out.print("Joueur 2, entre ton nom : ");
        String nomJ2 = sc.nextLine().trim();

        Joueur joueur1 = creerJoueur(sc, nomJ1);
        Joueur joueur2 = creerJoueur(sc, nomJ2);

        System.out.println("\n✅ Les équipes sont prêtes !");
        joueur1.afficherEquipe();
        System.out.println();
        joueur2.afficherEquipe();

        // Phase 2 : Boucle de combat
        int tour = 1;
        while (joueur1.aEncoreDesPersonnagesVivants() && joueur2.aEncoreDesPersonnagesVivants()) {
            System.out.println("\n--- TOUR " + tour + " ---");

            // Tour joueur 1
            jouerUnTour(sc, joueur1, joueur2);
            if (!joueur2.aEncoreDesPersonnagesVivants()) break;

            // Tour joueur 2
            jouerUnTour(sc, joueur2, joueur1);

            tour++;
        }

        // Fin du jeu
        if (joueur1.aEncoreDesPersonnagesVivants()) {
            System.out.println("\n🏁 Victoire de " + joueur1.getNomUtilisateur()
                    + " ! Tous les personnages de " + joueur2.getNomUtilisateur() + " sont éliminés.");
        } else {
            System.out.println("\n🏁 Victoire de " + joueur2.getNomUtilisateur()
                    + " ! Tous les personnages de " + joueur1.getNomUtilisateur() + " sont éliminés.");
        }

        sc.close();
    }

    // ------------------ Méthodes utilitaires ------------------

    private static Joueur creerJoueur(Scanner sc, String nomJoueur) {
        System.out.println("\n" + nomJoueur + ", crée ton équipe :");

        Personnage[] equipe = new Personnage[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Nom du personnage " + (i + 1) + " : ");
            String nomPerso = sc.nextLine().trim();
            equipe[i] = new Personnage(nomPerso, 100, 20);
        }

        return new Joueur(nomJoueur, equipe);
    }

    private static void jouerUnTour(Scanner sc, Joueur attaquant, Joueur defenseur) {
        System.out.println("\n👉 Tour de " + attaquant.getNomUtilisateur());

        System.out.println("Choisis ton personnage attaquant :");
        attaquant.afficherEquipe();
        int idxAttaquant = choisirIndexPersonnageVivant(sc, attaquant);

        System.out.println("Choisis la cible chez " + defenseur.getNomUtilisateur() + " :");
        defenseur.afficherEquipe();
        int idxCible = choisirIndexPersonnageVivant(sc, defenseur);

        Personnage pAttaquant = attaquant.getEquipe()[idxAttaquant];
        Personnage pCible = defenseur.getEquipe()[idxCible];

        System.out.println();
        pAttaquant.attaquer(pCible);

        System.out.println("\nEtat de l'équipe de " + defenseur.getNomUtilisateur() + " :");
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
                System.out.println("❌ Entrée invalide. Tape 1, 2 ou 3.");
                continue;
            }

            if (choix < 1 || choix > 3) {
                System.out.println("❌ Choix hors limite. Tape 1, 2 ou 3.");
                continue;
            }

            int index = choix - 1;
            Personnage p = joueur.getEquipe()[index];

            if (p == null) {
                System.out.println("❌ Slot vide. Choisis un autre.");
                continue;
            }
            if (!p.estVivant()) {
                System.out.println("❌ Ce personnage est KO. Choisis un personnage vivant.");
                continue;
            }

            return index;
        }
    }
}

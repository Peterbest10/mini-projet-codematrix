package jeuconsole;

/**
 *
 * @author Admin
 */
public class Joueur {
    private String nomUtilisateur;
    private Personnage[] equipe;

    public Joueur(String nomUtilisateur, Personnage[] equipe) {
        this.nomUtilisateur = nomUtilisateur;
        this.equipe = equipe;
    }

    // --- Méthodes demandées ---

    // Retourne un personnage vivant (simple : le premier vivant trouvé)
    public Personnage choisirPersonnageVivant() {
        for (Personnage p : equipe) {
            if (p != null && p.estVivant()) {
                return p;
            }
        }
        return null;
    }

    // Vérifie s'il reste au moins un personnage vivant
    public boolean aEncoreDesPersonnagesVivants() {
        for (Personnage p : equipe) {
            if (p != null && p.estVivant()) {
                return true;
            }
        }
        return false;
    }

    // Affiche les 3 personnages + état
    public void afficherEquipe() {
        System.out.println("Equipe de " + nomUtilisateur + " :");
        for (int i = 0; i < equipe.length; i++) {
            System.out.print((i + 1) + ") ");
            if (equipe[i] == null) {
                System.out.println("Vide");
            } else {
                equipe[i].afficherEtat();
            }
        }
    }

    // --- Getters / Setters ---

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public Personnage[] getEquipe() {
        return equipe;
    }

    public void setEquipe(Personnage[] equipe) {
        this.equipe = equipe;
    }
}

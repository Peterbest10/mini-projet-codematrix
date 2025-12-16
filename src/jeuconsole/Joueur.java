package jeuconsole;

/**
 * Cette classe représente un joueur.
 * Un joueur possède un nom et une équipe de 3 personnages.
 */
public class Joueur {

    private String nom;
    private Personnage[] equipe;

    /**
     * Constructeur du joueur.
     * On sécurise l'équipe pour qu'elle contienne exactement 3 personnages.
     */
    public Joueur(String nom, Personnage[] equipe) {
        this.nom = nom;

        if (equipe == null || equipe.length != 3) {
            this.equipe = new Personnage[3];
        } else {
            this.equipe = equipe;
        }
    }

    /**
     * Vérifie s'il reste au moins un personnage vivant dans l'équipe.
     */
    public boolean aEncoreDesPersonnagesVivants() {
        for (Personnage p : equipe) {
            if (p != null && p.estVivant()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne le premier personnage vivant trouvé dans l'équipe.
     * Cette méthode peut servir de "secours" si on veut un choix automatique.
     */
    public Personnage choisirPersonnageVivant() {
        for (Personnage p : equipe) {
            if (p != null && p.estVivant()) {
                return p;
            }
        }
        return null;
    }

    /**
     * Retourne un personnage à partir de son numéro (1 à 3).
     * Retourne null si le numéro est invalide.
     */
    public Personnage getPersonnageParNumero(int numero) {
        if (numero < 1 || numero > equipe.length) {
            return null;
        }
        return equipe[numero - 1];
    }

    /**
     * Affiche l'équipe du joueur avec l'état des personnages.
     */
    public void afficherEquipe() {
        System.out.println("Equipe de " + nom + " :");
        for (int i = 0; i < equipe.length; i++) {
            System.out.print((i + 1) + ") ");
            if (equipe[i] == null) {
                System.out.println("Aucun personnage");
            } else {
                equipe[i].afficherEtat();
            }
        }
    }

    // ---------------- Getters / Setters ----------------

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Personnage[] getEquipe() {
        return equipe;
    }

    /**
     * Modifie l'équipe seulement si elle contient exactement 3 personnages.
     */
    public void setEquipe(Personnage[] equipe) {
        if (equipe != null && equipe.length == 3) {
            this.equipe = equipe;
        }
    }
}

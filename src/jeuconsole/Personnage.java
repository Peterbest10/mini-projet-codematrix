package jeuconsole;

/**
 * Cette classe représente un personnage du jeu.
 * Un personnage possède un nom, des points de vie
 * et une force d'attaque.
 * 
 * Elle gère les actions de combat et l'état du personnage.
 */
public class Personnage {

    private String nom;
    private int vie;
    private int attaque;

    /**
     * Constructeur du personnage
     * @param nom nom du personnage
     * @param vie points de vie initiaux
     * @param attaque force d'attaque
     */
    public Personnage(String nom, int vie, int attaque) {
        this.nom = nom;
        this.vie = vie;
        this.attaque = attaque;
    }

    /**
     * Permet à ce personnage d'attaquer un autre personnage.
     * Un personnage KO ne peut pas attaquer.
     */
    public void attaquer(Personnage cible) {

        if (!this.estVivant()) {
            System.out.println(nom + " est KO et ne peut pas attaquer.");
            return;
        }

        if (!cible.estVivant()) {
            System.out.println(cible.nom + " est déjà KO.");
            return;
        }

        System.out.println(nom + " attaque " + cible.nom + ".");
        cible.vie -= attaque;

        if (cible.vie < 0) {
            cible.vie = 0;
        }

        System.out.println(cible.nom + " perd " + attaque + " points de vie.");
    }

    /**
     * Indique si le personnage est encore en vie
     */
    public boolean estVivant() {
        return vie > 0;
    }

    /**
     * Affiche l'état actuel du personnage
     */
    public void afficherEtat() {
        if (estVivant()) {
            System.out.println(nom + " - " + vie + " HP");
        } else {
            System.out.println(nom + " - 0 HP (KO)");
        }
    }

    // Getters

    public String getNom() {
        return nom;
    }

    public int getVie() {
        return vie;
    }

    public int getAttaque() {
        return attaque;
    }

    // Setter utile pour sécuriser la vie

    public void setVie(int vie) {
        this.vie = Math.max(0, vie);
    }
}

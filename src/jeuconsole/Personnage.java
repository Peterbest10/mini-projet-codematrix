package jeuconsole;

/**
 *
 * @author Admin
 */
public class Personnage {
    private String nom;
    private int vie;
    private int attaque;

    public Personnage(String nom, int vie, int attaque) {
        this.nom = nom;
        this.vie = vie;
        this.attaque = attaque;
    }

    // --- Méthodes du jeu ---

    public void attaquer(Personnage cible) {
        if (!this.estVivant()) {
            System.out.println(nom + " est KO et ne peut pas attaquer.");
            return;
        }

        if (!cible.estVivant()) {
            System.out.println(cible.nom + " est déjà KO.");
            return;
        }

        cible.vie -= attaque;
        if (cible.vie < 0) {
            cible.vie = 0;
        }

        System.out.println(nom + " attaque " + cible.nom + " !");
        System.out.println(cible.nom + " perd " + attaque + " points de vie.");
    }

    public boolean estVivant() {
        return vie > 0;
    }

    public void afficherEtat() {
        if (estVivant()) {
            System.out.println(nom + " - " + vie + " HP");
        } else {
            System.out.println(nom + " - 0 HP (KO)");
        }
    }

    // --- Getters / Setters ---

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = Math.max(0, vie);
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }
}

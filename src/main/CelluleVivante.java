package main;
/**
 * Contient la position ainsi que le score de la cellule
 * @author maxime
 * @version 1.0
 */
public class CelluleVivante {
    /**
     * Correspond a la ligne ou se situe la cellule
     */
    private int numLigne;
    /**
     * Correspond a la colonne ou se situe la cellule
     */
    private int numColonne;
    /**
     * Correspond au score de la cellule, 1 pour les voisins, 10 pour les cellules de base
     */
    private int score;
    /**
     * Constructeur vide
     */
    public CelluleVivante() {
        this.numLigne = 0;
        this.numColonne = 0;
        this.score = 0;
    }
    /**
     * Constructeur
     * @param numLigne numero ligne
     * @param numColonne numero colonne
     */
    public CelluleVivante(int numLigne, int numColonne) {
        this.numLigne = numLigne;
        this.numColonne = numColonne;
        this.score = 1;
    }
    /**
     * Constructeur
     * @param numLigne numero ligne
     * @param numColonne numero colonne
     * @param score score
     */
    public CelluleVivante(int numLigne, int numColonne, int score) {
        this.numLigne = numLigne;
        this.numColonne = numColonne;
        this.score = score;
    }


    public int getNumLigne() {
        return numLigne;
    }
    public int getNumColonne() {
        return numColonne;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}

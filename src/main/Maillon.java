package main;
/**
 * Maillon contient un type generique, qui est une CelluleVivante dans le projet
 * @author maxime
 * @version 1.0
 */
public class Maillon<T> {
    /**
     * correspond a la valeur/objet stocker dans le maillon
     */
    public T valeur = null;
    /**
     * lien vers le maillon suivant
     */
    public Maillon<T> suivant = null;
    /**
     * constructeur
     * @param valeur stocker dans le maillon
     */
    public Maillon(T valeur) {
        this.valeur = valeur;
    }


    public T getElement() {
        return valeur;
    }
}

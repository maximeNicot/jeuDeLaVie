package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Contient un objet ListeSimplementChainee
 * @author maxime
 */
public abstract class Jeu {
    private ListeSimplementChainee listeJeu;

    public Jeu(ListeSimplementChainee listeJeu) {
        this.listeJeu = listeJeu;
    }
    /**
     * Permet a partir du fichier lif de creer la liste initial
     * @param path chemin d'accces du fichier lif
     */
    public abstract void creerJeuInitial(String path);
    /**
     * permet de creer la liste suivante
     * @return la liste suivante
     */
    public abstract ListeSimplementChainee jeuEtapeSuivante();
    /**
     * Permet de creer un string correspondant a la liste
     * @return le string correspondant a la liste
     */
    @Override
    public abstract String toString();


    public ListeSimplementChainee getListeJeu() {
        return listeJeu;
    }
    public void setListeJeu(ListeSimplementChainee listeJeu) {
        this.listeJeu = listeJeu;
    }
}

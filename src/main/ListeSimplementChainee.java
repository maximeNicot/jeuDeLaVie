package main;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class ListeSimplementChainee contenant des Maillon
 * Permet de transformer un fichier Lif en String
 * @author maxime
 * @version 1.0
 */
public class ListeSimplementChainee<T> implements Iterable<T>{
    /**
     * tete pointe toujours sur le 1er element
     */
    public Maillon<T> tete;
    /**
     * tail pointe toujours sur le dernier element
     */
    public Maillon<T> tail;
    /**
     * nombre de Maillon contenu dans la liste
     */
    private int nombreMaillon;
    /**
     * constructeur vide
     */
    public ListeSimplementChainee() {
        CelluleVivante c = new CelluleVivante();
        Maillon m = new Maillon(c);
        this.tete = m;
        this.tail = this.tete;
        this.nombreMaillon = 0;
    }
    /**
     * Permet d'ajouter un Maillon a la fin de la liste
     * prends correctement en compte la tete qui est deja creer lors du constructeur
     * @param maillonAjouter Maillon a ajouter
     */
    public void ajouterFinListeEtRemplacerTete(Maillon<T> maillonAjouter) {
        Maillon<T> tampon;
        tampon = new Maillon(maillonAjouter.getElement());

        if (this.nombreMaillon == 0) {
            this.tete = tampon;
            this.tail = this.tete;
        } else {
            if (this.nombreMaillon == 1) {
                this.tete.suivant = tampon;
                this.tail = this.tete.suivant;
            } else {
                this.tail.suivant = tampon;
                this.tail = this.tail.suivant;
            }
        }
        this.nombreMaillon++;
    }
    /**
     * Permet d'ajouter un maillon dans la bonne place, au pire O(n)
     * @param maillonAjouter maillon a ajouter
     * @param score score du maillon a ajouter
     */
    public void ajouterTrierDansOrdre(Maillon<T> maillonAjouter, int score){//pas generique que ici
        Maillon<CelluleVivante> m, tampon;
        tampon = new Maillon(maillonAjouter.getElement());
        m = (Maillon<CelluleVivante>) this.tete;

        while(m != null) {
            if ((tampon.valeur.getNumLigne() == m.valeur.getNumLigne()) && (tampon.valeur.getNumColonne() == m.valeur.getNumColonne())) { // si meme ligne meme colonne
                m.valeur.setScore(m.valeur.getScore() + score);
                break;
            }
            else {
                if (m.suivant != null) {
                    if (tampon.valeur.getNumLigne() == m.suivant.valeur.getNumLigne() && tampon.valeur.getNumColonne() < m.suivant.valeur.getNumColonne()) { //ajout apres
                        tampon.suivant = m.suivant;
                        m.suivant = tampon;
                        this.nombreMaillon++;
                        break;
                    }
                    if (tampon.valeur.getNumLigne() < m.suivant.valeur.getNumLigne()) { //different ligne
                        tampon.suivant = m.suivant;
                        m.suivant = tampon;
                        this.nombreMaillon++;
                        break;
                    }
                }
                if(m.suivant == null){//le dernier
                    tampon.suivant = m.suivant;
                    m.suivant = tampon;
                    this.nombreMaillon++;
                    break;
                }
                m = m.suivant;
            }
        }
    }

    /**
     * permet de return un objet ListeSimplementChaineeIterator
     */
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new ListeSimplementChaineeIterator() ;
    }
    /**
     * class interne qui implements Iterator
     * @author maxime
     */
    class ListeSimplementChaineeIterator implements Iterator<T> {
        Maillon<T> courant = null;
        @Override
        public boolean hasNext() {
            if(courant == null && tete != null) {
                return true;
            }
            else if(courant != null) {
                return courant.suivant != null;
            }
            return false;
        }
        @Override
        public T next() {
            if(courant == null && tete != null) {
                System.out.print("b");
                courant = tete;
                return tete.getElement(); //getElement
            }
            else if (courant != null) {
                System.out.print("c");
                courant = courant.suivant;
                return courant.getElement();
            }
            throw new NoSuchElementException();
        }
        @Override
        public void remove() {
        }
    }


    public int getNombreMaillon() {
        return nombreMaillon;
    }
}

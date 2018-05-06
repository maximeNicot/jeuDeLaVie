package test;

import junit.framework.TestCase;
import main.CelluleVivante;
import main.JeuInfinie;
import main.ListeSimplementChainee;
import main.Maillon;
import org.junit.Test;


public class JeuInfinieTest extends TestCase{
    ListeSimplementChainee listeSimplementChainee = new ListeSimplementChainee();
    JeuInfinie jeuInfinie = new JeuInfinie(listeSimplementChainee);

    /**
     * Test la methode creerJeuInitial, en verifiant le nombre de Maillon ainsi que les valeurs de la tete
     */
    @Test
    public void testJeuInfinieInitial(){
        jeuInfinie.creerJeuInitial("fichierLif\\LifTest");
        // .*.
        // .*.
        // .*.
        //test nombre Maillon dans la liste du Jeu
        assertEquals(3, jeuInfinie.getListeJeu().getNombreMaillon());
        Maillon<CelluleVivante> maillonActual = jeuInfinie.getListeJeu().tete;
        Maillon<CelluleVivante> maillonExpected = new Maillon<>(new CelluleVivante(1,2));
        //test de la tete
        assertEquals(maillonActual.getElement().getNumLigne(), maillonExpected.getElement().getNumLigne());
        assertEquals(maillonActual.getElement().getNumColonne(), maillonExpected.getElement().getNumColonne());
    }
    /**
     * Test la methode jeuEtapeSuivante, en verifiant le nombre de Maillon ainsi que les valeurs de la tete
     */
    @Test
    public void testJeuInfinieEtapeSuivante(){
        jeuInfinie.creerJeuInitial("fichierLif\\LifTest");
        jeuInfinie.setListeJeu(jeuInfinie.jeuEtapeSuivante());
        // ...
        // ***
        // ...
        //test nombre Maillon dans la liste du Jeu
        assertEquals(3, jeuInfinie.getListeJeu().getNombreMaillon());
        Maillon<CelluleVivante> maillonActual = jeuInfinie.getListeJeu().tete;
        Maillon<CelluleVivante> maillonExpected = new Maillon<>(new CelluleVivante(2,1));
        //test de la tete
        assertEquals(maillonActual.getElement().getNumLigne(), maillonExpected.getElement().getNumLigne());
        assertEquals(maillonActual.getElement().getNumColonne(), maillonExpected.getElement().getNumColonne());
    }
}

package test;

import junit.framework.TestCase;
import main.*;
import org.junit.Test;


public class JeuBordTest extends TestCase{
    ListeSimplementChainee listeSimplementChainee = new ListeSimplementChainee();
    JeuBord jeuBord = new JeuBord(listeSimplementChainee);

    /**
     * Test la methode creerJeuInitial, en verifiant le nombre de Maillon ainsi que les valeurs de la tete
     */
    @Test
    public void testJeuBordInitial(){
        jeuBord.creerJeuInitial("fichierLif\\LifTest");
        // .*.
        // .*.
        // .*.
        assertEquals(3, jeuBord.getListeJeu().getNombreMaillon());
        Maillon<CelluleVivante> maillonActual = jeuBord.getListeJeu().tete;
        Maillon<CelluleVivante> maillonExpected = new Maillon<>(new CelluleVivante(1,2));
        //test de la tete
        assertEquals(maillonExpected.getElement().getNumLigne(), maillonActual.getElement().getNumLigne());
        assertEquals(maillonExpected.getElement().getNumColonne(), maillonActual.getElement().getNumColonne());
    }
    /**
     * Test la methode jeuEtapeSuivante, en verifiant le nombre de Maillon ainsi que les valeurs de la tete
     */
    @Test
    public void testJeuBordEtapeSuivante(){
        jeuBord.creerJeuInitial("fichierLif\\LifTest");
        JeuBord jeuBord2 = new JeuBord(jeuBord.jeuEtapeSuivante());
        jeuBord2.setTabInt(jeuBord.getTabInt());
        // ...
        // ***
        // ...
        assertEquals(3, jeuBord2.getListeJeu().getNombreMaillon());
        Maillon<CelluleVivante> maillonActual = jeuBord2.getListeJeu().tete;
        Maillon<CelluleVivante> maillonExpected = new Maillon<>(new CelluleVivante(2,1));
        //test de la tete
        assertEquals(maillonExpected.getElement().getNumLigne(), maillonActual.getElement().getNumLigne());
        assertEquals(maillonExpected.getElement().getNumColonne(), maillonActual.getElement().getNumColonne());
    }
}
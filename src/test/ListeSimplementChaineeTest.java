package test;

import junit.framework.TestCase;
import main.ListeSimplementChainee;
import main.Maillon;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ListeSimplementChaineeTest extends TestCase {
    ListeSimplementChainee<Integer> actuel = new ListeSimplementChainee();

    /**
     * Test la methode ajouterFinListeEtRemplacerTete, en verifiant le nombre de Maillon
     */
    @Test
    public void testAjouterFinListeEtRemplacerTete() {
        actuel.ajouterFinListeEtRemplacerTete(new Maillon(1));
        actuel.ajouterFinListeEtRemplacerTete(new Maillon(2));
        actuel.ajouterFinListeEtRemplacerTete(new Maillon(3));
        assertEquals(3, actuel.getNombreMaillon());
    }
}
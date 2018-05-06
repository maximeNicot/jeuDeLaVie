package test;

import junit.framework.TestCase;
import main.EcrireDansFichier;
import org.junit.Test;


public class EcrireDansFichierTest extends TestCase {
    EcrireDansFichier Fichier = new EcrireDansFichier();

    /**
     * Test la methode ecrireDansHTML et lireDansHTML
     */
    @Test
    public void testEcrireLireDansHTML() {
        Fichier.effacerFichierTexte();
        Fichier.ecrireDansHTML("TEST");
        String s = Fichier.lireDansHTML();
        assertEquals(s,"TEST");
    }
}
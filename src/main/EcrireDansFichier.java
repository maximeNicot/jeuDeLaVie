package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

/**
 * permet d'écrire ou lire dans un fichier txt
 */
public class EcrireDansFichier {
    /**
     * Constructeur vide
     */
    public EcrireDansFichier() {
    }
    /**
     * Ecrit dans le fichier html le string
     * @param string à ecrire dans le fichier
     */
    public void ecrireDansHTML(String string){
        try {
            FileWriter fileWriter = new FileWriter("fichierHTML\\fichier.txt",true);
            fileWriter.write(string);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Met le contenu du fichier txt dans un string
     * @return Le string contenant les caracteres du fichier txt
     */
    public String lireDansHTML(){
        String s = "";
        Scanner sc = null;
        try {
            sc = new Scanner(new File("fichierHTML\\fichier.txt"));
            while (sc.hasNextLine()) {
                for (char c : sc.next().toCharArray()) {
                    s = s + c;
                    }
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    /**
     * Permet pour chaque fichier dans le repertoire d'appeller la methode ecrireFichierMonde
     * @param pathRepertoire Nom du repertoire
     * @param args1 Duree max pour determiner la configuration
     * @param monde Infinie ou Bord
     */
    public void nomFichierDansRepertoire(String pathRepertoire, String args1, int monde) {
        effacerFichierTexte();
        File folder = new File(pathRepertoire);
        File[] listOfFiles = folder.listFiles();

        ecrireDansHTML("<!DOCTYPE html>\n" +
                "<html>\n" +
                "     <head>\n" +
                "     </head>\n" +
                "     <body>\n");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (monde == 1) {
                    ecrireFichierMondeInfinie(pathRepertoire, listOfFiles[i].getName(), args1);
                } else if (monde == 2) {
                    ecrireFichierMondeBord(pathRepertoire, listOfFiles[i].getName(), args1);
                }
            }
        }
        ecrireDansHTML("     </body>\n" +
                "</html>");
    }
    /**
     * Permet d'ecrire le resultat du fichier Lif dans le fichier html
     * @param pathRepertoire Nom du repertoir
     * @param pathFichier Nom du fichier
     * @param args1 Duree max pour determiner la configuration
     */
    private void ecrireFichierMondeInfinie(String pathRepertoire, String pathFichier, String args1){
        ListeSimplementChainee listeVide = new ListeSimplementChainee();
        JeuInfinie jeuInfinie= new JeuInfinie(listeVide);
        jeuInfinie.creerJeuInitial(pathRepertoire+ "\\"+pathFichier);
        System.out.println(jeuInfinie.toString());
        System.out.println("DEBUT");
        final ListeSimplementChainee[] resultat = new ListeSimplementChainee[1];
        final ListeSimplementChainee[] listePeriode = new ListeSimplementChainee[1];
        final ListeSimplementChainee[] etapeSuivante = new ListeSimplementChainee[1];
        resultat[0] = jeuInfinie.jeuEtapeSuivante();
        listePeriode[0] = jeuInfinie.jeuEtapeSuivante();
        final JeuInfinie jeux = new JeuInfinie(resultat[0]);
        final JeuInfinie jeuxPeriode = new JeuInfinie(listePeriode[0]);
        final JeuInfinie jeuxEtapeSuivante = new JeuInfinie(etapeSuivante[0]);
        jeux.stringPourNaissanceSurvieAvantBarre = jeuInfinie.stringPourNaissanceSurvieAvantBarre;
        jeux.stringPourNaissanceSurvieApresBarre = jeuInfinie.stringPourNaissanceSurvieApresBarre;
        jeuxEtapeSuivante.stringPourNaissanceSurvieAvantBarre = jeuInfinie.stringPourNaissanceSurvieAvantBarre;
        jeuxEtapeSuivante.stringPourNaissanceSurvieApresBarre = jeuInfinie.stringPourNaissanceSurvieApresBarre;
        jeuxPeriode.stringPourNaissanceSurvieAvantBarre = jeuInfinie.stringPourNaissanceSurvieAvantBarre;
        jeuxPeriode.stringPourNaissanceSurvieApresBarre = jeuInfinie.stringPourNaissanceSurvieApresBarre;
        Maillon<CelluleVivante> maillonVaisseauOuOscillateurInitial = (Maillon<CelluleVivante>) jeuInfinie.getListeJeu().tete; // sauvegarde de la tete initial
        final String[] message = {jeux.toString()};
        final Timer timer = new Timer(200, null);

        timer.addActionListener(new ActionListener() {
            int i = 0;
            boolean vitesse = false;
            boolean dejaEcrit = false;
            int arret = Integer.parseInt(args1);
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vitesse) {
                    resultat[0] = jeux.jeuEtapeSuivante();
                    jeux.setListeJeu(resultat[0]);
                    System.out.println(message[0]);
                    System.out.println("t = " + i);
                    message[0] = jeux.toString();
                    i++;
                }
                etapeSuivante[0] = jeux.jeuEtapeSuivante();
                jeuxEtapeSuivante.setListeJeu(etapeSuivante[0]);
                if (jeux.toString().equals(jeuxEtapeSuivante.toString())) {
                    if (jeux.getListeJeu().getNombreMaillon() == 0) {
                        ecrireDansHTML("           " +pathFichier + ": configuration MORTE\n");
                        timer.stop();

                    } else {
                        ecrireDansHTML("           " +pathFichier + ": configuration STABLE\n");
                        timer.stop();
                    }
                }
                else{
                    listePeriode[0] = jeuxPeriode.jeuEtapeSuivante();
                    jeuxPeriode.setListeJeu(listePeriode[0]);
                    if (jeux.toString().equals(jeuxPeriode.toString())) {
                        Maillon<CelluleVivante> maillonVaisseauOuOscillateur = (Maillon<CelluleVivante>) jeux.getListeJeu().tete;
                        if (maillonVaisseauOuOscillateurInitial.getElement().getNumLigne() == maillonVaisseauOuOscillateur.getElement().getNumLigne() &&
                                maillonVaisseauOuOscillateurInitial.getElement().getNumColonne() == maillonVaisseauOuOscillateur.getElement().getNumColonne()) {
                            ecrireDansHTML("           " + pathFichier + ": configuration OSCILLATEUR -> ");
                        } else {
                            ecrireDansHTML("           " +pathFichier + ": configuration VAISSEAU -> ");
                        }
                        i++;
                        ecrireDansHTML("PERIODE: " + i + "\n");
                        dejaEcrit = true;
                        timer.stop();
                    }
                }
                if (vitesse)
                    vitesse = false;
                else
                    vitesse = true;
                if (i == arret && !dejaEcrit ) {
                    ecrireDansHTML("           " + pathFichier + ": configuration INCONNU\n");
                    timer.stop();
                }
            }
        });
        timer.start();
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }
    /**
     * Permet d'ecrire le resultat du fichier Lif dans le fichier html
     * @param pathRepertoire Nom du repertoir
     * @param pathFichier Nom du fichier
     * @param args1 Duree max pour determiner la configuration
     */
    private void ecrireFichierMondeBord(String pathRepertoire, String pathFichier, String args1){
        ListeSimplementChainee listeVide = new ListeSimplementChainee();
        JeuBord jeuBord= new JeuBord(listeVide);
        jeuBord.creerJeuInitial(pathRepertoire+ "\\"+pathFichier);
        System.out.println(jeuBord.toString());
        System.out.println("DEBUT");
        final ListeSimplementChainee[] resultat = new ListeSimplementChainee[1];
        final ListeSimplementChainee[] listePeriode = new ListeSimplementChainee[1];
        final ListeSimplementChainee[] etapeSuivante = new ListeSimplementChainee[1];
        resultat[0] = jeuBord.jeuEtapeSuivante();
        listePeriode[0] = jeuBord.jeuEtapeSuivante();
        final JeuBord jeuxBord = new JeuBord(resultat[0]);
        final JeuBord jeuxPeriodeBord = new JeuBord(listePeriode[0]);
        final JeuBord jeuxEtapeSuivanteBord = new JeuBord(etapeSuivante[0]);
        jeuxBord.stringPourNaissanceSurvieAvantBarre = jeuBord.stringPourNaissanceSurvieAvantBarre;
        jeuxBord.stringPourNaissanceSurvieApresBarre = jeuBord.stringPourNaissanceSurvieApresBarre;
        jeuxEtapeSuivanteBord.stringPourNaissanceSurvieAvantBarre = jeuBord.stringPourNaissanceSurvieAvantBarre;
        jeuxEtapeSuivanteBord.stringPourNaissanceSurvieApresBarre = jeuBord.stringPourNaissanceSurvieApresBarre;
        jeuxPeriodeBord.stringPourNaissanceSurvieAvantBarre = jeuBord.stringPourNaissanceSurvieAvantBarre;
        jeuxPeriodeBord.stringPourNaissanceSurvieApresBarre = jeuBord.stringPourNaissanceSurvieApresBarre;
        jeuxBord.setTabInt(jeuBord.getTabInt());
        jeuxPeriodeBord.setTabInt(jeuBord.getTabInt());
        jeuxEtapeSuivanteBord.setTabInt(jeuBord.getTabInt());
        Maillon<CelluleVivante> maillonVaisseauOuOscillateurInitial = (Maillon<CelluleVivante>) jeuBord.getListeJeu().tete; // sauvegarde de la tete initial
        final String[] message = {jeuxBord.toString()};
        final Timer timer = new Timer(200, null);

        timer.addActionListener(new ActionListener() {
            int i = 0;
            boolean vitesse = false;
            boolean dejaEcrit = false;
            int arret = Integer.parseInt(args1);
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vitesse) {
                    resultat[0] = jeuxBord.jeuEtapeSuivante();
                    jeuxBord.setListeJeu(resultat[0]);
                    System.out.println(message[0]);
                    System.out.println("t = " + i);
                    message[0] = jeuxBord.toString();
                    i++;
                }
                etapeSuivante[0] = jeuxBord.jeuEtapeSuivante();
                jeuxEtapeSuivanteBord.setListeJeu(etapeSuivante[0]);
                if (jeuxBord.toString().equals(jeuxEtapeSuivanteBord.toString())) {
                    if (jeuxBord.getListeJeu().getNombreMaillon() == 0) {
                        ecrireDansHTML("           " + pathFichier + ": configuration MORTE\n");
                        timer.stop();

                    } else {
                        ecrireDansHTML("           " + pathFichier + ": configuration STABLE\n");
                        timer.stop();
                    }
                }
                else{
                    listePeriode[0] = jeuxPeriodeBord.jeuEtapeSuivante();
                    jeuxPeriodeBord.setListeJeu(listePeriode[0]);
                    if (jeuxBord.toString().equals(jeuxPeriodeBord.toString())) {
                        JeuBord jeuBord2 = new JeuBord(jeuxBord.getListeJeu());
                        jeuBord2.setTabInt(jeuBord.getTabInt());
                        Maillon<CelluleVivante> maillonVaisseauOuOscillateurBord = (Maillon<CelluleVivante>) jeuBord2.getListeJeu().tete;
                        if (maillonVaisseauOuOscillateurInitial.getElement().getNumLigne() == maillonVaisseauOuOscillateurBord.getElement().getNumLigne() &&
                                maillonVaisseauOuOscillateurInitial.getElement().getNumColonne() == maillonVaisseauOuOscillateurBord.getElement().getNumColonne()) {
                            ecrireDansHTML("           " + pathFichier + ": configuration OSCILLATEUR -> ");
                        } else {
                            ecrireDansHTML("           " + pathFichier + ": configuration VAISSEAU -> ");
                        }
                        i++;
                        ecrireDansHTML("PERIODE: " + i + "\n");
                        dejaEcrit = true;
                        timer.stop();
                    }
                }
                if (vitesse)
                    vitesse = false;
                else
                    vitesse = true;
                if (i == arret && !dejaEcrit ) {
                    ecrireDansHTML("           " + pathFichier + ": configuration INCONNU\n");
                    timer.stop();
                }
            }
        });
        timer.start();
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }
    /**
     * Permet d'effacer le contenu du fichier
     */
    public void effacerFichierTexte(){
        try {
            FileWriter fileWriter = new FileWriter("fichierHTML\\fichier.txt",false);// false pour test reussir
            fileWriter.write("");
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
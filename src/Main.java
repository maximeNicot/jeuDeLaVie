
import main.*;

import java.io.*;
import javax.swing.Timer;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
/*
 java -jar out/artifacts/JeuDeLaVie_jar/JeuDeLaVie.jar -s 1 fichierLif\\Lif
java -jar out/artifacts/JeuDeLaVie_jar/JeuDeLaVie.jar -c 20 fichierLif\\LifOscillateur15
java -jar out/artifacts/JeuDeLaVie_jar/JeuDeLaVie.jar -w 20 fichierLif
java -jar out/artifacts/JeuDeLaVie_jar/JeuDeLaVie.jar -s 4 fichierLif\\LifStable
java -jar out/artifacts/JeuDeLaVie_jar/JeuDeLaVie.jar -w 1 fichierLif
java -jar C:\Users\maxim\OneDrive\Bureau\JeuDeLaVie.jar -test 20 k
java -jar C:\Users\maxim\OneDrive\Bureau\JeuDeLaVie.jar -s 2 C:\Users\maxim\OneDrive\Bureau\fichierLif\Lif
 */

/**
 * class Main
 */
public class Main {
    public static void main(String[] args) {
        ListeSimplementChainee listeJeu = new ListeSimplementChainee();
        JeuInfinie jeuInfinie = new JeuInfinie(listeJeu);
        JeuBord jeuBord = new JeuBord(listeJeu);
        EcrireDansFichier ecrireDansFichier = new EcrireDansFichier();


        if (args.length == 1) {
            String args0 = args[0];

            if (args0.equals("-name")) {
                //ecrireDansFichier.ecrireDansHTML("PWET");
                System.out.println("Maxime NICOT");
            }

            if (args0.equals("-h")) {
                System.out.println("\n-name : affiche vos noms et prenoms.\n");
                System.out.println("-h : rapelle la liste des options du programme.\n");
                System.out.println("-s d fichier.lif :  execute  une  simulation  du  jeu d'une duree d affichant les configurations du jeu avec le numéro de génération.\n");
                System.out.println("-c max fichier.lif calcule  le  type  d'evolution  du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul.\n");
                System.out.println("-w max dossier calcule le type d'evolution de tous les jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier html.\n");
            }
        }


        if (args.length == 3) {
            String args0 = args[0];
            final String args1 = args[1];
            String path = args[2];
            System.out.println("Choississez votre monde: ");
            System.out.println("1 pour monde infinie");
            System.out.println("2 pour monde avec frontières");
            Scanner reader = new Scanner(System.in);
            int numeroMonde = reader.nextInt();


            if (args0.equals("-s")) {
                switch (numeroMonde) {
                    case 1:// infinie
                        jeuInfinie.creerJeuInitial(path);
                        System.out.println(jeuInfinie.toString());
                        System.out.println("DEBUT");
                        final ListeSimplementChainee[] resultat = new ListeSimplementChainee[1];
                        resultat[0] = jeuInfinie.jeuEtapeSuivante();
                        final JeuInfinie jeux = new JeuInfinie(resultat[0]);
                        jeux.stringPourNaissanceSurvieAvantBarre = jeuInfinie.stringPourNaissanceSurvieAvantBarre;
                        jeux.stringPourNaissanceSurvieApresBarre = jeuInfinie.stringPourNaissanceSurvieApresBarre;
                        final String[] message = {jeux.toString()};
                        final Timer timer = new Timer(200, null);

                        timer.addActionListener(new ActionListener() {
                            int i = 0;
                            int arret = Integer.parseInt(args1);
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                resultat[0] = jeux.jeuEtapeSuivante();
                                jeux.setListeJeu(resultat[0]);
                                System.out.println(message[0]);
                                System.out.println("t = " + i);
                                message[0] = jeux.toString();
                                i++;
                                if (i == arret) {
                                    timer.stop();
                                    System.exit(0);
                                }
                            }
                        });
                        timer.start();
                        try {
                            System.in.read();
                        } catch (IOException e) {
                        }
                        break;

                    case  2:// frontiere
                        jeuBord.creerJeuInitial(path);
                        System.out.println(jeuBord.toString());
                        System.out.println("DEBUT");
                        final ListeSimplementChainee[] resultatBord = new ListeSimplementChainee[1];

                        resultatBord[0] = jeuBord.jeuEtapeSuivante();
                        final JeuBord jeuxBord = new JeuBord(resultatBord[0]);
                        jeuxBord.setTabInt(jeuBord.getTabInt());
                        jeuxBord.stringPourNaissanceSurvieAvantBarre = jeuBord.stringPourNaissanceSurvieAvantBarre;
                        jeuxBord.stringPourNaissanceSurvieApresBarre = jeuBord.stringPourNaissanceSurvieApresBarre;
                        final String[] messageBord = {jeuxBord.toString()};
                        final Timer timerBord = new Timer(200, null);
                        timerBord.addActionListener(new ActionListener() {
                            int i = 0;
                            int arret = Integer.parseInt(args1);
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                resultatBord[0] = jeuxBord.jeuEtapeSuivante();
                                jeuxBord.setListeJeu(resultatBord[0]);
                                System.out.println(messageBord[0]);
                                System.out.println("t = " + i);
                                messageBord[0] = jeuxBord.toString();
                                i++;
                                if (i == arret) {
                                    timerBord.stop();
                                    System.exit(0);
                                }
                            }
                        });
                        timerBord.start();
                        try {
                            System.in.read();
                        } catch (IOException e) {
                        }
                        break;
                }
            }


            if (args0.equals("-c")) {
                switch (numeroMonde) {
                    case 1://Infinie
                        jeuInfinie.creerJeuInitial(path);
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
                                        System.out.println("CONFIGURATION MORTE\n");
                                        System.out.println(message[0]);
                                        timer.stop();
                                        System.exit(0);
                                    } else {
                                        System.out.println("CONFIGURATION STABLE\n");
                                        System.out.println(message[0]);
                                        timer.stop();
                                        System.exit(0);
                                    }
                                }
                                listePeriode[0] = jeuxPeriode.jeuEtapeSuivante();
                                jeuxPeriode.setListeJeu(listePeriode[0]);
                                if (jeux.toString().equals(jeuxPeriode.toString())) {
                                    Maillon<CelluleVivante> maillonVaisseauOuOscillateur = (Maillon<CelluleVivante>) jeux.getListeJeu().tete;
                                    if(maillonVaisseauOuOscillateurInitial.getElement().getNumLigne() == maillonVaisseauOuOscillateur.getElement().getNumLigne() &&
                                            maillonVaisseauOuOscillateurInitial.getElement().getNumColonne() == maillonVaisseauOuOscillateur.getElement().getNumColonne()){
                                    }
                                    else{
                                        System.out.println("CONFIGURATION VAISSEAU\n");
                                    }
                                    i++;
                                    System.out.println("PERIODE: " + i + "\n");
                                    timer.stop();
                                    System.exit(0);
                                }
                                if (vitesse)
                                    vitesse = false;
                                else
                                    vitesse = true;
                                if (i == arret) {
                                    timer.stop();
                                    System.exit(0);
                                }
                            }
                        });
                        timer.start();
                        try {
                            System.in.read();
                        } catch (IOException e) {
                        }
                        break;

                    case 2://Frontiere
                        jeuBord.creerJeuInitial(path);
                        System.out.println(jeuBord.toString());
                        System.out.println("DEBUT");
                        final ListeSimplementChainee[] resultatBord = new ListeSimplementChainee[1];
                        final ListeSimplementChainee[] listePeriodeBord = new ListeSimplementChainee[1];
                        final ListeSimplementChainee[] etapeSuivanteBord = new ListeSimplementChainee[1];
                        resultatBord[0] = jeuBord.jeuEtapeSuivante();
                        listePeriodeBord[0] = jeuBord.jeuEtapeSuivante();
                        final JeuBord jeuxBord = new JeuBord(resultatBord[0]);
                        final JeuBord jeuxPeriodeBord = new JeuBord(listePeriodeBord[0]);
                        final JeuBord jeuxEtapeSuivanteBord = new JeuBord(etapeSuivanteBord[0]);
                        jeuxBord.stringPourNaissanceSurvieAvantBarre = jeuBord.stringPourNaissanceSurvieAvantBarre;
                        jeuxBord.stringPourNaissanceSurvieApresBarre = jeuBord.stringPourNaissanceSurvieApresBarre;
                        jeuxEtapeSuivanteBord.stringPourNaissanceSurvieAvantBarre = jeuBord.stringPourNaissanceSurvieAvantBarre;
                        jeuxEtapeSuivanteBord.stringPourNaissanceSurvieApresBarre = jeuBord.stringPourNaissanceSurvieApresBarre;
                        jeuxPeriodeBord.stringPourNaissanceSurvieAvantBarre = jeuBord.stringPourNaissanceSurvieAvantBarre;
                        jeuxPeriodeBord.stringPourNaissanceSurvieApresBarre = jeuBord.stringPourNaissanceSurvieApresBarre;
                        Maillon<CelluleVivante> maillonVaisseauOuOscillateurInitialBord = (Maillon<CelluleVivante>) jeuInfinie.getListeJeu().tete; // sauvegarde de la tete initial
                        final String[] messageBord = {jeuxBord.toString()};
                        final Timer timerBord = new Timer(200, null);

                        jeuxBord.setTabInt(jeuBord.getTabInt());
                        jeuxPeriodeBord.setTabInt(jeuBord.getTabInt());
                        jeuxEtapeSuivanteBord.setTabInt(jeuBord.getTabInt());
                        timerBord.addActionListener(new ActionListener() {
                            int i = 0;
                            boolean vitesse = false;
                            int arret = Integer.parseInt(args1);
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (vitesse) {
                                    resultatBord[0] = jeuxBord.jeuEtapeSuivante();
                                    jeuxBord.setListeJeu(resultatBord[0]);
                                    System.out.println(messageBord[0]);
                                    System.out.println("t = " + i);
                                    messageBord[0] = jeuxBord.toString();
                                    i++;
                                }
                                etapeSuivanteBord[0] = jeuxBord.jeuEtapeSuivante();
                                jeuxEtapeSuivanteBord.setListeJeu(etapeSuivanteBord[0]);
                                if (jeuxBord.toString().equals(jeuxEtapeSuivanteBord.toString())) {
                                    if (jeuxBord.getListeJeu().getNombreMaillon() == 0) {
                                        System.out.println("CONFIGURATION MORTE\n");
                                        System.out.println(messageBord[0]);
                                        timerBord.stop();
                                        System.exit(0);
                                    } else {
                                        System.out.println("CONFIGURATION STABLE\n");
                                        System.out.println(messageBord[0]);
                                        timerBord.stop();
                                        System.exit(0);
                                    }
                                }
                                listePeriodeBord[0] = jeuxPeriodeBord.jeuEtapeSuivante();
                                jeuxPeriodeBord.setListeJeu(listePeriodeBord[0]);
                                if (jeuxBord.toString().equals(jeuxPeriodeBord.toString())) {
                                    JeuBord jeuBord2 = new JeuBord(jeuxBord.getListeJeu());
                                    jeuBord2.setTabInt(jeuBord.getTabInt());
                                    Maillon<CelluleVivante> maillonVaisseauOuOscillateurBord = (Maillon<CelluleVivante>) jeuBord2.getListeJeu().tete;
                                    if(maillonVaisseauOuOscillateurInitialBord.getElement().getNumLigne() == maillonVaisseauOuOscillateurBord.getElement().getNumLigne() &&
                                            maillonVaisseauOuOscillateurInitialBord.getElement().getNumColonne() == maillonVaisseauOuOscillateurBord.getElement().getNumColonne()){
                                        System.out.println("CONFIGURATION OSCILLATEUR\n");
                                    }
                                    else{
                                        System.out.println("CONFIGURATION VAISSEAU\n");
                                    }
                                    i++;
                                    System.out.println("PERIODE: " + i + "\n");
                                    timerBord.stop();
                                    System.exit(0);
                                }
                                if (vitesse)
                                    vitesse = false;
                                else
                                    vitesse = true;
                                if (i == arret) {
                                    timerBord.stop();
                                    System.exit(0);
                                }
                            }
                        });
                        timerBord.start();
                        try {
                            System.in.read();
                        } catch (IOException e) {
                        }
                        break;
                }
            }

            if (args0.equals("-w")){
                ecrireDansFichier.nomFichierDansRepertoire(path, args1, numeroMonde);
            }
        }
    }
}
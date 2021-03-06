package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Contient un objet ListeSimplementChainee
 */
public class JeuInfinie extends Jeu {
    private ListeSimplementChainee listeJeu;
    /**
     * Contient les chiffres avant le / dans le fichier lif
     */
    public String stringPourNaissanceSurvieAvantBarre;
    /**
     * Contient les chiffres apres le / dans le fichier lif
     */
    public String stringPourNaissanceSurvieApresBarre;
    /**
     * Constructeur JeuInfinie
     * @param listeJeu liste qui est contenu dans JeuInfinie
     */
    public JeuInfinie(ListeSimplementChainee listeJeu) {
        super(listeJeu);
        this.listeJeu = listeJeu;
        this.stringPourNaissanceSurvieAvantBarre = "";
        this.stringPourNaissanceSurvieApresBarre = "";
    }
    /**
     * Permet a partir du fichier lif de creer la liste initial
     * @param path chemin d'accces du fichier lif
     */
    public void creerJeuInitial(String path) {
        Scanner sc = null;
        int numeroLigne = 0;
        int numeroColonne;
        CelluleVivante celluleVivante;
        Maillon<CelluleVivante> m;
        String stringNaissanceSurvieCellule = "";
        String stringNaissanceSurvieCellule2 = "";
        boolean debutMatriceLif = false;
        boolean b = false;

        try {
            try {
                sc = new Scanner(new File(path));
                while (sc.hasNextLine()) {
                    numeroLigne++;
                    numeroColonne = 0;
                    for (char c : sc.next().toCharArray()) {
                        numeroColonne++;
                        if(c == '*' || c == '.'){
                            if(!debutMatriceLif){
                                numeroLigne = 1;
                                numeroColonne = 1;
                                debutMatriceLif = true;
                            }
                            if (c == '*') {
                                celluleVivante = new CelluleVivante(numeroLigne, numeroColonne, 10);
                                m = new Maillon(celluleVivante);
                                this.listeJeu.ajouterFinListeEtRemplacerTete(m);
                            }
                        }
                        if( c == 'R'){
                            for (char carac : sc.next().toCharArray()) {
                                if(carac == '/'){
                                    b = true;
                                }
                                else {
                                    if(!b){
                                        stringNaissanceSurvieCellule = stringNaissanceSurvieCellule + carac;
                                    }
                                    else{
                                        stringNaissanceSurvieCellule2 = stringNaissanceSurvieCellule2 + carac;
                                    }
                                }
                            }
                        }
                    }
                }
                this.stringPourNaissanceSurvieAvantBarre = stringNaissanceSurvieCellule;
                this.stringPourNaissanceSurvieApresBarre = stringNaissanceSurvieCellule2;
            } finally {
                if (sc != null)
                    sc.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * permet de creer la liste suivante
     * @return la liste suivante
     */
    public ListeSimplementChainee jeuEtapeSuivante(){ // a mettre dans liste
        ListeSimplementChainee[] listeTabVoisins = new ListeSimplementChainee[8];
        ListeSimplementChainee addition = new ListeSimplementChainee();
        ListeSimplementChainee resultat = new ListeSimplementChainee();
        Maillon<CelluleVivante> m;

        m = listeJeu.tete;
        for(int i = 0; i<listeTabVoisins.length ; i++){
            listeTabVoisins[i] = new ListeSimplementChainee();
        }
        while(m != null){
            Maillon m1 = new Maillon<>(new CelluleVivante(m.valeur.getNumLigne() - 1, m.valeur.getNumColonne() - 1, 1));
            addition.ajouterTrierDansOrdre(m1, 1);
            Maillon m2 = (new Maillon<>(new CelluleVivante(m.valeur.getNumLigne() - 1, m.valeur.getNumColonne(), 1)));
            addition.ajouterTrierDansOrdre(m2, 1);
            Maillon m3 = (new Maillon<>(new CelluleVivante(m.valeur.getNumLigne() - 1, m.valeur.getNumColonne() + 1, 1)));
            addition.ajouterTrierDansOrdre(m3, 1);
            Maillon m4 = (new Maillon<>(new CelluleVivante(m.valeur.getNumLigne(), m.valeur.getNumColonne() - 1, 1)));
            addition.ajouterTrierDansOrdre(m4, 1);
            Maillon m5 = (new Maillon<>(new CelluleVivante(m.valeur.getNumLigne(), m.valeur.getNumColonne() + 1, 1)));
            addition.ajouterTrierDansOrdre(m5, 1);
            Maillon m6 = (new Maillon<>(new CelluleVivante(m.valeur.getNumLigne() + 1, m.valeur.getNumColonne() - 1, 1)));
            addition.ajouterTrierDansOrdre(m6, 1);
            Maillon m7 = new Maillon<>(new CelluleVivante(m.valeur.getNumLigne() + 1, m.valeur.getNumColonne(), 1));
            addition.ajouterTrierDansOrdre(m7, 1);
            Maillon m8 = (new Maillon<>(new CelluleVivante(m.valeur.getNumLigne() + 1, m.valeur.getNumColonne() + 1, 1)));
            addition.ajouterTrierDansOrdre(m8, 1);
            m = m.suivant;
        }

        m = listeJeu.tete;
        while (m!= null){
            addition.ajouterTrierDansOrdre(m, 10);
            m = m.suivant;
        }

        Character[] nombrePourSurvieNaissanceTab = new Character[stringPourNaissanceSurvieAvantBarre.length()];
        for(int i = 0 ; i<nombrePourSurvieNaissanceTab.length; i++){
            nombrePourSurvieNaissanceTab[i] = stringPourNaissanceSurvieAvantBarre.charAt(i);
        }
        Character[] nombrePourSurvieNaissanceTab2 = new Character[stringPourNaissanceSurvieApresBarre.length()];
        for(int i = 0 ; i<nombrePourSurvieNaissanceTab2.length; i++){
            nombrePourSurvieNaissanceTab2[i] = stringPourNaissanceSurvieApresBarre.charAt(i);
        }

        m = addition.tete;
        while (m != null){
            for(int i = 0 ; i<nombrePourSurvieNaissanceTab.length; i++){
                int c =  Character.getNumericValue(nombrePourSurvieNaissanceTab[i]);
                if (m.valeur.getScore() == c + 10 ) {
                    resultat.ajouterFinListeEtRemplacerTete(m);
                }
            }
            for(int i = 0 ; i<nombrePourSurvieNaissanceTab2.length; i++){
                int c =  Character.getNumericValue(nombrePourSurvieNaissanceTab2[i]);
                if (m.valeur.getScore() == c ) {
                    resultat.ajouterFinListeEtRemplacerTete(m);
                }
            }
            m = m.suivant;
        }
        return resultat;
    }
    /**
     * Permet de creer un string correspondant a la liste
     * @return le string correspondant a la liste
     */
    @Override
    public String toString() {
        String s = "";
        int espace;
        Maillon<CelluleVivante> m ;
        if(listeJeu.getNombreMaillon() != 0){
            m = listeJeu.tete;
        }
        else {
            m = null;
        }
        espace = 0;
        while(m != null){
            //System.out.println("AAA" + listeJeu.getNombreMaillon() + "AAA");
            for(int i = espace ; i< m.valeur.getNumColonne()-1; i++){
                //s = s + ".";
                s = s + " ";
            }
            //s = s +"(" + m.valeur.getNumLigne() + ", " + m.valeur.getNumColonne() + ")";
            s = s + "*";
            espace = m.valeur.getNumColonne();

            if(m.suivant != null){
                if(m.valeur.getNumLigne() != m.suivant.valeur.getNumLigne()){
                    s = s + "\n";
                    if(m.valeur.getNumLigne()+1 != m.suivant.valeur.getNumLigne()) {
                        for (int j = m.valeur.getNumLigne(); j < m.suivant.valeur.getNumLigne(); j++) {
                            s = s + "\n";
                        }
                    }
                    espace = 0;
                }
            }
            m = m.suivant;
        }
        return s;
    }


    public ListeSimplementChainee getListeJeu() {
        return listeJeu;
    }
    public void setListeJeu(ListeSimplementChainee listeJeu) {
        this.listeJeu = listeJeu;
    }
}

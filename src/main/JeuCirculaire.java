package main;

public class JeuCirculaire extends Jeu {
    public JeuCirculaire(ListeSimplementChainee listeJeu) {
        super(listeJeu);
    }


    @Override
    public void creerJeuInitial(String path) {
    }
    @Override
    public ListeSimplementChainee jeuEtapeSuivante() {
        return null;
    }
    @Override
    public String toString() {
        return null;
    }

}

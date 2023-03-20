public class Demineur extends Plateau{
    private boolean gameOver;
    private int score;

    public Demineur(int nbLignes, int nbColonnes, int pourcentage){
        super(nbLignes, nbColonnes, pourcentage);
        this.gameOver = false;
        this.score = 0;
    }

    public int getScore(){return this.score;}
    public void reveler(int x, int y){
        
    }
}

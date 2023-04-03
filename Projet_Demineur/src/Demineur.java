import java.util.Scanner;

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
        int caseAReveler = x*super.getNbColonnes()+y;
        super.lePlateau.get(caseAReveler).reveler();
    }

    public void marquer(int x, int y){
        int caseAMarquer = x*super.getNbColonnes()+y;
        super.lePlateau.get(caseAMarquer).marquer();

    }

    public boolean estGagnee(){
        for(CaseIntelligente CaseElem: super.lePlateau){
            if(CaseElem.contientUneBombe()){
                if(!CaseElem.estMarquee()){
                    return false;
                }
            }
            else{
                if(!CaseElem.estDecouverte()){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean estPerdu(){
        for(CaseIntelligente CaseElem: super.lePlateau){
            if(CaseElem.contientUneBombe()){
                if(CaseElem.estDecouverte()){
                    return true;
                }
            }
        }
        return false;
    }

    public void reset(){
        this.gameOver = false;
        this.score = 0;
    }

    public void affiche(){
        Scanner input = new Scanner(System.in);
        System.out.println("JEU DU DÉMINEUR");
        int cpt_marques = 0;
        for (CaseIntelligente c : super.lePlateau){

        }
        System.out.println("Nombre de bombes : " + super.getNbTotalBombes());
        System.out.println("Nombre de cases marquées : ");
        System.out.println("Nombre de cases découvertes : ");
        String instruction = input.nextLine();
    }

    public void nouvellePartie(){
        reset();
        new Demineur(super.getNbLignes(), super.getNbColonnes(), super.getNbTotalBombes());
    }
}
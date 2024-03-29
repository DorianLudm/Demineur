import java.util.Scanner;

public class Demineur extends Plateau{

    private boolean gameOver;
    private int score;

    /**
     * Constructeur de la classe Demineur
     * @param nbLignes le nombre de ligne du plateau du jeu
     * @param nbColonnes le nombre de colonnes du plateau du jeu
     * @param pourcentage le pourcentage de bombes qui seront dispersées dans le plateau
     */
    public Demineur(int nbLignes, int nbColonnes, int pourcentage){
        super(nbLignes, nbColonnes, pourcentage);
        this.gameOver = false;
        this.score = 0;
    }

    /**
     * Permet de retourner le score actuel de la partie
     * @return la valeur en entier du score actuel
     */
    public int getScore(){
        return this.score;
    }

    /**Révèle une case si et seulement si elle n'est pas marquée ou révélée
     * @param x la position X de la case à révéler
     * @param y la position Y de la case à révéler
    */
    public void reveler(int x, int y){
        CaseIntelligente laCase = this.getCase(x,y);
        if(super.getNbCasesDecouverte() == 0){
            if(laCase.contientUneBombe()){
                /**Enlever la bombe de la case et enlever 1 à l'attribute nb bombe */
                laCase.demine();
                super.changeNbBombes(-1);
            }
        }
        if(!laCase.estMarquee()){
            if(laCase.contientUneBombe()){
                this.gameOver = true;
            }
            laCase.reveler();
            ajouterScore(1000);
            // Si la case ne contient pas de bombe, on révèle ses voisines et si ses voisines n'en ont pas non plus, on révèle leurs voisines
            if (laCase.nombreBombesVoisines()==0 && !laCase.contientUneBombe()){
                if (x>0 && !this.getCase(x-1, y).estDecouverte() && !this.getCase(x-1, y).estMarquee() && !this.getCase(x-1, y).contientUneBombe()){
                    this.reveler(x-1, y);
                }
                if (x<this.getNbLignes()-1 && !this.getCase(x+1, y).estDecouverte() && !this.getCase(x+1, y).estMarquee() && !this.getCase(x+1, y).contientUneBombe()){
                    this.reveler(x+1, y);
                }
                if (y>0 && !this.getCase(x, y-1).estDecouverte() && !this.getCase(x, y-1).estMarquee() && !this.getCase(x, y-1).contientUneBombe()){
                    this.reveler(x, y-1);
                }
                if (y<this.getNbColonnes()-1 && !this.getCase(x, y+1).estDecouverte() && !this.getCase(x, y+1).estMarquee() && !this.getCase(x, y+1).contientUneBombe()){
                    this.reveler(x, y+1);
                }
                if (x>0 && y>0 && !this.getCase(x-1, y-1).estDecouverte() && !this.getCase(x-1, y-1).estMarquee() && !this.getCase(x-1, y-1).contientUneBombe()){
                    this.reveler(x-1, y-1);
                }
                if (x>0 && y<this.getNbColonnes()-1 && !this.getCase(x-1, y+1).estDecouverte() && !this.getCase(x-1, y+1).estMarquee() && !this.getCase(x-1, y+1).contientUneBombe()){
                    this.reveler(x-1, y+1);
                }
                if (x<this.getNbLignes()-1 && y>0 && !this.getCase(x+1, y-1).estDecouverte() && !this.getCase(x+1, y-1).estMarquee() && !this.getCase(x+1, y-1).contientUneBombe()){
                    this.reveler(x+1, y-1);
                }
                if (x<this.getNbLignes()-1 && y<this.getNbColonnes()-1 && !this.getCase(x+1, y+1).estDecouverte() && !this.getCase(x+1, y+1).estMarquee() && !this.getCase(x+1, y+1).contientUneBombe()){
                    this.reveler(x+1, y+1);
                }
            }
        }
    }

    /**
     * Incrémente le score en fonction du nombre passé en paramètre
     * @param nb 
     */
    public void ajouterScore(int nb){
        this.score += nb;
    }

    /**
     * Permet au joueur de marquer une case qu'il pense être une bombe
     * @param x la position x de la case ciblée
     * @param y la position y de la case ciblée
     */
    public void marquer(int x, int y){
        int caseAMarquer = x*super.getNbColonnes()+y;
        if (super.lePlateau.get(caseAMarquer).estMarquee()){
            super.lePlateau.get(caseAMarquer).demarquer();
        }
        else{
            super.lePlateau.get(caseAMarquer).marquer();
        }
    }

    /**
     * Gère la partie lorsqu'elle est gagnée, donc si le joueur a marqué toutes les bombes
     * @return true si la partie est gagnée, false sinon
     */
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
    
    /**
     * Gère la partie lorsqu'elle est perdue, donc si le joueur révèle une bombe
     * @return true si al partie est perdue, false sinon
     */
    public boolean estPerdue(){
        for(CaseIntelligente CaseElem: super.lePlateau){
            if(CaseElem.contientUneBombe()){
                if(CaseElem.estDecouverte()){
                    return true;
                }
            }
        }
        return false;
    }

    /**Permet de réinitialiser le jeu */
    public void reset(){
        super.reset();
        this.gameOver = false;
        this.score = 0;
    }

    /**Gère l'affichage du jeu du démineur dans le terminal */
    public void affiche(){
        System.out.println("JEU DU DEMINEUR");
        // affichage de la bordure supérieure
        System.out.print("  ");
        for (int j=0; j<this.getNbColonnes(); j++){
            System.out.print("  "+j+" ");
        }
        System.out.print(" \n");

        // affichage des numéros de ligne + cases
        System.out.print("  ┌");
        for (int j=0; j<this.getNbColonnes()-1; j++){
                System.out.print("───┬");
        }
        System.out.println("───┐");
        
        // affichage des numéros de ligne + cases
        for (int i = 0; i<this.getNbLignes(); i++){
            System.out.print(i+" ");
            for (int j=0; j<this.getNbColonnes(); j++){
                System.out.print("│ "+this.getCase(i, j).toString() + " ");
            }
            System.out.print("│\n");
            
            if (i!=this.getNbLignes()-1){
                // ligne milieu
                System.out.print("  ├");
                for (int j=0; j<this.getNbColonnes()-1; j++){
                        System.out.print("───┼");
                }
                System.out.println("───┤");
            }
        }

        // affichage de la bordure inférieure
        System.out.print("  └");
        for (int j=0; j<this.getNbColonnes()-1; j++){
                System.out.print("───┴");
        }
        System.out.println("───┘");
        
        // affichage des infos 
        System.out.println("Nombres de bombes à trouver : " + this.getNbTotalBombes());
        System.out.println("Nombres de cases marquées : " + this.getNbCasesMarquees());
        System.out.println("Score : " + this.getScore());
    }

    /**Permet de lancer une nouvelle partie */
    public void nouvellePartie(){
        this.reset();
        this.poseDesBombesAleatoirement();
        this.affiche();
        Scanner scan = new Scanner(System.in).useDelimiter("\n");

        while (!this.estPerdue() || this.estGagnee()){
            System.out.println("Entrer une instruction de la forme R 3 2 ou M 3 2\npour Révéler/Marquer la case à la ligne 3 et à la colonne 2");
            String [] s = scan.nextLine().split(" ");
            String action = s[0];
            int x = Integer.valueOf(s[1]);
            int y = Integer.valueOf(s[2]);
            if (action.equals("M") || action.equals("m"))
                this.marquer(x, y);
            else if (action.equals("R") || action.equals("r"))
                this.reveler(x, y);
            this.affiche();
        }
        if (this.gameOver){
            System.out.println("Oh !!! Vous avez perdu !");
        }
        else{
            System.out.println("Bravo !! Vous avez gagné !");   
        }
    }
}

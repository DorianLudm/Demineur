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
<<<<<<< Updated upstream
=======
        CaseIntelligente laCase = this.getCase(x,y);
        if(super.getNbCasesDecouverte() == 0){
            if(laCase.contientUneBombe()){
                /**Enlever la bombe de la case et enlever 1 à l'attribute nb bombe */
                laCase.demine();
                super.changeNbBombes(-1);
            }
            /**Reveler toute les cases adjacentes */
            
        }
        //*else{
            if(!laCase.estMarquee()){
                if(laCase.contientUneBombe()){
                    this.gameOver = true;
                }
                laCase.reveler();
                this.score++;
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

        //* */}     
    }

    public void marquer(int x, int y){
        int caseAMarquer = x*super.getNbColonnes()+y;
        if (super.lePlateau.get(caseAMarquer).estMarquee()){
            super.lePlateau.get(caseAMarquer).demarquer();
        }
        else{
            super.lePlateau.get(caseAMarquer).marquer();
        }
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

    public void affiche(){
        System.out.println("JEU DU DEMINEUR");
        // affichage de la bordure supérieure
        System.out.print("  ");
        for (int j=0; j<this.getNbColonnes(); j++){
            System.out.print("  "+j+" ");
        }
        System.out.print(" \n");
>>>>>>> Stashed changes
        
    }
}

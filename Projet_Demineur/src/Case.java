public class Case{
    private boolean contientUneBombe;
    private boolean estDecouverte;
    private boolean estMarquee;

    /**Constructeur de la classe Case */
    public Case(){
        this.contientUneBombe = false;
        this.estDecouverte = false;
        this.estMarquee = false;
    }

    /**Permet de reset les données contenues dans une case */
    public void reset(){
        this.contientUneBombe = false;
        this.estDecouverte = false;
        this.estMarquee = false;
    }

    /**Permet de poser une bombe sur la case */
    public void poseBombe(){
        this.contientUneBombe = true;
    }

    /**
     * Permet de savoir s'il y a une bombe sur la case
     * @return true si la case contient une bombe, false sinon
     */
    public boolean contientUneBombe(){
        return this.contientUneBombe;
    }

    /**Permet de faire en sorte que la première case découverte ne soit pas une bombe */
    public void demine(){
        this.contientUneBombe = false ;
    }

    /**
     * Permet de savoir si la case a été découverte ou non
     * @return true si la case est découverte, false sinon
     */
    public boolean estDecouverte(){
        return this.estDecouverte;
    }

    /**
     * Permet de savoir si la case a été marquée ou non
     * @return true si la case est marquée, false sinon
     */
    public boolean estMarquee(){
        return this.estMarquee;
    }

    /**
     * Permet de révéler une case
     * @return true si la case n'était pas déjà découverte, false sinon
     */
    public boolean reveler(){
        if(!this.estMarquee){
            this.estDecouverte = true;
            return this.contientUneBombe;
        }
        return false;
    }

    /**Permet de marquer une case */
    public void marquer(){
        this.estMarquee = true;
    }

    /**Permet de démarquer une case */
    public void demarquer(){
        this.estMarquee = false;
    }
}
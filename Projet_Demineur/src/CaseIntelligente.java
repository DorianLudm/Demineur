import java.util.ArrayList;
import java.util.List;

public class CaseIntelligente extends Case{
    private List<Case> lesVoisines;

    /**Constructeur de la classe*/
    public CaseIntelligente(){
        super();
        this.lesVoisines = new ArrayList<>();
    }

    /**Ajoute une case dans l'attribut "lesVoisines"
     * @param lacase (case), la case à ajouter dans l'attribut "lesVoisines"
    */
    public void ajouteCaseVoisine(Case lacase){
        this.lesVoisines.add(lacase);
    }

    /**Renvoie le nombre de bombes dans les cases voisines
     * @return nb (int), le nombre de case voisines qui possède une bombe
     */
    public int nombreBombesVoisines(){
        int nb = 0;
        for(Case c : this.lesVoisines){
            if (c.contientUneBombe()){
            nb += 1;
            }
        }
        return nb;
    }

    public List<Case> getCasesVoisines(){
        return this.lesVoisines;
    }

    /**Renvoie les informations de la case sous forme de String
     * @return res (String), les informations mise sous forme de phrase
     */
    @Override
    public String toString(){
        if (this.estMarquee()){return "?";}
        if (!this.estDecouverte()){return " ";}
        if (this.contientUneBombe()){return "@";}
        if (this.nombreBombesVoisines() == 0){return "0";}
        return String.valueOf(this.nombreBombesVoisines());
    }
}

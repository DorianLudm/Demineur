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

    /**Renvoie les informations de la case sous forme de String
     * @return res (String), les informations mise sous forme de phrase
     */
    @Override
    public String toString(){
        String res = "";
        if (this.estMarquee()){res = "?";}
        if (!this.estDecouverte()){res = " ";}
        if (this.contientUneBombe()){res = "@";}
        if (this.nombreBombesVoisines() == 0){res = "0";}
        res = String.valueOf(this.nombreBombesVoisines());
        return res;
    }
}

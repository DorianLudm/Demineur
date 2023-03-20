import java.util.ArrayList;
import java.util.List;

public class CaseIntelligente extends Case{
    private List<Case> lesVoisines;

    public CaseIntelligente(){
        super();
        this.lesVoisines = new ArrayList<>();
    }

    public void ajouteCaseVoisine(Case lacase){
        this.lesVoisines.add(lacase);
    }

    public int nombreBombesVoisines(){
        int nb = 0;
        for(Case c : this.lesVoisines){
            if (c.contientUneBombe()){
            nb += 1;
            }
        }
        return nb;
    }

    @Override
    public String toString(){
        if (this.estMarquee()){return "?";}
        if (!this.estDecouverte()){return " ";}
        if (this.contientUneBombe()){return "@";}
        if (this.nombreBombesVoisines() == 0){return "0";}
        return String.valueOf(this.nombreBombesVoisines());
    }
}

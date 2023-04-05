import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Plateau{

   private int nbLignes;
   private int nbColonnes;
   private int pourcentageDeBombes;
   private int nbBombes;
   protected List<CaseIntelligente> lePlateau;

   public Plateau(int nbLignes, int nbColonnes, int pourcentage){
      this.nbLignes = nbLignes;
      this.nbColonnes = nbColonnes;
      this.pourcentageDeBombes = pourcentage;
      this.lePlateau = new ArrayList<>();
   }

   public void creerLesCasesVides(){
      int i = nbLignes;
      int j = nbColonnes;
      for (int k = 0; k < i*j-1; k++){
         this.lePlateau.add(new CaseIntelligente());
      }
   }

   public void rendLesCasesIntelligentes(){
      for (int ligneCaseElem = 0; ligneCaseElem < this.nbLignes; ligneCaseElem++){
         for (int colonneCaseElem = 0; colonneCaseElem < this.nbLignes; colonneCaseElem++){
            for (int ligneCaseVoisine = 0; ligneCaseVoisine < this.nbLignes; ligneCaseVoisine++){
               for (int colonneCaseVoisine = 0; colonneCaseVoisine < this.nbLignes; colonneCaseVoisine++){
                  boolean estVoisine = false;
                  if(ligneCaseElem == ligneCaseVoisine){ /**Test si les deux cases sont sur la même ligne*/
                     if(colonneCaseElem - 1 >= 0 && colonneCaseElem - 1 == colonneCaseVoisine){
                        estVoisine = true;
                     }
                     if(colonneCaseElem + 1 <= this.nbColonnes && colonneCaseElem + 1 == colonneCaseVoisine){
                        estVoisine = true;
                     }
                  }
                  else{
                     if(colonneCaseElem == colonneCaseVoisine){ /**Test si les deux cases sont sur la même colonne*/
                        if(ligneCaseElem - 1 >= 0 && ligneCaseElem - 1 == ligneCaseVoisine){
                           estVoisine = true;
                        }
                        if(ligneCaseElem + 1 <= this.nbLignes && ligneCaseElem + 1 == ligneCaseVoisine){
                           estVoisine = true;
                        }
                     }
                     else{ /**Test si la case potentiellement voisine est dans un coin*/
                        if(colonneCaseElem - 1 >= 0 && ligneCaseElem - 1 >= 0 && ligneCaseElem - 1 == ligneCaseVoisine && colonneCaseElem - 1 == colonneCaseVoisine){estVoisine = true;}
                        if(ligneCaseElem + 1 <= this.nbLignes && ligneCaseElem + 1 == ligneCaseVoisine && colonneCaseElem + 1 <= this.nbColonnes && colonneCaseElem + 1 == colonneCaseVoisine){estVoisine = true;}
                        if(colonneCaseElem - 1 >= 0 && colonneCaseElem - 1 == colonneCaseVoisine && ligneCaseElem + 1 <= this.nbLignes && ligneCaseElem + 1 == ligneCaseVoisine){estVoisine = true;}
                        if(colonneCaseElem + 1 <= this.nbColonnes && colonneCaseElem + 1 == colonneCaseVoisine && ligneCaseElem - 1 >= 0 && ligneCaseElem - 1 == ligneCaseVoisine){estVoisine = true;}
                     }
                  }
                  if(estVoisine){
                     getCase(ligneCaseElem, colonneCaseElem).ajouteCaseVoisine(getCase(ligneCaseVoisine,colonneCaseVoisine));
                  }
               }
            } 
         }
      }
   }

   protected void poseDesBombesAleatoirement(){
        Random generateur = new Random();
        for (int x = 0; x < this.getNbLignes(); x++){
            for (int y = 0; y < this.getNbColonnes(); y++){
                if (generateur.nextInt(100)+1 < this.pourcentageDeBombes){
                    this.poseBombe(x, y);
                    this.nbBombes = this.nbBombes + 1;
                }
            }
        }
    }

   public int getNbLignes(){return this.nbLignes;}
   public int getNbColonnes(){return this.nbColonnes;}
   public int getNbTotalBombes(){return this.nbBombes;}
   public int getNbCasesMarquees(){
      int res = 0;
      for(CaseIntelligente caseElem: this.lePlateau){
         if(caseElem.estMarquee()){
            res += 1;
         }
      }
      return res;
   }

   public CaseIntelligente getCase(int numLigne, int numColonne){
      if(numLigne*this.nbColonnes+numColonne <= this.lePlateau.size()){
         return this.lePlateau.get(numLigne*this.nbColonnes+numColonne);
      }
      return null;
   }

   public int getNbCases(){return this.lePlateau.size();}

   public void poseBombe(int x, int y){
         getCase(x, y).poseBombe();
   }

   public void reset(){
      creerLesCasesVides();
   }

}
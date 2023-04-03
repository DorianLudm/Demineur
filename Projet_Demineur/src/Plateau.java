import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {
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

   private void creerLesCasesVides(){
      int i = nbLignes;
      int j = nbColonnes;
      for (int k = 0; k < i*j-1; k++){
         this.lePlateau.add(new CaseIntelligente());
      }
   }

   private void rendLesCasesIntelligentes(){
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

   public CaseIntelligente getCase(int numLigne, int numColonne){
      for (int i = 0; i < nbLignes; i++){
         for (int j = 0; j < nbColonnes; j++){
            if (i == numLigne){
               if (j == numColonne){
                  return this.lePlateau.get(i*nbColonnes+j);
               }
            }  
         }
      }
      return null;
   }

   public int getNbCases(){return this.lePlateau.size();}

   public void poseBombe(int x, int y){
      for (int ligne = 0; ligne < nbLignes; ligne++){
         for (int colonne = 0; colonne < nbColonnes; colonne++){
            if (ligne == x){
               if (colonne == y){
                  lePlateau.get(ligne*nbColonnes+colonne).poseBombe();
               }
            }
         }
      }
   }

   public void reset(){
      creerLesCasesVides();
   }
}

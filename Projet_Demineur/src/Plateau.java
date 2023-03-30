import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {
   private int nbLignes;
   private int nbColonnes;
   private int pourcentageDeBombes;
   private int nbBombes;
   private List<CaseIntelligente> lePlateau;

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
      for (int ligne = 0; ligne < nbLignes; ligne++){
         for (int colonne = 0; colonne < nbColonnes; colonne++){
            for (int ligneC = 0; ligneC < nbLignes; ligneC++){
               for (int colonneC = 0; colonneC < nbColonnes; colonneC++){
                  
               }
            }
         }
      }
   }

/**  */

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
                  return this.lePlateau.get(i+j);
               }
            }  
         }
      }
      return null;
   }

   public int getNbCases(){return this.lePlateau.size();}

   public void poseBombe(int x, int y){
      
   }

   public void reset(){
      creerLesCasesVides();
   }
}

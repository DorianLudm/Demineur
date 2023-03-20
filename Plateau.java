import java.util.ArrayList;
import java.util.List;

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
      for (CaseIntelligente c : this.lePlateau){
         
      }
   }

   protected void poseDesBombesAleatoirement(){}

   public int getNbLignes(){return this.nbLignes;}
   public int getNbColonnes(){return this.nbColonnes;}
   public int getNbTotalBombes(){return this.nbBombes;}

   public CaseIntelligente getCase(int numLigne, int numColonne){
      for (int i = 0; i < nbLignes; i++){
         for (int j = 0; j < nbColonnes; j++){
            if (i == numLigne){
               if (j == numColonne){
                  return this.lePlateau.get(i).get(j);
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

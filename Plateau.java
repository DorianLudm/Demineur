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
   }

   public void creerLesCasesVides(){
    
   }
}

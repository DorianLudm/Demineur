import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Plateau{

   private int nbLignes;
   private int nbColonnes;
   private int pourcentageDeBombes;
   private int nbBombes;
   protected List<CaseIntelligente> lePlateau;

   /**
    * Constructeur de la classe Plateau
    * @param nbLignes le nombre de lignes du plateau
    * @param nbColonnes le nombre de colonnes du plateau
    * @param pourcentage le pourcentage de bombes qui vont être dispersées sur le plateau
    */
   public Plateau(int nbLignes, int nbColonnes, int pourcentage){
      this.nbLignes = nbLignes;
      this.nbColonnes = nbColonnes;
      this.pourcentageDeBombes = pourcentage;
      this.lePlateau = new ArrayList<>();
      this.nbBombes = 0;
      creerLesCasesVides();
      rendLesCasesIntelligentes();
      poseDesBombesAleatoirement();
   }

   /**
    * Permet de changer le nombre de bombes que l'on souhaite disperser sur le plateau
    * @param nb
    */
   public void changeNbBombes(int nb){
      this.nbBombes += nb;
   }

   /**Permet de créer un plateau avec seulement des cases vides */
   public void creerLesCasesVides(){
      int i = this.nbLignes;
      int j = this.nbColonnes;
      for (int k = 0; k < i*j; k++){
         this.lePlateau.add(new CaseIntelligente());
      }
   }

   /**Permet de rendre chaque case du plateau "intelligente"
    * Cela veut surtout dire que pour chaque case, nous allons pouvoir connaitre leurs voisines
    */
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

   /**Permet de disperser les bombes de façon aléatoire sur le plateau */
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

    /**
     * Permet de récupérer le nombre de lignes du plateau
     * @return la valeur en entier de la hauteur du plateau
     */
   public int getNbLignes(){
      return this.nbLignes;
   }

   /**
    * Permet de récupérer le nombre de colonnes du plateau
    * @return la valeur en entier de la largeur du plateau
    */
   public int getNbColonnes(){
      return this.nbColonnes;
   }

   /**
    * Permet de récupérer le nombre total de bombes présentes dans le plateau
    * @return l'entier représentant le total des bombes posées sur le plateau
    */
   public int getNbTotalBombes(){
      return this.nbBombes;
   }

   /**
    * Permet de récupérer le nombre de cases qui ont été marquées par le joueur
    * @return l'entier représentant le nombre total des cases qui ont été marquées
    */
   public int getNbCasesMarquees(){
      int res = 0;
      for(CaseIntelligente caseElem: this.lePlateau){
         if(caseElem.estMarquee()){
            res += 1;
         }
      }
      return res;
   }

   /**
    * Permet de récupérer le nombre de cases qui ont été découvertes par le joueur
    * @return l'entier représentant le nombre total de cases qui ont été découvertes
    */
   public int getNbCasesDecouverte(){
      int res = 0;
      for(CaseIntelligente caseElem: this.lePlateau){
         if(caseElem.estDecouverte()){
            res += 1;
         }
      }
      return res;
   }

   /**
    * Permet de récupérer une case spécifique
    * @param numLigne le numéro de ligne de la case ciblée
    * @param numColonne le numéro de colone de la case ciblée
    * @return la case ciblée
    */
   public CaseIntelligente getCase(int numLigne, int numColonne){
      if(numLigne*this.nbColonnes+numColonne <= this.lePlateau.size()){
         return this.lePlateau.get(numLigne*this.nbColonnes+numColonne);
      }
      return null;
   }

   /**
    * Permet de récupérer le nombre total de cases du plateau
    * @return l'entier représentant le nombre de cases du plateau
    */
   public int getNbCases(){
      return this.lePlateau.size();
   }

   /**
    * Permet de poser une bombe sur une case spécifique
    * @param x la position en x de la case ciblée
    * @param y la position en y de la case ciblée
    */
   public void poseBombe(int x, int y){
      getCase(x, y).poseBombe();
   }

   /**Permet de reset le plateau */
   public void reset(){
      for (CaseIntelligente c : this.lePlateau){
         c.reset();
      }
      this.nbBombes = 0;
   }
}
import javafx.application.Application;

public class Executable{

    public static void main(String [] args){
        // paramètres : nb_lignes=10, nb_colonnes = 10, pourcentage bombres = 20;        
        Application.launch(DemineurGraphique.class, "10", "10", "15");
    }
}
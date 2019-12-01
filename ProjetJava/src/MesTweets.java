import java.util.Scanner;

public class MesTweets {
	// collec : contient l'ensemble des articles d'actualité
    private static BaseDeTweets collec = null;

    //Programme principal
    public static void main(String[] args) {
    	//creationBase();
    	//ouvrirBase();
    }
    
    //Création d'une base
    public static void creationBase()
    {
        collec = new BaseDeTweets();
    }
    
    //Affichage de la base
   /* public static void affichage()
    {
        System.out.println(collec);
    }
    */
    
    //Récupération d'une base
    public static void ouvrirBase(String base) {
        System.out.println(" --- Chargement de la base: --- ");
        //Scanner scan = new Scanner(System.in);
        //System.out.print("Veuillez entrer le nom pour la base : ");
        //String nom = scan.nextLine();
        collec.lecture(base);
    }
   
    //Requêtage
    public static void rechercher(String recherche)
    {
    	//Scanner scan = new Scanner(System.in);
        //System.out.println("Votre requête : ");
        //String mot = scan.nextLine();
        collec.recherche(recherche);
    }
    
}
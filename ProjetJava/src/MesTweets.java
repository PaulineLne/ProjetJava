import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MesTweets {
	// collec : contient l'ensemble des articles d'actualité
    private static BaseDeTweets collec = null;

    //Programme principal
    public static void main(String[] args) {
        boucle();
    }
    
    //Affichage des options du menu 
    public static void Menu()
    {
       System.out.println("--- Liste des possibilités ---");
       System.out.println("1. Créer une nouvelle base d'actualités");
       System.out.println("2. Ouvrir une base d'actualités");
       System.out.println("3. Sauvegarder la base d'actualités");
       System.out.println("4. Afficher toutes les actualités");
       System.out.println("5. Insérer une nouvelle actualité");
       System.out.println("6. Supprimer une actualité");
       System.out.println("7. Rechercher une actualité");
       System.out.println("8. Quitter");        
    }
    
    //Affichage en boucle du menu avec les fonctions associées
    public static void boucle()
    {        
        int num = 0;
        do {
            Menu();
            System.out.print("Choix: ");
            Scanner scan = new Scanner(System.in);
            num = scan.nextInt();
            switch (num)
            {
                case 1: creationBase();
                    break;
                case 2: ouvrirBase();
                    break;                    
                case 3: sauvegarderBase();
                    break;                    
                case 4: affichage();
                    break;    
                case 5: insererNews();
                    break;
                case 6: suppression();
                    break;
                case 7: rechercher();
                    break;                   
            }
        } while (num != 8);
        System.out.println("Fin du programme");
    }
    
    //Création d'une base
    public static void creationBase()
    {
        System.out.println(" --- Creation d'une base d'articles --- ");	
        collec = new BaseDeTweets();
    }
    
    //Appel à l'insertion d'une news
    public static void insererNews(Tweets t)
    {
        collec.insertNews(t);
    }
    
    //Collecte des informations + insertion
    public static void insererNews()
    {
    	Scanner scanti = new Scanner(System.in);
        System.out.print("Veuillez entrer un titre : ");
        String title = scanti.nextLine();
        
        Scanner scana = new Scanner(System.in);
        System.out.print("Veuillez entrer un nom d'auteur : ");
        String author = scana.nextLine();
        
        Scanner scand = new Scanner(System.in);
        System.out.print("Veuillez entrer une date au format jj-mm-aaaa : ");
        String d = scand.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        LocalDate date = LocalDate.now(); // par défaut
        try
        {
            date = LocalDate.parse(d, formatter);
        } catch (DateTimeParseException e) { }
        System.out.print("Veuillez entrer L'url source : ");
        String url = scand.nextLine();
        URL formattedurl = null;
        try {
            formattedurl = new URL(url);
        } catch (MalformedURLException me)
        {
            System.out.println("erreur");
        }
        
        
        //News n = new News(title, author, date, formattedurl);
        
    }    
    
    //Suppression d'une news
    public static void suppression()
    {
    	Scanner scan = new Scanner(System.in);
        System.out.println("Quelle actualité voulez-vous supprimer ?");
        int i = scan.nextInt();
        collec.supprimerNews(i);
    }
    
    //Affichage de la base
    public static void affichage()
    {
        System.out.println(collec);
    }

    //Récupération d'une base
    public static void ouvrirBase() {
        System.out.println(" --- Lecture des actualités --- ");
        Scanner scan = new Scanner(System.in);
        System.out.print("Veuillez entrer le nom pour la base : ");
        String nom = scan.nextLine();
        collec.lecture(nom);
    }

    //Sauvegarde d'une base
    public static void sauvegarderBase() {
        System.out.println(" --- Sauvegarde des actualités --- ");
        Scanner scan = new Scanner(System.in);
        System.out.print("Veuillez entrer le nom pour la base : ");
        String nom = scan.nextLine();
        collec.enregistrer(nom);
        
    }

    //Requêtage
    public static void rechercher()
    {
    	
    	Scanner scan = new Scanner(System.in);
        System.out.println("Votre requête : ");
        String mot = scan.nextLine();
        collec.recherche(mot);
    }
    
}

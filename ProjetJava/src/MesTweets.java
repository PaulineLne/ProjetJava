import java.time.LocalDate;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public static void rechercher(String recherche,Integer id)
    {
    	//Scanner scan = new Scanner(System.in);
        //System.out.println("Votre requête : ");
        //String mot = scan.nextLine();
       collec.recherche(recherche,id);
    }
    
    @SuppressWarnings("unchecked")
	public static TableView<Tweets> fenetre (String recherche,Integer nb) {
    	TableView<Tweets> table=new TableView<Tweets>();
    	//TableColumn<Tweets,Integer> id = new TableColumn<Tweets,Integer>("Numéro de tweet");
		TableColumn<Tweets,String> utilisateur = new TableColumn<Tweets,String>("Nom d'utilisateur");
		TableColumn<Tweets,LocalDate> date = new TableColumn<Tweets,LocalDate>("Date");
		TableColumn<Tweets,String> texte = new TableColumn<Tweets,String>("Contenu");
		TableColumn<Tweets,String> rtutilisateur = new TableColumn<Tweets,String>("Nom de l'utilisateur retweeté");
		TableColumn<Tweets,Integer> NbMot = new TableColumn<Tweets,Integer>("Nombre de mots");

		//id.setCellValueFactory(new PropertyValueFactory<>("id"));
		utilisateur.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		texte.setCellValueFactory(new PropertyValueFactory<>("texte"));
		rtutilisateur.setCellValueFactory(new PropertyValueFactory<>("rtutilisateur"));
		NbMot.setCellValueFactory(new PropertyValueFactory<>("NbMot"));

		//Récupération des résultats de la recherche
		
		ObservableList<Tweets> list=BaseDeTweets.recherche(recherche,nb);
		table.setItems(list);
		
		//Insertion des résultats dans le tableau
		table.getColumns().addAll(/*id;*/utilisateur,date,texte,rtutilisateur,NbMot);
		return table;
    }
    
}
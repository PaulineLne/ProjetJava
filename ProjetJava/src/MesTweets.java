import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MesTweets {
    private static BaseDeTweets collec = null;

    //Création d'une base
    public static void creationBase()
    {
        collec = new BaseDeTweets();
    }

    //Récupération d'une base
    public static void ouvrirBase(String base) {
        collec.lecture(base);
    }
    
    //Affichage des données
    @SuppressWarnings("unchecked")
	public static TableView<Tweets> tab_data(ObservableList<Tweets> list,boolean norm,boolean c) {
    	TableView<Tweets> table=new TableView<Tweets>();
    	
    	//Normalisation et k-means
    	if(norm==true & c==true) {
    		TableColumn<Tweets,Integer> classe = new TableColumn<Tweets,Integer>("Classe");
			TableColumn<Tweets,Double> poids = new TableColumn<Tweets,Double>("Poids du tweet");
	    	TableColumn<Tweets,String> utilisateur = new TableColumn<Tweets,String>("Nom d'utilisateur");
			TableColumn<Tweets,LocalDate> date = new TableColumn<Tweets,LocalDate>("Date");
			TableColumn<Tweets,String> texte = new TableColumn<Tweets,String>("Contenu");
			TableColumn<Tweets,String> rtutilisateur = new TableColumn<Tweets,String>("Nom de l'utilisateur retweeté");
			
			//Affectation des noms de colonnes
			classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
			poids.setCellValueFactory(new PropertyValueFactory<>("poids"));
			utilisateur.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
			date.setCellValueFactory(new PropertyValueFactory<>("date"));
			texte.setCellValueFactory(new PropertyValueFactory<>("texte"));
			rtutilisateur.setCellValueFactory(new PropertyValueFactory<>("rtutilisateur"));
		   
	        
	      //Insertion des résultats dans le tableau
			table.setItems(list);
			table.getColumns().addAll(classe,poids,utilisateur,date,texte,rtutilisateur);
			
    	}else {
    		//Normalisation
    		if(norm==true) {
    			TableColumn<Tweets,Double> poids = new TableColumn<Tweets,Double>("Poids du tweet");
    	    	TableColumn<Tweets,String> utilisateur = new TableColumn<Tweets,String>("Nom d'utilisateur");
    			TableColumn<Tweets,LocalDate> date = new TableColumn<Tweets,LocalDate>("Date");
    			TableColumn<Tweets,String> texte = new TableColumn<Tweets,String>("Contenu");
    			TableColumn<Tweets,String> rtutilisateur = new TableColumn<Tweets,String>("Nom de l'utilisateur retweeté");
    			
    			//Affectation des noms de colonnes
    			poids.setCellValueFactory(new PropertyValueFactory<>("poids"));
    			utilisateur.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
    			date.setCellValueFactory(new PropertyValueFactory<>("date"));
    			texte.setCellValueFactory(new PropertyValueFactory<>("texte"));
    			rtutilisateur.setCellValueFactory(new PropertyValueFactory<>("rtutilisateur"));
    		   
    	        
    	      //Insertion des résultats dans le tableau
    			table.setItems(list);
    			table.getColumns().addAll(poids,utilisateur,date,texte,rtutilisateur);
    			
    			
    		}else {
    			//k-means
    			if(c==true) {
    				TableColumn<Tweets,Integer> classe = new TableColumn<Tweets,Integer>("Classe");
    				TableColumn<Tweets,String> utilisateur = new TableColumn<Tweets,String>("Nom d'utilisateur");
    				TableColumn<Tweets,LocalDate> date = new TableColumn<Tweets,LocalDate>("Date");
    				TableColumn<Tweets,String> texte = new TableColumn<Tweets,String>("Contenu");
    				TableColumn<Tweets,String> rtutilisateur = new TableColumn<Tweets,String>("Nom de l'utilisateur retweeté");
    				
    				//Affectation des noms de colonnes
    				classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
    				utilisateur.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
    				date.setCellValueFactory(new PropertyValueFactory<>("date"));
    				texte.setCellValueFactory(new PropertyValueFactory<>("texte"));
    				rtutilisateur.setCellValueFactory(new PropertyValueFactory<>("rtutilisateur"));
    			   
    		        
    		      //Insertion des résultats dans le tableau
    				table.setItems(list);
    				table.getColumns().addAll(classe,utilisateur,date,texte,rtutilisateur);
    				
    			}else {
    				//Pas d'action
    				TableColumn<Tweets,String> utilisateur = new TableColumn<Tweets,String>("Nom d'utilisateur");
    				TableColumn<Tweets,LocalDate> date = new TableColumn<Tweets,LocalDate>("Date");
    				TableColumn<Tweets,String> texte = new TableColumn<Tweets,String>("Contenu");
    				TableColumn<Tweets,String> rtutilisateur = new TableColumn<Tweets,String>("Nom de l'utilisateur retweeté");
    				
    				//Affectation des noms de colonnes
    				utilisateur.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
    				date.setCellValueFactory(new PropertyValueFactory<>("date"));
    				texte.setCellValueFactory(new PropertyValueFactory<>("texte"));
    				rtutilisateur.setCellValueFactory(new PropertyValueFactory<>("rtutilisateur"));
    			   
    		        
    		      //Insertion des résultats dans le tableau
    				table.setItems(list);
    				table.getColumns().addAll(utilisateur,date,texte,rtutilisateur);
    				
    			}
    		}
    	}
    	return table;
    }
}
//Base où ajouter les fonction de tfidf
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BaseDeTweets implements Serializable {
	private static TreeSet<Tweets> liste;
    
    //Création d'un Treeset contenant les tweets
    public BaseDeTweets()
    {
    	liste = new TreeSet<Tweets>();
    }
    	
    //Importation d'une liste de tweets
	public void lecture (String nom) {
   		try {
   			
   			//Ouverture du fichier
   			FileInputStream r = new FileInputStream(nom);
   			InputStreamReader o = new InputStreamReader(r,"UTF8");
   			BufferedReader br=new BufferedReader(o);
   			
   			//Initialisation des variables utiles
   			String ligne;
   			Integer id=0;
   			
   			//Lecture de chaque ligne du fichier
   			while((ligne=br.readLine())!=null & id<500000 ) {
   				
   				//Découpage de la ligne en liste de variables
   				String[] st=ligne.split("\t");
   				id=id+1;
				String u=st[1];//utilisateur
				
				//date
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
				LocalDate date = LocalDate.now(); // par défaut
				try
				{
					date = LocalDate.parse(st[2], formatter);
				} catch (DateTimeParseException e) { }
				
				//Liste de mots
				String c=st[3];
				String[] mot=c.split("[ ,?!;.'\"()]+");
				ArrayList<String> l = new ArrayList<String>();
				for (String i : mot) {
					l.add(i) ;
				}
   				//Cas n°1 : Le tweet n'est pas retweeté
   				if(st.length==4) {
					//Ajout du tweet
   			        Tweets t=new Tweets(id,date,u,c,"",l,0.0,0);
           			liste.add(t);
           			
   				}else {
   					//Cas n°2 : Tweet retweeté
   					if(st.length==5) {
						String rt=st[4]; //utilisateur retweeté
						
						//Ajout du tweet
       			        Tweets t=new Tweets(id,date,u,c,rt,l,0.0,0);
               			liste.add(t);
       				}
   				}
				
   			}
   			//Fermeture du fichier
			br.close();
   		}
   		catch (Exception e){
   			System.out.println("Erreur d’entrée-sortie"+e);
   		}
   	}
   			
   	
   	
    
    //Recherche d'une expression dans chacun des tweets
    private static Pattern patterns;
    private static Matcher matchers;
    public static ObservableList<Tweets> recherche(String mot,Integer id,ObservableList<Tweets> temp) {
    	//Liste contenant les résultats de la recherche
    	ObservableList <Tweets> list=FXCollections.observableArrayList();
    	patterns=Pattern.compile(mot);
    	
    	//Parcours de chaque tweet
    	Iterator<Tweets> iter=temp.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            switch(id) {
            	
            	//Recherche dans toutes les variables du tweets
            	case 1:
                	String champ1 = String.valueOf(t);
                	//System.out.println(champ);
                    matchers=patterns.matcher(champ1);
                    if (matchers.find()) {
                    	list.add(t);
                    }
                break;
                
                //Recherche d'un utlisateur
            	case 2:
            		String champ2 = t.getUtilisateur();
                	//System.out.println(champ);
                    matchers=patterns.matcher(champ2);
                    if (matchers.find()) {
                    	list.add(t);
                    }
                break;
                
                //Recherche d'un utilisateur retweeté
            	case 3:
            		String champ3 = t.getRtutilisateur();
                    matchers=patterns.matcher(champ3);
                    if (matchers.find()) {
                    	list.add(t);
                    }
                break;
            	case 4:
            		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDateTime = t.getDate().format(formatter);
            		String champ4 = formattedDateTime;
                	//System.out.println(champ);
                    matchers=patterns.matcher(champ4);
                    if (matchers.find()) {
                    	list.add(t);
                    }
                break;		
            }  
        }
        return list;
    }
    
    //Liste des tweets
    public static ObservableList<Tweets> ListeTweets() {
    	ObservableList <Tweets> list=FXCollections.observableArrayList();
    	//Iterator<Tweets> iter=liste.iterator();
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            list.add(t);
        }
    	return list;
    }
    
    public static ArrayList<String> ListeDate() {
    	ArrayList<String>  list=new ArrayList<String>();
    	//Iterator<Tweets> iter=liste.iterator();
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDateTime = t.getDate().format(formatter);
            list.add(formattedDateTime);
        }
        Set <String> temp=new HashSet<String>(list);
        ArrayList<String>  list2=new ArrayList<String>(temp);
    	return list2;
    }
    
    //Liste des utilisateurs
    public static ArrayList<String> ListeUtilisateur() {
    	ArrayList<String>  list=new ArrayList<String>();
    	//Iterator<Tweets> iter=liste.iterator();
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            list.add(t.getUtilisateur());
            
        }
        
        //Suppression des doublons
        Set <String> temp=new HashSet<String>(list);
        ArrayList<String>  list2=new ArrayList<String>(temp);
    	return list2;
    }
    
    //Liste des utilisateurs retweetés
    public static ArrayList<String> ListeRtutilisateur() {
    	ArrayList<String>  list=new ArrayList<String>();
    	//Iterator<Tweets> iter=liste.iterator();
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            list.add(t.getRtutilisateur());
            
        }
        
        //Suppression des doublons
        Set <String> temp=new HashSet<String>(list);
        ArrayList<String>  list2=new ArrayList<String>(temp);
    	return list2;
    }  
    
  
}
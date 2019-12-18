import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
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
    
    //Création d'un Treeset de News
    public BaseDeTweets()
    {
	liste = new TreeSet<Tweets>();
    }
    	
    	//Importation d'un Treeset
    	@SuppressWarnings("unchecked")
		public void lecture (String nom) {
    		try {
    			FileInputStream r = new FileInputStream(nom);
    			InputStreamReader o = new InputStreamReader(r,"UTF-8");
    			BufferedReader br=new BufferedReader(o);
    			String ligne;
    			Integer id=0;
    			while((ligne=br.readLine())!=null) {
    				
    				String[] st=ligne.split("\t");
    				if(st.length==4) {
						
						id=id+1;
						String u=st[1];
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
						LocalDate date = LocalDate.now(); // par défaut
						try
						{
							date = LocalDate.parse(st[2], formatter);
						} catch (DateTimeParseException e) { }
						String c=st[3];
    			        String rt="";
    			        
    			        Tweets t=new Tweets(id,date,u,c,rt);
    			        
            			liste.add(t);
    				}else {
    					if(st.length==5) {
    						id=id+1;
							String u=st[1];
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
							LocalDate date = LocalDate.now(); // par défaut
							try
							{
								date = LocalDate.parse(st[2], formatter);
							} catch (DateTimeParseException e) { }
							String c=st[3];
        			        String rt=st[4];
        			        Tweets t=new Tweets(id,date,u,c,rt);
                			liste.add(t);
        				}
    				}
					
    				}
       				System.out.println(id);
    				System.out.println(liste.size());
    				System.out.println("Fin d'insertion");
					br.close();
    			}
    			catch (Exception e)
    			{
    			System.out.println("Erreur d’entrée-sortie"+e);
    			}
    			}
    			
    	
    	
    
    //Recherche dans le Treeset d'une expression
    private static Pattern patterns;
    private static Matcher matchers;
    private Integer nombre;
    public static ObservableList<Tweets> recherche(String mot,Integer id) {
    	ObservableList <Tweets> list=FXCollections.observableArrayList();
    	patterns=Pattern.compile(mot);
    	System.out.println(patterns);
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            switch(id) {
            	case 1:
                	String champ1 = String.valueOf(t);
                	//System.out.println(champ);
                    matchers=patterns.matcher(champ1);
                    if (matchers.find()) {
                    	list.add(t);
                    }
                break;
            	case 2:
            		String champ2 = t.getUtilisateur();
                	//System.out.println(champ);
                    matchers=patterns.matcher(champ2);
                    if (matchers.find()) {
                    	list.add(t);
                    }
                break;
            	case 3:
            		String champ3 = t.getRtutilisateur();
                	//System.out.println(champ);
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
    
    //Tri
    public static ObservableList<Tweets> ListeTweets() {
    	ObservableList <Tweets> list=FXCollections.observableArrayList();
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
    
    public static ArrayList<String> ListeUtilisateur() {
    	ArrayList<String>  list=new ArrayList<String>();
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            list.add(t.getUtilisateur());
            
        }
        Set <String> temp=new HashSet<String>(list);
        ArrayList<String>  list2=new ArrayList<String>(temp);
    	return list2;
    }
    
    public static ArrayList<String> ListeRtutilisateur() {
    	ArrayList<String>  list=new ArrayList<String>();
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            list.add(t.getRtutilisateur());
            
        }
        Set <String> temp=new HashSet<String>(list);
        ArrayList<String>  list2=new ArrayList<String>(temp);
    	return list2;
    }
    
    
    
    //Modalité d'affichage
    public String toString()
    {
        String s = "";
        int num = 1;
        int i=1;
        Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext() && i<10)
        {
            Tweets t = iter.next();
            s += "-- Tweet n° " + num + " --\n";
            s += t.toString();
            num++;
            i++;
        }
        return s;
    }  
    
    
    
    
}







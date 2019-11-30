import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseDeTweets implements Serializable {
	private TreeSet<Tweets> liste;
    
    //Création d'un Treeset de News
    public BaseDeTweets()
    {
	liste = new TreeSet<Tweets>();
    }
   
    //Insertion dans le Treeset
    public void insertTweets(Tweets t)
    {
        liste.add(t);
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
    public void recherche(String mot) {
    	patterns=Pattern.compile(mot);
    	System.out.println(patterns);
    	nombre=0;
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            
                	String champ = String.valueOf(t);
                	//System.out.println(champ);
                    matchers=patterns.matcher(champ);
                    if (matchers.find()) {
                    	System.out.println(t);
                    	nombre=nombre+1;
                    }
            
        }
        if(nombre==0) {
        	System.out.println("Pas de correspondance!");
        }
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




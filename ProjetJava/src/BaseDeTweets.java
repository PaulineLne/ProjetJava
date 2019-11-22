import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BaseDeTweets implements Serializable {
private TreeSet<Tweets> liste;
    
    //Création d'un Treeset de News
    public BaseDeTweets()
    {
	liste = new TreeSet<Tweets>();
    }
   
    //Insertion dans le Treeset
    public void insertNews(Tweets t)
    {
        liste.add(t);
    }
    
    //Suppression dans le Treeset à partir d'un numéro
    public void supprimerNews(int i)
    {
        int num = 1;
        Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            if (num == i)
            {
                liste.remove(t);
                return;
            }
            num++;
        }
        System.out.println("Ce numéro n'existe pas");
    }
    
    //Enregistrement du Treeset
    public void enregistrer(String nom) {
    	try {
    		FileOutputStream w = new FileOutputStream(nom);
    		ObjectOutputStream o = new ObjectOutputStream(w);
    		o.writeObject(liste);
    		o.close();
    		w.close();
    		} catch (Exception e)
    		{ 
    			System.out.println("Erreur"+ e);
    		}
    	}
    	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
    		out.defaultWriteObject();
    	}
    	
    	//Importation d'un Treeset
    	@SuppressWarnings("unchecked")
		public void lecture (String nom) {
    		try {
    			FileInputStream r = new FileInputStream(nom);
    			ObjectInputStream o = new ObjectInputStream(r);
    			Object lu = o.readObject();
    			liste = (TreeSet<Tweets>)lu;
    			o.close();
    			r.close();
    			}
    			catch (Exception e)
    			{
    			System.out.println("Erreur d’entrée-sortie");
    			}
    	}
    	private void readObject(java.io.ObjectInputStream in)
    	throws IOException, ClassNotFoundException {
    	in.defaultReadObject();
    	}
    
    //Recherche dans le Treeset d'une expression
    private static Pattern patterns;
    private static Matcher matchers;
    private Integer nombre;
    public void recherche(String mot) {
    	patterns=Pattern.compile(mot);
    	nombre=0;
    	Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            
                	String champ = String.valueOf(t);
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
        Iterator<Tweets> iter=liste.iterator();
        while(iter.hasNext())
        {
            Tweets t = iter.next();
            s += "-- Actualité " + num + " --\n";
            s += t.toString();
            num++;
        }
        return s;
    }    
}

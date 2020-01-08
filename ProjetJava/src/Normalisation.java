import java.util.ArrayList;
import java.util.List;

//trouver sur internet à adapter.
public class Normalisation {
	
	//Nombre d'occurence d'un mot dans un tweet
	 public static double tweet_occurence(ArrayList<String> mots, String terme) {
	        double result = 0;
	        for (String word : mots) {
	            if (terme.equalsIgnoreCase(word))
	                result++;
	        }
	        return result / mots.size();
	    }

	 	//Nombre d'occurence d'un mot dans un ensemble de tweets
	    public static double total_occurence(ArrayList<ArrayList<String>> docs, String terme) {
	        double n = 0;
	        for (List<String> doc : docs) {
	            for (String word : doc) {
	                if (terme.equalsIgnoreCase(word)) {
	                    n++;
	                    break;
	                }
	            }
	        }
	        return n;
	    }

	    //Calcul du poids d'une expression dans un tweet
	    public static double norm(ArrayList<String> doc, ArrayList<ArrayList<String>> docs, String term) {
	    	return tweet_occurence(doc, term) * total_occurence(docs, term);
	    }      
}

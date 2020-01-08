import java.util.ArrayList;
import java.util.Random;

import javafx.collections.ObservableList;

public class KMeans {
    	
    	public static ArrayList<Tweets> initialisation(Integer nb_c,ObservableList<Tweets> list,ArrayList<Tweets> moy){
    		for (int k=0;k<nb_c;k++) {
            	Random rand=new Random();
            	int m=rand.nextInt(list.size());
            	Tweets t=list.get(m);
            	moy.add(t);
            	
            	
            }
    		return moy;
    	}
    	
    	//Affectation des classes pour chaque tweet
    	public static ArrayList<Tweets> affectation(ArrayList<Tweets> moy, ArrayList<Double> dist,Integer nb_c, ObservableList<Tweets> list,boolean select) {
    		dist.clear();
	        
        	//Pour chaque tweet
        	for(int i=0;i<list.size();i++) {
	        	Integer d=0;
	        	Double min=0.0;
	        	Tweets t=list.get(i);
	        	min=distance(moy.get(0),list.get(i),select);
	        	
        		//Calcul des distances pour les autres centroïdes
        		for (int k=1;k<nb_c;k++) {
	        		Double p=0.0;
	        		p=distance(moy.get(k),list.get(i),select);
	        		if(p>min) {
	        			d=k;
	        			min=p;
	        		}
	        		//Ajout de la classe du tweet et de la distance avec le centroïde
	        		t.setClasse(d+1);
	        		dist.add(min);
	        	}
	        	
	        }
        	//Nouveaux centroïdes
        	moy=centroide(moy,dist,nb_c,list);
        	return moy;
    	}
    	
    	//Recherche des nouveaux centroïdes
	    public static ArrayList <Tweets> centroide (ArrayList<Tweets> moy, ArrayList<Double> dist,Integer nb_c, ObservableList<Tweets> list){
	    	 moy.clear();
		        
		        //Recherche pour chaque classe
		        for (int k=0;k<nb_c;k++) {
		        	Double moyenne=0.0;
		        	Double mini=1000.0;
		        	Integer nb=0;
		        	Tweets m=list.get(0);
		        	
		        	//Calcul de la moyenne
		        	for(int i=0;i<list.size();i++) {
		        		if(list.get(i).getClasse()==k) {
		        			moyenne=moyenne+dist.get(i);
		        			nb=nb+1;
		        		}
		        	}
		        	//Choix du centroïdes le plus proche de la moyenne
	        		for(int i=1;i<list.size();i++) {
	        			if(list.get(i).getClasse()==k) {
		        			if(Math.abs(moyenne - dist.get(i))<mini) {
		        				mini=Math.abs(moyenne - dist.get(i));
		        				m=list.get(i);
		        			}
		        		}
	        		}
	        		//Ajout des nouveaux centroïdes
	        		moy.add(m);
	        	}
		        return moy;
	    } 
	    
	    public static double distance (Tweets moy,Tweets t,boolean select) {
	    	Double d=0.0;
	    	ArrayList<String> m=new ArrayList<String>();
	    	
	    	if(moy.getUtilisateur()==t.getUtilisateur()) {d=d+1;}
    		if(moy.getDate()==t.getDate()) {d=d+1;}
    		if(moy.getRtutilisateur()==t.getUtilisateur()) {d=d+1;}
    		if(moy.getRtutilisateur()==t.getRtutilisateur()) {d=d+1;}
    		if (select==true) {d=d-Math.abs(t.getPoids()-moy.getPoids());}
    		
        	m=moy.getMots();
        	m.retainAll(t.getMots());
        	d=d+m.size();
	    	
	    	return d;
	    }
        
    }


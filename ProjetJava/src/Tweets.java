import java.time.LocalDate;
import java.util.ArrayList;

public class Tweets implements Comparable<Tweets> {
	
	//Composant d'un tweets
	private Integer id;
    private LocalDate date;
    private String utilisateur;
    private String texte;
    private String rtutilisateur;
    private ArrayList<String> Mots; //Liste des mots du tweets
    private double poids; //Poids du tweet (Normalisation)
    private Integer classe; //Classe du tweet (K-Means)
    
    //Constructeur
    public Tweets(Integer id,LocalDate date,String utilisateur,String texte, String rtutilisateur,ArrayList<String> Mots,double poids,Integer classe)
    {
    	this.id=id;
    	this.date=date;
    	this.utilisateur=utilisateur;
		this.texte=texte;
		this.rtutilisateur=rtutilisateur;
		this.Mots=Mots;
		this.poids=poids;
		this.classe=classe;
    }
    
    //Getter et Setter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getRtutilisateur() {
		return rtutilisateur;
	}
	public void setRtutilisateur(String rtutilisateur) {
		this.rtutilisateur = rtutilisateur;
	}
	
	public ArrayList<String> getMots() {
		return Mots;
	}
	public void setMots(ArrayList<String> Mots) {
		this.Mots = Mots;
	}
	
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids=poids;
	}

	public Integer getClasse() {
		return classe;
	}
	public void setClasse(Integer classe) {
		this.classe=classe;
	}
	
	//Fonction de comparaison pour le Treeset
    public int compareTo(Tweets n) {

            if (getUtilisateur().compareTo(n.getUtilisateur())<0) return -1;
            else if (getUtilisateur().compareTo(n.getUtilisateur())>0) return 1;
            else
                    if (getDate().compareTo(n.getDate())<0) return -1;
                    else if (getDate().compareTo(n.getDate())>0) return 1;
                    else return 0;
    }

    
     
}
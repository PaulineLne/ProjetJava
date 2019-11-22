import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tweets implements Comparable,Serializable {
	private Integer id;
    // date de publication
    private LocalDate date;
    private String utilisateur;
    // l'auteur si disponible
    private String texte;
    // la source
    private String rtid;
    
    //Constructeur
    public Tweets(Integer id,LocalDate date,String utilisateur,String texte, String rtid)
    {
    	this.id=id;
    	this.date=date;
    	this.utilisateur=utilisateur;
		this.texte=texte;
		this.rtid=rtid;
    }
    


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



	public String getRtid() {
		return rtid;
	}



	public void setRtid(String rtid) {
		this.rtid = rtid;
	}



	//Fonction de comparaison pour le Treeset
    public int compareTo(Object arg0) {
            // On transtype arg0 (de type Object) en Film :
            Tweets n = (Tweets)arg0;

            if (getId().compareTo(n.getId())<0) return -1;
            else if (getId().compareTo(n.getId())>0) return 1;
            else
                    if (getDate().compareTo(n.getDate())<0) return -1;
                    else if (getDate().compareTo(n.getDate())>0) return 1;
                    else return 0;
    }

    //Modalité d'affichage
    public String toString() {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateTime = getDate().format(formatter);
        
        String affich =  "Nom d'utilisateur : " + getId() + "\n";
        affich +=  "Contenu : " + getTexte() + "\n";
        affich +=  "Date : " + formattedDateTime + "\n";
        affich +=  "Nom de l'utilisateur retweeté : " + getRtid() + "\n";

        return affich;	
    }
    
    
    
}


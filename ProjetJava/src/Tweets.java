import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tweets implements Comparable<Tweets> {
	private Integer id;
    // date de publication
    private LocalDate date;
    private String utilisateur;
    // l'auteur si disponible
    private String texte;
    // la source
    private String rtutilisateur;
    
    //Constructeur
    public Tweets(Integer id,LocalDate date,String utilisateur,String texte, String rtutilisateur)
    {
    	this.id=id;
    	this.date=date;
    	this.utilisateur=utilisateur;
		this.texte=texte;
		this.rtutilisateur=rtutilisateur;
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

	public String getrtutilisateur() {
		return rtutilisateur;
	}

	public void setrtutilisateur(String rtutilisateur) {
		this.rtutilisateur = rtutilisateur;
	}

	//Fonction de comparaison pour le Treeset
    public int compareTo(Tweets arg0) {
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
        
        String affich = "Numéro de tweet : "+ getId() + "\n";
        affich +=  "Nom d'utilisateur : " + getUtilisateur() + "\n";
        affich +=  "Contenu : " + getTexte() + "\n";
        affich +=  "Date : " + formattedDateTime + "\n";
        affich +=  "Nom de l'utilisateur retweeté : " + getrtutilisateur() + "\n";

        return affich;	
    }
     
}
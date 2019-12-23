import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Interface extends Application  {
	@SuppressWarnings("unchecked")
	public Scene construireScene() {
		
		BorderPane borderPaneRoot = new BorderPane();
		Scene scene = new Scene(borderPaneRoot, 800, 200);
		
		// Haut de la fenêtre :
		HBox hbox1 = new HBox();
		Label labelNom = new Label("Quel base de données voulez-vous importer ?");
		Label Base = new Label();
		labelNom.setPadding(new Insets(10));
		Base.setPadding(new Insets(10));
		

		//Choix de la base :
		ToggleGroup group = new ToggleGroup();
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			 public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
	               // Has selection.
	               if (group.getSelectedToggle() != null) {
	            	   ToggleButton button = (ToggleButton) group.getSelectedToggle();
	                   System.out.println("Button: " + button.getText());
	                   Base.setText("Vous avez importé la base de données sur le " + button.getText());
	               }
	           }
		});
			
		// Bouton foot : 
		ToggleButton buttonF = new ToggleButton("foot");
		buttonF.setToggleGroup(group);
		buttonF.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent event)
		{
			MesTweets.creationBase();
			MesTweets.ouvrirBase("Foot.txt");
		}
		});

		
		// Bouton climat
		ToggleButton buttonC = new ToggleButton("climat");
		buttonC.setToggleGroup(group);
		buttonC.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent event)
		{
			MesTweets.creationBase();
			MesTweets.ouvrirBase("climat.txt");
		}
		});
		
		//position
		buttonF.setPadding(new Insets(10));
		buttonC.setPadding(new Insets(10));
		
		hbox1.setPadding(new Insets(10));
		hbox1.setSpacing(10);
		hbox1.getChildren().addAll(labelNom,buttonF,buttonC, Base);

		// VBox :
		VBox vBox1 = new VBox();
		vBox1.setPadding(new Insets(10));
		
		// Barre de recherche
		HBox hbox2 = new HBox();
		//hbox2.setPadding(new Insets(10));
		hbox2.setSpacing(10);
		Label labelNom2 = new Label("Quel est votre recherche :"); 
		labelNom2.setPadding(new Insets(5));
		TextField text1 = new TextField(); 
		Button ButtonR = new Button("Recherche");
		ButtonR.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent event)
		{
			String recherche = text1.getText();
			
			//Tableau d'affichage
			TableView<Tweets> table=MesTweets.fenetre(recherche,1);
			
			
			
			//Nouvelle fenêtre pour l'affichage du tableau
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(table);
            Scene secondScene = new Scene(secondaryLayout, 1000, 600);
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            newWindow.show();
		}
		});
		hbox2.getChildren().addAll(labelNom2, text1,ButtonR);
				
		// Ajout des hbox dans le vbox
		vBox1.getChildren().add(hbox2);
		
		//Choix de la date
		HBox hbox3 = new HBox();
		hbox3.setSpacing(10);
		Label labelNom3 = new Label("Choix de la date "); 
		labelNom3.setPadding(new Insets(5));
		
		ComboBox<String> Date=new ComboBox<String>(); 
		ArrayList<String> listed=BaseDeTweets.ListeDate();
		for(int i=0;i<listed.size();i++) {
			String d=listed.get(i);
			Date.getItems().add(d);
		}
		Date.setEditable(true);
		
		//Boutton de validation
		Button ButtonD = new Button("Valider");
		ButtonD.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent event)
		{
			//Récupération des résultats de la recherche
			String recherche = Date.getValue();
			
			//Tableau d'affichage
			TableView<Tweets> table=MesTweets.fenetre(recherche,4);

			//Nouvelle fenêtre pour l'affichage du tableau
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(table);
            Scene secondScene = new Scene(secondaryLayout, 1000, 1000);
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            newWindow.show();
		}
		});
		
		hbox3.getChildren().addAll(labelNom3,Date,ButtonD);
		
		//Choix de l'utilisateur
		HBox hbox4 = new HBox();
		hbox4.setSpacing(10);
		Label labelNom4 = new Label("Choix de l'utilisateur "); 
		labelNom4.setPadding(new Insets(5));
		
		ComboBox<String> Utilisateur=new ComboBox<String>(); 
		ArrayList<String> listeu=BaseDeTweets.ListeUtilisateur();
		for(int i=0;i<listeu.size();i++) {
			String u=listeu.get(i);
			Utilisateur.getItems().add(u);
		}
		Utilisateur.setEditable(true);
		
		//Boutton de validation
		Button ButtonU = new Button("Valider");
		ButtonU.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent event)
		{
			//Récupération des résultats de la recherche
			String recherche = Utilisateur.getValue();
			
			//Tableau d'affichage
			TableView<Tweets> table=MesTweets.fenetre(recherche,2);

			//Nouvelle fenêtre pour l'affichage du tableau
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(table);
            Scene secondScene = new Scene(secondaryLayout, 500, 500);
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            newWindow.show();
		}
		});
		
		hbox4.getChildren().addAll(labelNom4,Utilisateur,ButtonU);
		
		
		//Choix de l'utilisateur retweeté
		HBox hbox5 = new HBox();
		hbox5.setSpacing(10);
		Label labelNom5 = new Label("Choix de l'utilisateur retweeté "); 
		labelNom5.setPadding(new Insets(5));
		
		ComboBox<String> Rtutilisateur=new ComboBox<String>(); 
		ArrayList<String> listertu=BaseDeTweets.ListeRtutilisateur();
		for(int i=0;i<listertu.size();i++) {
			String u=listertu.get(i);
			Rtutilisateur.getItems().add(u);
		}
		Rtutilisateur.setEditable(true);
		
		//Boutton de validation
		Button ButtonRtu = new Button("Valider");
		ButtonRtu.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent event)
		{
			//Récupération des résultats de la recherche
			String recherche = Rtutilisateur.getValue();
			
			//Tableau d'affichage
			TableView<Tweets> table=MesTweets.fenetre(recherche,3);
		
			
			//Nouvelle fenêtre pour l'affichage du tableau
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(table);
            Scene secondScene = new Scene(secondaryLayout, 500, 500);
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            newWindow.show();
		}
		});
		
		hbox5.getChildren().addAll(labelNom5,Rtutilisateur,ButtonRtu);
		
		
		
		// Ajout des hbox dans le vbox
		vBox1.getChildren().add(hbox3);
		vBox1.getChildren().add(hbox4);
		vBox1.getChildren().add(hbox5);
	
		
		borderPaneRoot.setCenter(vBox1); 
		borderPaneRoot.setTop(hbox1);
		
		return scene;
		
	}
	

public void start(Stage primaryStage)
{
	Stage myStage = primaryStage;
	primaryStage.setTitle("Faire une recherche");
	primaryStage.setScene(construireScene());
	primaryStage.show();
	

}

public static void main(String[] args)
{
	MesTweets.creationBase();
	//MesTweets.ouvrirBase("test.txt");
	launch(args);
}
	
	
}
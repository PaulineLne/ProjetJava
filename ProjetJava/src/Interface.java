import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Interface extends Application {
	public Scene construireScene() {
		
		BorderPane borderPaneRoot = new BorderPane();
		Scene scene = new Scene(borderPaneRoot, 700, 300);
		
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
			MesTweets.rechercher(recherche);
		}
		});
		hbox2.getChildren().addAll(labelNom2, text1,ButtonR);
		
		
		
		// Bouton de selection du trie
		
		Label Trier = new Label("Trier les données par :");
		Trier.setPadding(new Insets(20,10,10,10));
		//HBox hbox3 = new HBox();
		//hbox3.setPadding(new Insets(10));
		//hbox3.setSpacing(10);
		
		ToggleGroup group2 = new ToggleGroup();
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			 public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
	               // Has selection.
	               if (group2.getSelectedToggle() != null) {
	            	   ToggleButton button = (ToggleButton) group2.getSelectedToggle();
	                   System.out.println("Button: " + button.getText());
	               }
	           }
		});
			
		// Bouton annee : 
		RadioButton buttonA = new RadioButton("Année");
		buttonA.setToggleGroup(group2);
		buttonA.setSelected(true);
		buttonA.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent event)
		{
			// Fonction de trie par année
		}
		});

		// Bouton utilisateur
		RadioButton buttonU = new RadioButton("Utilisateur");
		buttonU.setToggleGroup(group2);
		buttonU.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent event)
		{
			//Fonction de trie par utilisateur
		}
		});
		
		// Bouton nb de retweet
		RadioButton buttonRt = new RadioButton("Nombre de retweets");
		buttonRt.setToggleGroup(group2);
		buttonRt.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				//Fonction de trie par nombre de retweet
			}
		});
		
		//position
		
		buttonA.setPadding(new Insets(10));
		buttonU.setPadding(new Insets(10));
		buttonRt.setPadding(new Insets(10));

		// Ajout dans le hbox2
		//hbox2.getChildren().addAll(Trier,buttonA,buttonU, buttonRt)
		
		// Ajout des hbox dans le vbox
		vBox1.getChildren().add(hbox2);
		vBox1.getChildren().add(Trier);
		vBox1.getChildren().add(buttonA);
		vBox1.getChildren().add(buttonU);
		vBox1.getChildren().add(buttonRt);
		
		
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
	launch(args);
}
	
	
}
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Fenetre extends Application {
	public Scene construitScene()
	{
	GridPane grid = new GridPane();
	/*MenuBar menuBar = new MenuBar();
	Menu menu1 = new Menu("Menu1");
	Menu menu2 = new Menu("Menu2");
	menuBar.getMenus().addAll(menu1,menu2);
	MenuItem menuItem1 = new MenuItem("MenuItem1");
	menu1.getItems().addAll(menuItem1,new MenuItem("MenuItem2"));*/
	
	Label label1 = new Label("");
	TextField text1 = new TextField();
	text1.setText("");

	
	Button button1 = new Button("Bouton1");
	button1.setText("Rechercher");
	button1.setOnAction(new EventHandler<ActionEvent>()
	{
	public void handle(ActionEvent event)
	{
		String recherche = text1.getText();
		MesTweets.rechercher(recherche);
	}
	});
	
	Button button2 = new Button("Bouton2");
	button2.setText("Foot");
	button2.setOnAction(new EventHandler<ActionEvent>()
	{
	public void handle(ActionEvent event)
	{
		MesTweets.creationBase();
		MesTweets.ouvrirBase("foot.txt");
	}
	});
	
	Button button3 = new Button("Bouton3");
	button3.setText("Climat");
	button3.setOnAction(new EventHandler<ActionEvent>()
	{
	public void handle(ActionEvent event)
	{
		MesTweets.creationBase();
		MesTweets.ouvrirBase("climat.txt");
	}
	});
	
	
	HBox hBox1 = new HBox(10);
	HBox hBox2 = new HBox(10);
	//hBox.getChildren().addAll(label, textField, button1);

	hBox1.getChildren().addAll(button2,button3);
	hBox2.getChildren().addAll(label1,text1,button1);

	
	Text text = new Text("Mon premier texte");
	Separator separator = new Separator();
	/*GridPane.setConstraints(menuBar, 0, 0);
	grid.getChildren().add(menuBar);*/
	GridPane.setConstraints(hBox1, 0, 1);
	grid.getChildren().add(hBox1);
	GridPane.setConstraints(separator, 0, 8);
	grid.getChildren().add(separator);
	GridPane.setConstraints(text, 0, 9);
	grid.getChildren().add(text);
	StackPane root = new StackPane();
	root.getChildren().addAll(grid);
	Scene scene = new Scene(root, 500, 300, Color.RED);
	// Scene scene = new Scene(root);
	return scene;
	}
	
public void start(Stage primaryStage)
{
Stage myStage = primaryStage;
primaryStage.setTitle("Faire une recherche");
primaryStage.setScene(construitScene());
primaryStage.sizeToScene();
primaryStage.show();
}

public static void main(String[] args)
{
	//MesTweets.creationBase();
	//MesTweets.ouvrirBase();
	launch(args);
}

}
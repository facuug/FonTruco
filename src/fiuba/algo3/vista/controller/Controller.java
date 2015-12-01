package fiuba.algo3.vista.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.interfaces.JuegoTruco;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Controller implements Initializable {
	protected static Stage stage;
	
	public static JuegoTruco juegoTruco;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public static void redirect(String nombreVista){
		Pane pane;
		try {
			 pane = (Pane)FXMLLoader.load(Main.class.getResource(new StringBuilder().append("../").append(nombreVista).append(".fxml").toString()));
			 Scene scene = new Scene(pane);
			 stage.setScene(scene);
			 stage.close();
			 stage.setTitle(new StringBuilder().append(nombreVista).toString());
			 stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void popup(String nombreVista) {
		Pane pane;
		try {
			 pane = (Pane)FXMLLoader.load(Main.class.getResource(new StringBuilder().append("../").append(nombreVista).append(".fxml").toString()));
			 Scene scene = new Scene(pane);
			 
			 Stage popup = new Stage();
			 popup.initModality(Modality.APPLICATION_MODAL);
			 popup.initOwner(stage);
			 popup.setScene(scene);
			 popup.setTitle(new StringBuilder().append(nombreVista).toString());
			 popup.showAndWait();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

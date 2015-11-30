package fiuba.algo3.vista.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.interfaces.JuegoTruco;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
	public void redirect(String nombreVista){
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
	
	public void popup(String nombreVista) {
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
	
	public String armarRutaImagen(Carta carta) {
		return new StringBuilder().append("src/fiuba/algo3/vista/recursos/carta/")
				.append(carta.getPalo().toString().toLowerCase())
				.append("/").append(String.valueOf(carta.getTipoCarta().getValorRealCarta()))
				.append(".png").toString();
	}	
	
	public void plasmarCartaEnImageView(Mano unaMano, List<ImageView> cartasAMostrar) {
		
		int posicionCarta = 0;
		for(Carta carta : unaMano.getCartas()) {
			String rutaImagen = armarRutaImagen(carta);
			File archivoCarta = new File(rutaImagen);
			Image pngCarta = new Image(archivoCarta.toURI().toString());
			cartasAMostrar.get(posicionCarta).setImage(pngCarta);
			posicionCarta+=1;
		}
	}
}

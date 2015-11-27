package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.vista.controller.handler.CartaHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MesaController extends Controller{
	
	//Cada control de javafx tiene por nombre el id:fx del control que esta en Mesa.fxml
	
	private static int cantidadJugadores = 0;
	
	private static Mesa mesa;
	
	public static int getCantidadJugadores() {
		return cantidadJugadores;
	}

	public static void setCantidadJugadores(int cantidadJugadores) {
		MesaController.cantidadJugadores = cantidadJugadores;
	}

	@FXML
	private Button btnSalir;
	@FXML
    private Button btnVolver;
	@FXML
    private Button btnEnvido;
	@FXML
    private Button btnTruco;
	@FXML
    private Button btnFlor;
	
	@FXML
    private ImageView carta1Jug1;
	@FXML
    private ImageView carta2Jug1;
	@FXML
    private ImageView carta3Jug1;
	@FXML
    private ImageView carta1Jug2;
	@FXML
    private ImageView carta2Jug2;
	@FXML
    private ImageView carta3Jug2;
	@FXML
    private ImageView carta1Jug3;
	@FXML
    private ImageView carta2Jug3;
	@FXML
    private ImageView carta3Jug3;
	@FXML
    private ImageView carta1Jug4;
	@FXML
    private ImageView carta2Jug4;
	@FXML
    private ImageView carta3Jug4;
	
	List<List<ImageView>> cartasJugando;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		cartasJugando = new ArrayList<>(Arrays.asList(
				Arrays.asList(carta1Jug1,carta2Jug1,carta3Jug1),
				Arrays.asList(carta1Jug2,carta2Jug2,carta3Jug2),
				Arrays.asList(carta1Jug3,carta2Jug3,carta3Jug3),
				Arrays.asList(carta1Jug4,carta2Jug4,carta3Jug4)));
		prepararMesa();
		setImageViewCartaHandler();
	}

	public static Mesa getMesa() {
		return mesa;
	}

	public static void setMesa(Mesa mesa) {
		MesaController.mesa = mesa;
	}
	
	private void prepararMesa() {
		if(cantidadJugadores == 2) {
			esconderCartas();
		}
	}
	
	private void setImageViewCartaHandler() {
		//for ( ImageView carta : cartasJugando) {
			//carta.setOnMouseClicked(new CartaHandler(carta, cartasJugando));
		//}
		
	}
	
	private void esconderCartas() {
		
		for(int i=1; i<3;i++) {
			List<ImageView> cartasNoUsadas = cartasJugando.get(i);
			for(ImageView cartaAEsconder : cartasNoUsadas) {
				cartaAEsconder.setVisible(false);
				
			}
			cartasJugando.remove(i);
		}
	}
	
}

package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import fiuba.algo3.vista.controller.handler.CartaHandler;
import fiuba.algo3.vista.controller.handler.btnSalirHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MesaController extends Controller{
	
	//Cada control de javafx tiene por nombre el id:fx del control que esta en Mesa.fxml
	
	@FXML
	private Button btnSalir;
	@FXML
	private Button btnAyuda;
	@FXML
    private Button btnVolver;
	@FXML
	private Button btnJugarCarta;
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
	
	private int cantidadDeJugadores = 4; //sacar el hardcodeo!!!!!!!!!!
	
	private int numeroCartaJugador = 0;
	
	private List<ImageView> cartasEnMano;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		 cartasEnMano = new ArrayList<ImageView>(Arrays.asList(carta1Jug1,carta2Jug1,carta3Jug1,
				carta1Jug2,carta2Jug2,carta3Jug2,
				carta1Jug3,carta2Jug3,carta3Jug3,
				carta1Jug4,carta2Jug4,carta3Jug4));
		btnSalirHandler();
		btnAyudaHandler();
		btnVolverHandler();
		cartasHandler();
		btnJugarCartaHandler();
	}
	
	public void btnSalirHandler() {
		btnSalir.setOnAction(new btnSalirHandler());
	}
	
	private void btnAyudaHandler() {
		btnAyuda.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup("Ayuda");
			}
		});
	}
	
	public void btnVolverHandler() {
		btnVolver.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				redirect("MenuPrincipal");
			}
		});
	}

	public void btnJugarCartaHandler() {
		btnJugarCarta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				rotarJugador();
			}
		});
	}
	public void btnTrucoHandler() {
		btnTruco.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			}
		});
	}
	public void btnEnvidoHandler() {
		btnEnvido.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			}
		});
	}
	public void btnFlorHandler() {
		btnFlor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void cartasHandler() {
		for (ImageView carta : cartasEnMano) {
			carta.setOnMouseClicked(new CartaHandler(carta, cartasEnMano));
		}
	}
	
	public void rotarJugador() {
		habilitarCarta();
		numeroCartaJugador= numeroCartaJugador+3;
		if(numeroCartaJugador>11){
			numeroCartaJugador=0;
		}
		
		habilitarCarta();
	
	}
	
	public void habilitarCarta() {
		for(int i=numeroCartaJugador;i<numeroCartaJugador+3;i++){
			cartasEnMano.get(i).setDisable(!cartasEnMano.get(i).isDisable());
		}
		
	}
}

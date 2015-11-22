package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPrincipalController extends Controller {

	@FXML
	Button btnJugar;
	@FXML
    Button btnAyuda;
	@FXML
    Button btnSalir;
	
	@FXML
	Button btnDosJugadores;
	@FXML
    Button btnCuatroJugadores;
	@FXML
    Button btnPicaPica;
	@FXML
    Button btnVolver;         
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		btnJugarHandler();
		btnVolverHandler();
	}
	
	private EventHandler<ActionEvent> esconderOtrosBotonesHandler = new EventHandler<ActionEvent>(){

	    @Override
	    public void handle(final ActionEvent event) {
	    	cambiarVisibilidadDeBotones();
	    }
	};
	private void btnJugarHandler() {
		btnJugar.setOnAction(esconderOtrosBotonesHandler);
	}

	private void btnVolverHandler() {
		btnVolver.setOnAction(esconderOtrosBotonesHandler);
	}
	private void cambiarVisibilidadDeBotones() {
		cambiarVisibilidadBoton(btnDosJugadores);
		cambiarVisibilidadBoton(btnCuatroJugadores);
		cambiarVisibilidadBoton(btnPicaPica);
		cambiarVisibilidadBoton(btnAyuda);
		cambiarVisibilidadBoton(btnJugar);
		cambiarVisibilidadBoton(btnSalir);
		cambiarVisibilidadBoton(btnVolver);
	}
	
	private void cambiarVisibilidadBoton(Button unBoton) {
		unBoton.setVisible(!unBoton.isVisible());
	}
}

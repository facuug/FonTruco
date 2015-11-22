package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPrincipalController extends Controller {

	@FXML
	private Button btnJugar;
	@FXML
	private Button btnAyuda;
	@FXML
	private Button btnSalir;
	
	@FXML
	private Button btnDosJugadores;
	@FXML
	private Button btnCuatroJugadores;
	@FXML
	private Button btnPicaPica;
	@FXML
	private Button btnVolver;         
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		btnJugarHandler();
		btnVolverHandler();
		btnSalirHandler();
		btnAyudaHandler();
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
	
	private void btnSalirHandler() {
		btnSalir.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
	}
	
	private void btnAyudaHandler() {
		btnAyuda.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				redirect("Ayuda");
			}
		});
	}
}

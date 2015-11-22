package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fiuba.algo3.vista.controller.handler.btnSalirHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
	
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		btnSalirHandler();
		btnAyudaHandler();
		btnVolverHandler();
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
				// TODO Auto-generated method stub
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
}

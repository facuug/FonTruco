package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PopUpGanadorController extends Controller{

	@FXML
	Button btnVolver;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		btnVolverHandler();
	}
	
	private void btnVolverHandler() {
		btnVolver.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				redirect("MenuPrincipal");
			}
		});
	}
	
}

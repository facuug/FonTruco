package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AyudaController extends Controller{

	@FXML
	private Button btnVolver;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		btnAyudaHandler();
		
	}
	
	public void btnAyudaHandler() {
		btnVolver.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				redirect("MenuPrincipal");
			}
		});
	}

}

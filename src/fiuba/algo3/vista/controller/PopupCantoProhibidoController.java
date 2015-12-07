package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PopupCantoProhibidoController extends Controller {

	@FXML
	private Button btnOk;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		btnVolverHandler();
	}

	private void btnVolverHandler() {
		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Node fuente = (Node) event.getSource();
				((Stage)fuente.getScene().getWindow()).close();
			}
		});
	}
	
}

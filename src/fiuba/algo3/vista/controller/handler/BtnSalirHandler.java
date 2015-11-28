package fiuba.algo3.vista.controller.handler;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BtnSalirHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		Platform.exit();
	}
	
}

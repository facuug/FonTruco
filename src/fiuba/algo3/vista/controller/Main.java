package fiuba.algo3.vista.controller;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		 try {
			 	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/MenuPrincipal.fxml"));
	            Pane page = (Pane) fxmlLoader.load();
	            Scene scene = new Scene(page);
	            MenuPrincipalController menuPrincipalController = (MenuPrincipalController)fxmlLoader.getController();
	            stage.setScene(scene);
	            stage.setTitle("FonTruco");
	            menuPrincipalController.setStage(stage);
	            stage.show();
	        } catch (Exception ex) {
	           ex.printStackTrace();
	        }
	}

}

package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Mesa;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class MesaDeSeisController extends MesaGeneralController{

	
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
	@FXML
	private ImageView carta1Jug5;
	@FXML
	private ImageView carta2Jug5;
	@FXML
	private ImageView carta3Jug5;
	@FXML
	private ImageView carta1Jug6;
	@FXML
	private ImageView carta2Jug6;
	@FXML
	private ImageView carta3Jug6;
	
	private List<List<ImageView>> cartasJugando;
	
	public static Mesa mesa;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		cartasJugando = new ArrayList<List<ImageView>>(Arrays.asList(
				new ArrayList<>(Arrays.asList(carta1Jug1, carta2Jug1, carta3Jug1)),
				new ArrayList<>(Arrays.asList(carta1Jug2,carta2Jug2,carta3Jug2)),
				new ArrayList<>(Arrays.asList(carta1Jug3,carta2Jug3,carta3Jug3)),
				new ArrayList<>(Arrays.asList(carta1Jug4,carta2Jug4,carta3Jug4)),
				new ArrayList<>(Arrays.asList(carta1Jug5,carta2Jug5,carta3Jug5)),
				new ArrayList<>(Arrays.asList(carta1Jug6,carta2Jug6,carta3Jug6))));
	}

	
	
	
	
}

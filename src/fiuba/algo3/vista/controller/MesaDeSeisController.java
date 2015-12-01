package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.interfaces.MesaGeneralController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	@FXML
	private Button btnSalir;
	@FXML
    private Button btnVolver;
	@FXML
    private Button btnEnvido3;
	@FXML
    private Button btnTruco;
	@FXML
    private Button btnFlor;
	@FXML
    private Button btnTruco1;
	@FXML
    private Button btnTruco11;
	@FXML
    private Button btnEnvido1;
	@FXML
    private Button btnEnvido4;
	@FXML
    private Button btnNoQuiero;
	@FXML
    private Button btnQuiero;
	@FXML
    private Button btnFlor1;
	@FXML
    private Button btnFlor11;
	
	@FXML
    private Label lblPuntosEq1;
	@FXML
    private Label lblPuntosEq2;
	
	@FXML
	private ImageView contenedor1;
	@FXML
	private ImageView contenedor2;
	@FXML
	private ImageView contenedor3;
	@FXML
	private ImageView contenedor4;
	@FXML
	private ImageView contenedor5;
	@FXML
	private ImageView contenedor6;
	
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

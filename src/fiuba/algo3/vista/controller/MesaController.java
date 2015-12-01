package fiuba.algo3.vista.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.vista.controller.handler.BtnSalirHandler;
import fiuba.algo3.vista.controller.handler.CartaHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MesaController extends MesaGeneralController{
	
	//Cada control de javafx tiene por nombre el id:fx del control que esta en Mesa.fxml
	
	private static int cantidadJugadores = 0;
	
	public static Mesa mesa;
	
	public static int getCantidadJugadores() {
		return cantidadJugadores;
	}

	public static void setCantidadJugadores(int cantidadJugadores) {
		MesaController.cantidadJugadores = cantidadJugadores;
	}

	
	
	@FXML
    private Label lblPuntosEq1;
	@FXML
    private Label lblPuntosEq2;
	
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
	private ImageView contenedor1;
	@FXML
	private ImageView contenedor2;
	@FXML
	private ImageView contenedor3;
	@FXML
	private ImageView contenedor4;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		cartasJugando = new ArrayList<List<ImageView>>(Arrays.asList(
				new ArrayList<>(Arrays.asList(carta1Jug1, carta2Jug1, carta3Jug1)),
				new ArrayList<>(Arrays.asList(carta1Jug2,carta2Jug2,carta3Jug2)),
				new ArrayList<>(Arrays.asList(carta1Jug3,carta2Jug3,carta3Jug3)),
				new ArrayList<>(Arrays.asList(carta1Jug4,carta2Jug4,carta3Jug4))));
		setBtnVolverHandler();
		setBtnSalirHandler();
		setBtnTrucoHandler();
		setBtnReTrucoHandler();
		setBtnValeCuatroHandler();
		setBtnEnvidoHandler();
		setBtnRealEnvidoHandler();
		setBtnFaltaEnvidoHandler();
		setBtnNoQuieroHandler();
		setBtnQuieroHandler();
		setBtnFlorHandler();
		setBtnContraFlorHandler();
		setBtnContraFlorAlRestoHandler();
		contenedores = new ArrayList<ImageView>(Arrays.asList(contenedor1,contenedor2,contenedor3,contenedor4));
		prepararMesa();
		mesa.repartir();
		setImageViewCartaHandler();
		mostrarCartas();
	}
	
	public void setBtnSalirHandler() {
		btnSalir.setOnAction(new BtnSalirHandler());
	}

	public void setBtnVolverHandler() {
		btnVolver.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				redirect("MenuPrincipal");
			}
		});
	}
	
	private void setBtnTrucoHandler(){
		btnTruco.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.truco();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnEnvidoHandler(){
		btnEnvido3.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.envido();
				}catch ( CantoInvalidoException exception ){
					System.out.println("cantoInvalido");
				}
			}
		});
	}
	
	private void setBtnReTrucoHandler(){
		btnTruco1.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.reTruco();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnValeCuatroHandler(){
		btnTruco11.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.valeCuatro();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnRealEnvidoHandler(){
		btnEnvido1.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.realEnvido();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnFaltaEnvidoHandler(){
		btnEnvido4.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.faltaEnvido();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnQuieroHandler(){
		btnQuiero.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.quiero();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnNoQuieroHandler(){
		btnNoQuiero.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.noQuiero();
					if(Controller.juegoTruco.manoFinalizada()){
						Controller.juegoTruco.sumarPuntos();
						lblPuntosEq1.setText( Integer.toString(Controller.juegoTruco.puntosEquipoUno()) );
						lblPuntosEq2.setText( Integer.toString(Controller.juegoTruco.puntosEquipoDos()) );
					}
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnFlorHandler(){
		btnFlor.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.flor();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnContraFlorHandler(){
		btnFlor1.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.contraFlor();
				}catch ( CantoInvalidoException exception ){
				}
			}
		});
	}
	
	private void setBtnContraFlorAlRestoHandler(){
		btnFlor11.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.contraFlorAlResto();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	public Mesa getMesa() {
		return mesa;
	}

	public static void setMesa(Mesa mesa) {
		MesaController.mesa = mesa;
	}
	
	public void prepararMesa() {
		if(cantidadJugadores == 2) {
			esconderCartas();
		}
	}
	
	private void setImageViewCartaHandler() {

		List<Equipo> equipos = mesa.getEquipos();
		List<Mano> manos = obtenerManosIntercaladas();
		int i = 0;
		for(List<ImageView> cartasEnMano : cartasJugando) {
			int j = 0;
			for(ImageView carta : cartasEnMano) {
				carta.setOnMouseClicked(new CartaHandler(cartasEnMano,cartasJugando,manos.get(i).getCartas().get(j),lblPuntosEq1,lblPuntosEq2));
				((CartaHandler)carta.getOnMouseClicked()).setContendorEnMesa(contenedores.get(i));
				j++;
			}
			i++;
		}
	}
	
	public static List<Mano> obtenerManosIntercaladas() {
		int posicionOtraMano = 0;
		List<Jugador> equipoUno = mesa.getEquipos().get(0).getJugadores();
		List<Jugador> equipoDos = mesa.getEquipos().get(1).getJugadores();
		List<Mano> manos = new ArrayList<>();
		for(Jugador jugador : equipoUno) {
			manos.add(jugador.getMano());
			manos.add(equipoDos.get(posicionOtraMano).getMano());
			posicionOtraMano++;
		}
		return manos;
	}
	
	private void esconderCartas() {
		for(int i=1; i<3;i++) {
			List<ImageView> cartasNoUsadas = cartasJugando.get(i);
			for(ImageView cartaAEsconder : cartasNoUsadas) {
				cartaAEsconder.setVisible(false);
			}
			cartasJugando.remove(i);
			contenedores.remove(i);
		}
	}
	
	private void mostrarCartas() {
		List<Equipo> equipos = mesa.getEquipos();
		List<ImageView> vistaCartas = cartasJugando.get(0);
		plasmarCartaEnImageView(equipos.get(0).getJugadores().get(0).getMano(),vistaCartas);
	}
}

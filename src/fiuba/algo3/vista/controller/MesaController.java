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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MesaController extends Controller{
	
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
	
	List<List<ImageView>> cartasJugando;
	List<ImageView> contenedores;
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
					Controller.juegoTruco.truco();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnEnvidoHandler(){
		btnEnvido3.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					Controller.juegoTruco.envido();
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
					Controller.juegoTruco.reTruco();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnValeCuatroHandler(){
		btnTruco11.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					Controller.juegoTruco.valeCuatro();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnRealEnvidoHandler(){
		btnEnvido1.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					Controller.juegoTruco.realEnvido();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnFaltaEnvidoHandler(){
		btnEnvido4.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					Controller.juegoTruco.faltaEnvido();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnQuieroHandler(){
		btnQuiero.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					Controller.juegoTruco.quiero();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnNoQuieroHandler(){
		btnNoQuiero.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					Controller.juegoTruco.noQuiero();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnFlorHandler(){
		btnFlor.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					Controller.juegoTruco.flor();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	private void setBtnContraFlorHandler(){
		btnFlor1.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					Controller.juegoTruco.contraFlor();
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
					Controller.juegoTruco.contraFlorAlResto();
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
				carta.setOnMouseClicked(new CartaHandler(cartasEnMano,cartasJugando,manos.get(i).getCartas().get(j)));
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
		plasmarCartaEnImageView(equipos.get(0).getJugadores().get(0).getMano(),0);
	}
	
	private void plasmarCartaEnImageView(Mano unaMano, int posicionJugador) {
		List<ImageView> vistaCartas = cartasJugando.get(posicionJugador);
		int posicionCarta = 0;
		for(Carta carta : unaMano.getCartas()) {
			String rutaImagen = armarRutaImagen(carta);
			File archivoCarta = new File(rutaImagen);
			Image pngCarta = new Image(archivoCarta.toURI().toString());
			vistaCartas.get(posicionCarta).setImage(pngCarta);
			posicionCarta+=1;
		}
	}
	
	private String armarRutaImagen(Carta carta) {
		return new StringBuilder().append("src/fiuba/algo3/vista/recursos/carta/")
				.append(carta.getPalo().toString().toLowerCase())
				.append("/").append(String.valueOf(carta.getTipoCarta().getValorRealCarta()))
				.append(".png").toString();
	}
}

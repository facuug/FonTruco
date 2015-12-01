package fiuba.algo3.modelo.interfaces;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.vista.controller.Controller;
import fiuba.algo3.vista.controller.handler.BtnSalirHandler;
import fiuba.algo3.vista.controller.handler.CartaHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MesaGeneralController extends Controller{
	
	@FXML
	protected Button btnSalir;
	@FXML
    protected Button btnVolver;
	@FXML
    protected Button btnEnvido3;
	@FXML
    protected Button btnTruco;
	@FXML
    protected Button btnFlor;
	@FXML
    protected Button btnTruco1;
	@FXML
    protected Button btnTruco11;
	@FXML
    protected Button btnEnvido1;
	@FXML
    protected Button btnEnvido4;
	@FXML
    protected Button btnNoQuiero;
	@FXML
    protected Button btnQuiero;
	@FXML
    protected Button btnFlor1;
	@FXML
    protected Button btnFlor11;
	
	@FXML
    protected Label lblPuntosEq1;
	@FXML
    protected Label lblPuntosEq2;
	
	public static Mesa mesa;
	
	public static int cantidadJugadores = 0;
	
	public String armarRutaImagen(Carta carta) {
		return new StringBuilder().append("src/fiuba/algo3/vista/recursos/carta/")
				.append(carta.getPalo().toString().toLowerCase())
				.append("/").append(String.valueOf(carta.getTipoCarta().getValorRealCarta()))
				.append(".png").toString();
	}	
	
	public void plasmarCartaEnImageView(Mano unaMano, List<ImageView> cartasAMostrar) {
		
		int posicionCarta = 0;
		for(Carta carta : unaMano.getCartas()) {
			String rutaImagen = armarRutaImagen(carta);
			File archivoCarta = new File(rutaImagen);
			Image pngCarta = new Image(archivoCarta.toURI().toString());
			cartasAMostrar.get(posicionCarta).setImage(pngCarta);
			posicionCarta+=1;
		}
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
	
	protected void setBtnTrucoHandler(){
		btnTruco.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.truco();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	protected void setBtnEnvidoHandler(){
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
	
	protected void setBtnReTrucoHandler(){
		btnTruco1.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.reTruco();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	protected void setBtnValeCuatroHandler(){
		btnTruco11.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.valeCuatro();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	protected void setBtnRealEnvidoHandler(){
		btnEnvido1.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.realEnvido();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	protected void setBtnFaltaEnvidoHandler(){
		btnEnvido4.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.faltaEnvido();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	protected void setBtnQuieroHandler(){
		btnQuiero.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.quiero();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	protected void setBtnNoQuieroHandler(){
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
	
	protected void setBtnFlorHandler(){
		btnFlor.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.flor();
				}catch ( CantoInvalidoException exception ){}
			}
		});
	}
	
	protected void setBtnContraFlorHandler(){
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
	
	protected void setBtnContraFlorAlRestoHandler(){
		btnFlor11.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				try{
					juegoTruco.contraFlorAlResto();
				}catch ( CantoInvalidoException exception ){}
			}
		});
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
	
	

	
}

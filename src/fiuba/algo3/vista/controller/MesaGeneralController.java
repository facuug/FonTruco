package fiuba.algo3.vista.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.vista.controller.handler.BtnSalirHandler;
import fiuba.algo3.vista.controller.handler.CartaHandlerGeneral;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class MesaGeneralController extends Controller {

	@FXML
	protected Button btnSalir;
	@FXML
	protected Button btnVolver;
	@FXML
	protected Button btnEnvido;
	@FXML
	protected Button btnTruco;
	@FXML
	protected Button btnFlor;
	@FXML
	protected Button btnReTruco;
	@FXML
	protected Button btnValeCuatro;
	@FXML
	protected Button btnRealEnvido;
	@FXML
	protected Button btnFaltaEnvido;
	@FXML
	protected Button btnNoQuiero;
	@FXML
	protected Button btnQuiero;
	@FXML
	protected Button btnContraFlor;
	@FXML
	protected Button btnContraFlorAlResto;

	protected static List<List<ImageView>> cartasJugando;
	protected static List<ImageView> contenedores;
	@FXML
	protected Label lblPuntosEq1;
	@FXML
	protected Label lblPuntosEq2;
	public static Mesa mesa;
	
	private MediaPlayer mediaPlayer;
	

	public static String armarRutaImagen(Carta carta) {
		return new StringBuilder().append("src/fiuba/algo3/vista/recursos/carta/")
				.append(carta.getPalo().toString().toLowerCase()).append("/")
				.append(String.valueOf(carta.getTipoCarta().getValorRealCarta())).append(".png").toString();
	}

	public void plasmarCartaEnImageView(Mano unaMano, List<ImageView> cartasAMostrar) {

		int posicionCarta = 0;
		for (Carta carta : unaMano.getCartas()) {
			String rutaImagen = armarRutaImagen(carta);
			File archivoCarta = new File(rutaImagen);
			Image pngCarta = new Image(archivoCarta.toURI().toString());
			cartasAMostrar.get(posicionCarta).setImage(pngCarta);
			cartasAMostrar.get(posicionCarta).setDisable(false);
			posicionCarta += 1;
		}
	}

	private void setBtnSalirHandler() {
		btnSalir.setOnAction(new BtnSalirHandler());
	}

	private void setBtnVolverHandler() {
		btnVolver.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				redirect("MenuPrincipal");
			}
		});
	}

	private void setBtnTrucoHandler() {
		btnTruco.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()){
						
						juegoTruco.getEnfrentamientoActual().truco();
					} else {
						juegoTruco.truco();
					}
					ejecutarAudio("truco");
				} catch (CantoInvalidoException exception) { 
					popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnEnvidoHandler() {
		btnEnvido.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()) {
						
						juegoTruco.getEnfrentamientoActual().envido();
					} else {
						juegoTruco.envido();
					}
					ejecutarAudio("envido");
				} catch (CantoInvalidoException exception) { 
					popup("popupCantoProhibido");
					System.out.println("cantoInvalido");
				}
			}
		});
	}

	private void setBtnReTrucoHandler() {
		btnReTruco.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()) {
						
						juegoTruco.getEnfrentamientoActual().reTruco();
					} else {
						juegoTruco.reTruco();
					}
					ejecutarAudio("reTruco");
				} catch (CantoInvalidoException exception) { 
					popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnValeCuatroHandler() {
		btnValeCuatro.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()){
						
						juegoTruco.getEnfrentamientoActual().valeCuatro();
					} else {
						juegoTruco.valeCuatro();
					}
					ejecutarAudio("valeCuatro");
				} catch (CantoInvalidoException exception) { 
					popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnRealEnvidoHandler() {
		btnRealEnvido.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()){
						
						juegoTruco.getEnfrentamientoActual().realEnvido();
					} else {
						juegoTruco.realEnvido();
					}
					ejecutarAudio("realEnvido");
				} catch (CantoInvalidoException exception) { 
					popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnFaltaEnvidoHandler() {
		btnFaltaEnvido.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()){
						
						juegoTruco.getEnfrentamientoActual().faltaEnvido();
					} else {
						
						juegoTruco.faltaEnvido();
					}
					ejecutarAudio("faltaEnvido");
				} catch (CantoInvalidoException exception) { 
					popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnQuieroHandler() {
		btnQuiero.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()){
						
						juegoTruco.getEnfrentamientoActual().quiero();
					} else {
						juegoTruco.quiero();
					}
					ejecutarAudio("quiero");
				} catch (CantoInvalidoException exception) { 
					popup("popupCantoProhibido");
					exception.printStackTrace();
				}
			}
		});
	}
	private void setBtnNoQuieroHandler() {
		btnNoQuiero.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					juegoTruco.noQuiero();
					Controller.juegoTruco.sumarPuntos();
					lblPuntosEq1.setText(Integer.toString(Controller.juegoTruco.puntosEquipoUno()));
					lblPuntosEq2.setText(Integer.toString(Controller.juegoTruco.puntosEquipoDos()));
					((CartaHandlerGeneral)(cartasJugando.get(0).get(0).getOnMouseClicked())).actualizar();
					ejecutarAudio("noQuiero");
				} catch (CantoInvalidoException exception) { 
					exception.printStackTrace();
					popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnFlorHandler() {
		btnFlor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()){
						
						juegoTruco.getEnfrentamientoActual().flor();
					} else {
						juegoTruco.flor();
					}
					ejecutarAudio("flor");
				} catch (CantoInvalidoException exception) { 
					popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnContraFlorHandler() {
		btnContraFlor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()){
						
						juegoTruco.getEnfrentamientoActual().contraFlor();
					} else { 
						juegoTruco.contraFlor();
					}
					ejecutarAudio("contraFlor");
				} catch (CantoInvalidoException exception) { popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnContraFlorAlRestoHandler() {
		btnContraFlorAlResto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if(juegoTruco.esPicaPica()) {
						
						juegoTruco.getEnfrentamientoActual().contraFlorAlResto();
					} else {
					juegoTruco.contraFlorAlResto();
					}
					ejecutarAudio("contraFlorResto");
				} catch (CantoInvalidoException exception) { popup("popupCantoProhibido");
				}
			}
		});
	}
	
	protected void ejecutarAudio(String nombre) {
		File f = new File(new StringBuilder().append("src/fiuba/algo3/vista/recursos/audio/")
				.append(nombre).append(".mp3").toString());
		Media media = new Media(f.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		
	}

	public void inicializarBotones() {
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
	}
	
	protected abstract void prepararMesa();

	protected abstract void setImageViewCartaHandlerYListener();

	protected abstract void mostrarCartas();

	protected void prepararJuego() {
		prepararMesa();
		mesa.repartir();
		setImageViewCartaHandlerYListener();
		mostrarCartas();
	}

	public static List<Mano> obtenerManosIntercaladas() {

		int posicionOtraMano = 0;
		List<Jugador> equipoUno = mesa.getEquipos().get(0).getJugadores();
		List<Jugador> equipoDos = mesa.getEquipos().get(1).getJugadores();
		List<Mano> manos = new ArrayList<>();
		for (Jugador jugador : equipoUno) {
			manos.add(jugador.getMano());
			manos.add(equipoDos.get(posicionOtraMano).getMano());
			posicionOtraMano++;
		}
		return manos;
	}	
}
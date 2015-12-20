package fiuba.algo3.vista.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.tipoJuego.JuegoTruco;
import fiuba.algo3.vista.controller.handler.BtnSalirHandler;
import fiuba.algo3.vista.controller.handler.CartaDeSeisHandler;
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
	
	private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	
	private MediaPlayer mediaPlayer;

	public static String armarRutaImagen(Carta carta) {
		String rutaImagen ="";
		try {
			rutaImagen = classLoader.getResource(new StringBuilder().append("gui/imagess/")
					.append(carta.getPalo().toString().toLowerCase()).append("/")
					.append(String.valueOf(carta.getTipoCarta().getValorRealCarta())).append(".png").toString())
			.toURI().toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return rutaImagen;
	}

	public void plasmarCartaEnImageView(Mano unaMano, List<ImageView> cartasAMostrar) {

		int posicionCarta = 0;
		for (Carta carta : unaMano.getCartas()) {
			Image pngCarta = new Image(armarRutaImagen(carta));
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
					if (juegoTruco.esPicaPica()) {

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
					if (juegoTruco.esPicaPica()) {

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
					if (juegoTruco.esPicaPica()) {

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
					if (juegoTruco.esPicaPica()) {
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
	
	protected void restablecerContenedores() {
		for (ImageView contenedor : contenedores) {
			contenedor.setImage(null);
		}
	}

	private void setBtnRealEnvidoHandler() {
		btnRealEnvido.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if (juegoTruco.esPicaPica()) {

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
					if (juegoTruco.esPicaPica()) {

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
					if (juegoTruco.esPicaPica()) {

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

	protected void ejecutarAudio(String nombre) {
		String rutaAudio ="";
		try {
			rutaAudio = classLoader.getResource(new StringBuilder().append("gui/sounds/").append(nombre)
					.append(".mp3").toString()).toURI().toString();
					
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Media media = new Media(rutaAudio);
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();

	}

	private void actualizarPuntos(JuegoTruco juegoTruco) {
		juegoTruco.sumarPuntos();
		lblPuntosEq1.setText(Integer.toString(juegoTruco.puntosEquipoUno()));
		lblPuntosEq2.setText(Integer.toString(juegoTruco.puntosEquipoDos()));
	}

	private void setBtnNoQuieroHandler() {
		btnNoQuiero.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					for (ImageView contenedor : contenedores ) {
						contenedor.setImage(null);
					}
					if (juegoTruco.esPicaPica()) {
						juegoTruco.getEnfrentamientoActual().noQuiero();
						juegoTruco.getEnfrentamientoActual().sumarPuntos();
						juegoTruco.terminarEnfrentamiento();
						actualizarPuntos(juegoTruco.getEnfrentamientoActual());
						((CartaDeSeisHandler) (cartasJugando.get(0).get(0).getOnMouseClicked()))
								.actualizarConPicaPica();
						ejecutarAudio("noQuiero");
					} else {
						juegoTruco.noQuiero();
						actualizarPuntos(juegoTruco);
						((CartaHandlerGeneral) (cartasJugando.get(0).get(0).getOnMouseClicked())).actualizar();
						ejecutarAudio("noQuiero");
					}
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
					if (juegoTruco.esPicaPica()) {

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
					if (juegoTruco.esPicaPica()) {

						juegoTruco.getEnfrentamientoActual().contraFlor();
					} else {
						juegoTruco.contraFlor();
					}
					ejecutarAudio("contraFlor");
				} catch (CantoInvalidoException exception) {
					popup("popupCantoProhibido");
				}
			}
		});
	}

	private void setBtnContraFlorAlRestoHandler() {
		btnContraFlorAlResto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (juegoTruco.esPicaPica()) {

						juegoTruco.getEnfrentamientoActual().contraFlorAlResto();
					} else {
						juegoTruco.contraFlorAlResto();
					}
					ejecutarAudio("contraFlorResto");
				} catch (CantoInvalidoException exception) {
					popup("popupCantoProhibido");
				}
			}
		});
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
		mostrarDorsos();
		mostrarCartas();
	}

	protected void mostrarDorso(ImageView carta) {
		
		Image imagenDorso=null;
		try {
			imagenDorso = new Image(classLoader.getResource("gui/imagess/dorso.jpg").toURI().toString());
			carta.setImage(imagenDorso);
			carta.setDisable(true);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private void mostrarDorsos() {
		for(List<ImageView> mano : cartasJugando) {
			for(ImageView carta : mano) {
				mostrarDorso(carta);
			}
		}
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
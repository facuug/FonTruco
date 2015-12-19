package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.IA;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.vista.controller.handler.CartaHandlerGeneral;
import fiuba.algo3.vista.controller.handler.CartaHandlerIA;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class MesaConIAController extends MesaGeneralController {

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
	private ImageView contenedor3;

	private IA jugadorIA;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		cartasJugando = new ArrayList<List<ImageView>>(
				Arrays.asList(new ArrayList<>(Arrays.asList(carta1Jug1, carta2Jug1, carta3Jug1)),
						new ArrayList<>(Arrays.asList(carta1Jug2, carta2Jug2, carta3Jug2)),
						new ArrayList<>(Arrays.asList(carta1Jug3, carta2Jug3, carta3Jug3)),
						new ArrayList<>(Arrays.asList(carta1Jug4, carta2Jug4, carta3Jug4))));
		inicializarBotones();
		jugadorIA = (IA) mesa.getEquipos().get(1).getJugadores().get(0);
		setBtnTrucoHandler();
		setBtnReTrucoHandler();
		setBtnValeCuatroHandler();
		setBtnEnvidoHandler();
		setBtnRealEnvidoHandler();
		setBtnFaltaEnvidoHandler();
		setBtnFlorHandler();
		setBtnContraFlorHandler();
		setBtnContraFlorAlRestoHandler();
		prepararJuego();

	}

	@Override
	protected void prepararMesa() {
		for (int i = 1; i < 3; i++) {
			List<ImageView> cartasNoUsadas = cartasJugando.get(i);
			for (ImageView cartaAEsconder : cartasNoUsadas) {
				cartaAEsconder.setVisible(false);
			}
			cartasJugando.remove(i);
		}
	}

	@Override
	protected void setImageViewCartaHandlerYListener() {
		Mano mano = obtenerManosIntercaladas().get(0);
		List<ImageView> cartasJugador = cartasJugando.get(0);
		for (int i = 0; i < 3; i++) {
			cartasJugador.get(i).setOnMouseClicked(new CartaHandlerIA(cartasJugando.get(0), cartasJugando,
					mano.getCartas().get(i), lblPuntosEq1, lblPuntosEq2));
			((CartaHandlerIA) (cartasJugador.get(i).getOnMouseClicked())).setContenedorEnMesa(contenedor1);
			((CartaHandlerIA) (cartasJugador.get(i).getOnMouseClicked())).setContenedorRival(contenedor3);
		}
	}

	@Override
	protected void mostrarCartas() {
		List<Equipo> equipos = mesa.getEquipos();
		List<ImageView> vistaCartas = cartasJugando.get(0);
		plasmarCartaEnImageView(equipos.get(0).getJugadores().get(0).getMano(), vistaCartas);
	}

	private void respuestaIA() {
		String respuesta = jugadorIA.responderCanto(juegoTruco);
			if (respuesta.equals("quiero")) {
				ejecutarAudio("quieroIA");
			} else {
				ejecutarAudio("noQuieroIA");
				Controller.juegoTruco.sumarPuntos();
				lblPuntosEq1.setText(Integer.toString(Controller.juegoTruco.puntosEquipoUno()));
				lblPuntosEq2.setText(Integer.toString(Controller.juegoTruco.puntosEquipoDos()));
				((CartaHandlerGeneral)(cartasJugando.get(0).get(0).getOnMouseClicked())).actualizar();
			}
			
	}

	private void setBtnTrucoHandler() {
		btnTruco.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					juegoTruco.truco();
					ejecutarAudio("truco");
					respuestaIA();
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
					juegoTruco.envido();
					ejecutarAudio("envido");
					respuestaIA();
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
					juegoTruco.reTruco();
					ejecutarAudio("reTruco");
					respuestaIA();
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
					juegoTruco.valeCuatro();
					ejecutarAudio("valeCuatro");
					respuestaIA();
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
					juegoTruco.realEnvido();
					ejecutarAudio("realEnvido");
					respuestaIA();
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
					juegoTruco.faltaEnvido();
					ejecutarAudio("faltaEnvido");
					respuestaIA();
				} catch (CantoInvalidoException exception) {
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
					juegoTruco.flor();
					ejecutarAudio("flor");
					respuestaIA();
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
					juegoTruco.contraFlor();
					ejecutarAudio("contraFlor");
					respuestaIA();
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
					juegoTruco.contraFlorAlResto();
					ejecutarAudio("contraFlorResto");
					respuestaIA();
				} catch (CantoInvalidoException exception) {
					popup("popupCantoProhibido");
				}
			}
		});
	}
}

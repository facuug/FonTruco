package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.vista.controller.handler.CartaHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MesaController extends MesaGeneralController {

	private static int cantidadJugadores = 0;

	public static int getCantidadJugadores() {
		return cantidadJugadores;
	}

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

	public static void setCantidadJugadores(int cantidad) {
		cantidadJugadores = cantidad;
	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		cartasJugando = new ArrayList<List<ImageView>>(
				Arrays.asList(new ArrayList<>(Arrays.asList(carta1Jug1, carta2Jug1, carta3Jug1)),
						new ArrayList<>(Arrays.asList(carta1Jug2, carta2Jug2, carta3Jug2)),
						new ArrayList<>(Arrays.asList(carta1Jug3, carta2Jug3, carta3Jug3)),
						new ArrayList<>(Arrays.asList(carta1Jug4, carta2Jug4, carta3Jug4))));
		inicializarBotones();
		contenedores = new ArrayList<ImageView>(Arrays.asList(contenedor1, contenedor2, contenedor3, contenedor4));

		prepararJuego();
	}

	@Override
	public void prepararMesa() {
		if (cantidadJugadores == 2) {
			esconderCartas();
		}
	}

	@Override
	protected void setImageViewCartaHandlerYListener() {
		List<Mano> manos = obtenerManosIntercaladas();
		int i = 0;
		for (List<ImageView> cartasEnMano : cartasJugando) {
			int j = 0;
			for (ImageView carta : cartasEnMano) {
				carta.setOnMouseClicked(new CartaHandler(cartasEnMano, cartasJugando, manos.get(i).getCartas().get(j),
						lblPuntosEq1, lblPuntosEq2));
				((CartaHandler) carta.getOnMouseClicked()).setContenedorEnMesa(contenedores.get(i));
				j++;
			}
			i++;
		}
		for (ImageView contenedor : contenedores) {
			contenedor.imageProperty().addListener(new ChangeListener<Image>() {

				@Override
				public void changed(ObservableValue<? extends Image> observable, Image oldValue, Image newValue) {
					if(juegoTruco.manoFinalizada()) {
						restablecerContenedores();
					}
				}

			});
		}
	}
	
	private void restablecerContenedores() {
		for (ImageView contenedor : contenedores) {
			contenedor.setImage(null);
		}
	}

	private void esconderCartas() {
		for (int i = 1; i < 3; i++) {
			List<ImageView> cartasNoUsadas = cartasJugando.get(i);
			for (ImageView cartaAEsconder : cartasNoUsadas) {
				cartaAEsconder.setVisible(false);
			}
			cartasJugando.remove(i);
			contenedores.remove(i);
		}
	}

	@Override
	protected void mostrarCartas() {
		List<Equipo> equipos = mesa.getEquipos();
		List<ImageView> vistaCartas = cartasJugando.get(0);
		plasmarCartaEnImageView(equipos.get(0).getJugadores().get(0).getMano(), vistaCartas);
	}
}
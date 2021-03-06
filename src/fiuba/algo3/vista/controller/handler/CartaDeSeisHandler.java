package fiuba.algo3.vista.controller.handler;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import fiuba.algo3.modelo.interfaces.JuegoTruco;
import fiuba.algo3.vista.controller.Controller;
import fiuba.algo3.vista.controller.MesaController;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaDeSeisHandler extends CartaHandlerGeneral {

	public CartaDeSeisHandler(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando,
			Carta cartaQueRepresenta, Label puntosEquipoUno, Label puntosEquipoDos) {
		super(cartasDeMano, cartasJugando, cartaQueRepresenta, puntosEquipoUno, puntosEquipoDos);
	}

	@Override
	public void handle(Event event) {
		try {
			JuegoTruco juegoTruco = Controller.juegoTruco;
			ImageView cartaJugada = (ImageView) event.getSource();
			cartaJugada.setVisible(false);
			jugarCarta(cartaJugada);
			if (juegoTruco.esPicaPica()) {
				juegoTruco.crearEnfrentamientosPicaPica();
				juegoTruco.getEnfrentamientoActual().jugadorDeTurnoJuegaCarta(this.cartaQueSoy);
				if (!juegoTruco.getEnfrentamientoActual().manoFinalizada()) {
					habilitarCartasPicaPica();
				} else {
					juegoTruco.terminarEnfrentamiento();
					
					if(juegoTruco.obtenerMesa().getJugadores().indexOf(juegoTruco.
							getEnfrentamientoActual().obtenerMesa().getJugadores().get(0)) == 0) {
						
					} else {
						habilitarSiguientePicaPica();
					}
				}
			} else {
				Controller.juegoTruco.jugadorDeTurnoJuegaCarta(this.cartaQueSoy);
				habilitarCartas();
				actualizar();
			}

		} catch (AccionInvalidaException exception) {
			System.out.println("No se puede jugar carta - CartaDeSeisHandler"); // esto es temporal
		}
	}
	
	private void restaurarMesa() {
		
	}

	private void habilitarSiguientePicaPica() {
		JuegoTruco juegoTruco = Controller.juegoTruco;
		Jugador jugador = juegoTruco.getEnfrentamientoActual().jugadorDeTurno();
		int posicionJugador = juegoTruco.obtenerMesa().getJugadores().indexOf(jugador);
		List<ImageView> cartas = cartasEnJuego.get(posicionJugador);
		for(ImageView carta : cartas) {
			mostrarCarta(carta);
		}
	}

	private void habilitarCartasPicaPica() {
		int posicionJugador = Controller.juegoTruco.obtenerMesa().getJugadores()
				.indexOf(Controller.juegoTruco.getEnfrentamientoActual().jugadorDeTurno());

		for (ImageView carta : cartasDeMano) {
			mostrarDorso(carta);
		}

		List<ImageView> manoAHabilitar = cartasEnJuego.get(posicionJugador);

		for (ImageView carta : manoAHabilitar) {
			mostrarCarta(carta);
		}

	}

	private void habilitarCartas() {
		List<ImageView> cartasAHabilitar = new ArrayList<ImageView>();
		cartasAHabilitar.addAll(cartasDeMano);

		int posicionDeManoSiguiente = MesaController.obtenerManosIntercaladas()
				.indexOf(Controller.juegoTruco.jugadorDeTurno().getMano());
		List<ImageView> cartasSiguientes = this.cartasEnJuego.get(posicionDeManoSiguiente);

		cartasAHabilitar.addAll(cartasSiguientes);
		for (ImageView carta : cartasAHabilitar) {
			if (!carta.isDisable()) {
				mostrarDorso(carta);
			} else {
				mostrarCarta(carta);
				carta.setDisable(false);
			}

		}
	}

	private void jugarCarta(ImageView cartaJugada) {
		Image imagenCarta = cartaJugada.getImage();
		contenedorAsociado.setImage(imagenCarta);
	}
}
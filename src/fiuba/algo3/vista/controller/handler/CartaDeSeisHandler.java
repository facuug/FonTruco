package fiuba.algo3.vista.controller.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import fiuba.algo3.vista.controller.Controller;
import fiuba.algo3.vista.controller.MesaController;
import fiuba.algo3.vista.controller.MesaGeneralController;
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
			ImageView cartaJugada = (ImageView) event.getSource();
			cartaJugada.setVisible(false);
			jugarCarta(cartaJugada);
			if (Controller.juegoTruco.esPicaPica()) {
				Controller.juegoTruco.crearEnfrentamientosPicaPica();
				Controller.juegoTruco.getEnfrentamientoActual().jugadorDeTurnoJuegaCarta(this.cartaQueSoy);
				habilitarCartasPicaPica();
				actualizarPicaPica();
			} else {
				Controller.juegoTruco.jugadorDeTurnoJuegaCarta(this.cartaQueSoy);
				habilitarCartas();
				actualizar();
			}

		} catch (AccionInvalidaException exception) {
			System.out.println("no se puede jugar carta"); // esto es temporal
		}
	}

	private void actualizarPicaPica() {
		if(Controller.juegoTruco.getEnfrentamientoActual().manoFinalizada()) {
		} else {
			
		}
	}
	
	private void habilitarSiguientePicaPica() {
		
	}
	
	private void habilitarCartasPicaPica() {
		int posicionJugador =Controller.juegoTruco.obtenerMesa().getJugadores().indexOf( 
				Controller.juegoTruco.getEnfrentamientoActual().jugadorDeTurno());
		
		for(ImageView carta : cartasDeMano) {
			mostrarDorso(carta);
		}
		
		List<ImageView> manoAHabilitar = cartasEnJuego.get(posicionJugador);
		
		for(ImageView carta : manoAHabilitar) {
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

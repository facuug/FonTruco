package fiuba.algo3.vista.controller.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.IA;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import fiuba.algo3.vista.controller.Controller;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaHandlerIA extends CartaHandlerGeneral {

	private ImageView contenedorRival;
	private static int cartaRival;
	private List<Jugador> jugadores;

	
	public CartaHandlerIA(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando, Carta cartaQueRepresenta,
			Label lblEquipoUno, Label lblEquipoDos) {
		super(cartasDeMano, cartasJugando, cartaQueRepresenta,lblEquipoUno,lblEquipoDos);
		jugadores = new ArrayList<>();
		for (Equipo equipo : Controller.juegoTruco.obtenerMesa().getEquipos()) {
			jugadores.add(equipo.getJugadores().get(0));
		}
		cartaRival = 0;
	}

	@Override
	public void handle(Event event) {
		try {
			Controller.juegoTruco.jugadorDeTurnoJuegaCarta(this.cartaQueSoy);
			ImageView cartaJugada = (ImageView) event.getSource();
			cartaJugada.setVisible(false);
			jugarCarta(cartaJugada);
			actualizar();

		} catch (AccionInvalidaException exception) {
			System.out.println("no se puede jugar carta"); // esto es temporal
		}
	}

	private void jugarCarta(ImageView cartaJugada) {
		Image imagenCarta = cartaJugada.getImage();
		contenedorAsociado.setImage(imagenCarta);
		if (!Controller.juegoTruco.manoFinalizada()) {
			if (Controller.juegoTruco.jugadorDeTurno() instanceof IA) {
				IA jugadorIA = (IA) Controller.juegoTruco.jugadorDeTurno();
				jugarCartaIA(jugadorIA, cartaQueSoy);
				cartaRival++;

			}
		}
	}

	private void jugarCartaIA(IA jugadorIA, Carta cartaJugador) {
		cartasEnJuego.get(1).get(cartaRival).setVisible(false);
		Carta cartaJugadaPorIA = jugadorIA.jugar(cartaJugador);
		Controller.juegoTruco.jugadorDeTurnoJuegaCarta(cartaJugadaPorIA);
		mostrarCartaIA(contenedorRival, cartaJugadaPorIA);

	}

	protected void mostrarCartaIA(ImageView contenedor, Carta carta) {
		File fileDorso = new File(armarRutaImagen(carta));
		Image imagenCarta = new Image(fileDorso.toURI().toString());
		contenedor.setImage(imagenCarta);
	}

	public void actualizar() {
		if (Controller.juegoTruco.manoFinalizada()) {
			for(Jugador jugador : jugadores) {
				if(jugador instanceof IA) {
					((IA)jugador).vaciar();
					break;
				}
			}
			Controller.juegoTruco.sumarPuntos();
			this.lblEquipoUno.setText(Integer.toString(Controller.juegoTruco.puntosEquipoUno()));
			this.lblEquipoDos.setText(Integer.toString(Controller.juegoTruco.puntosEquipoDos()));
			Controller.juegoTruco.restablecer();
			Mesa mesa = Controller.juegoTruco.obtenerMesa();
			List<Equipo> equipos = mesa.getEquipos();
			List<Jugador> jugadores = equipos.get(0).getJugadores();
			restaurarCartas(cartasEnJuego.get(0), jugadores.get(0).getMano());
			contenedorAsociado.setImage(null);
			contenedorRival.setImage(null);
			for (ImageView carta : cartasEnJuego.get(1)) {
				carta.setVisible(true);
			}
			cartaRival = 0;
		}  
		if(Controller.juegoTruco.hayGanador()) {
			Controller.popupGanador("PopUpGanador", Controller.juegoTruco.ganadorDeJuego());
		}
		if (Controller.juegoTruco.jugadorDeTurno() instanceof IA) {
			jugarCartaIA((IA) Controller.juegoTruco.jugadorDeTurno(), null);
		}
	}

	private void restaurarCartas(List<ImageView> cartasVista, Mano mano) {
		int i = 0;
		for (Carta carta : mano.getCartas()) {
			File fileCarta = new File(armarRutaImagen(carta));
			Image imagenCarta = new Image(fileCarta.toURI().toString());
			ImageView cartaVista = cartasVista.get(i);
			cartaVista.setImage(imagenCarta);
			cartaVista.setVisible(true);
			((CartaHandlerIA) cartaVista.getOnMouseClicked()).cartaQueSoy = carta;
			i++;
		}

	}

	public void setContenedorRival(ImageView contenedorRival) {
		this.contenedorRival = contenedorRival;
	}
}

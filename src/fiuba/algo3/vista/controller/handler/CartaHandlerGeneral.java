package fiuba.algo3.vista.controller.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.tipoJuego.JuegoTruco;
import fiuba.algo3.vista.controller.Controller;
import fiuba.algo3.vista.controller.MesaDeSeisController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaHandlerGeneral implements EventHandler<Event>{
	
	protected Carta cartaQueSoy;
	protected ImageView contenedorAsociado;
	protected List<ImageView> cartasDeMano;
	protected List<List<ImageView>> cartasEnJuego;
	protected Label lblEquipoUno,lblEquipoDos;
	
	
	
	protected String armarRutaImagen(Carta carta) {
		return new StringBuilder().append("src/fiuba/algo3/vista/recursos/carta/")
				.append(carta.getPalo().toString().toLowerCase())
				.append("/").append(String.valueOf(carta.getTipoCarta().getValorRealCarta()))
				.append(".png").toString();
	}
	
	public void setContenedorEnMesa(ImageView contenedor) {
		contenedorAsociado = contenedor;
	}
	
	protected void mostrarCarta(ImageView carta) {
		File file = new File(armarRutaImagen(((CartaHandlerGeneral)carta.getOnMouseClicked()).cartaQueSoy));
		Image imagen = new Image(file.toURI().toString());
		carta.setImage(imagen);
		carta.setDisable(false);
	}
	
	private void restaurarCartas() {
		Controller.juegoTruco.restablecer();
		Mesa mesa = Controller.juegoTruco.obtenerMesa();
		List<Equipo> equipos = mesa.getEquipos();
		int j = 0;
		for(int i = 0 ; i<equipos.size();i++) {
			List<Jugador> jugadores = equipos.get(i).getJugadores();
			j=i;
			for(Jugador jugador: jugadores) {
				restaurarCartas(cartasEnJuego.get(j), jugador.getMano());
				j+=2;
			}
		}
	}
	
	public void actualizar() {
		if(Controller.juegoTruco.manoFinalizada()){
			Controller.juegoTruco.sumarPuntos();
			this.lblEquipoUno.setText( Integer.toString(Controller.juegoTruco.puntosEquipoUno()) );
			this.lblEquipoDos.setText( Integer.toString(Controller.juegoTruco.puntosEquipoDos()) );
			restaurarCartas();
			if( Controller.juegoTruco.esPicaPica()) {
				mostrarCartasPicaPica();
			}else {
				mostrarCartasMano();
			}
		} else if(Controller.juegoTruco.hayGanador()) {
			Controller.popupGanador("PopUpGanador", Controller.juegoTruco.ganadorDeJuego());
		}
	}
	
	private void mostrarCartasPicaPica() {
		Controller.juegoTruco.crearEnfrentamientosPicaPica();
		JuegoTruco juegoPicaPica = Controller.juegoTruco.getEnfrentamientoActual();
		Jugador jugadorPicaPica = juegoPicaPica.jugadorDeTurno();
		int posicionJugadorPicaPica = MesaDeSeisController.obtenerManosIntercaladas().indexOf(jugadorPicaPica.getMano());
		//muestro cartas en la posicion 
		for(List<ImageView> cartas : cartasEnJuego) {
			if(cartasEnJuego.indexOf(cartas)==posicionJugadorPicaPica) {
				for(ImageView carta :cartas) {
					mostrarCarta(carta);
					carta.setDisable(false);
				}
			}
		}
	}
	
	protected void mostrarDorso(ImageView carta) {
		File fileDorso = new File("src/fiuba/algo3/vista/recursos/carta/CARTA_JUMBO_BICYCLE_52_EN_1_DORSO_AZUL_-_DORSO.jpg");
		Image imagenDorso = new Image(fileDorso.toURI().toString());
		carta.setImage(imagenDorso);
		carta.setDisable(true);
		
	}
	
	
	private void restaurarCartas(List<ImageView> cartasVista, Mano mano) {
		int i = 0;
		for(Carta carta : mano.getCartas()) {
			ImageView cartaVista = cartasVista.get(i);
			mostrarDorso(cartaVista);
			cartaVista.setVisible(true);
			
			((CartaHandlerGeneral)cartaVista.getOnMouseClicked()).cartaQueSoy=carta;
			i++;
		}
		
	}
	private void mostrarCartasMano() {
		Mesa mesa = Controller.juegoTruco.obtenerMesa();
		List<Equipo> equipos = mesa.getEquipos();
		List<Mano> manos = new ArrayList<Mano>();
		int cantidadDeJugadores = mesa.getEquipos().get(0).cantidadDeJugadores();
		for(int i = 0; i < cantidadDeJugadores; i++){
			manos.add(equipos.get(0).getJugadores().get(i).getMano());
			manos.add(equipos.get(1).getJugadores().get(i).getMano());
		}

		int posicion = manos.indexOf( Controller.juegoTruco.jugadorDeTurno().getMano() );
		List<ImageView> cartasHabilitar = cartasEnJuego.get(posicion);
		
		for(ImageView carta : cartasHabilitar) {
			File file = new File(armarRutaImagen(((CartaHandlerGeneral)carta.getOnMouseClicked()).cartaQueSoy));
			Image imagen = new Image(file.toURI().toString());
			carta.setImage(imagen);
			carta.setDisable(false);
		}
	}

	@Override
	public void handle(Event arg0) {
	}
	
	public CartaHandlerGeneral(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando, Carta cartaQueRepresenta, Label puntosEquipoUno, Label puntosEquipoDos) {
		this.cartasDeMano = cartasDeMano;
		int posicionSiguiente = cartasJugando.indexOf(cartasDeMano)+1;
		if(posicionSiguiente == cartasJugando.size()) {
			posicionSiguiente = 0;
		}
		cartaQueSoy= cartaQueRepresenta;
		this.cartasEnJuego = cartasJugando;
		
		this.lblEquipoUno = puntosEquipoUno;
		this.lblEquipoDos = puntosEquipoDos;
	}

}

package fiuba.algo3.vista.controller.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.vista.controller.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class CartaHandlerGeneral implements EventHandler<Event>{
	
	protected Carta cartaQueSoy;
	protected ImageView contenedorAsociado;
	protected List<ImageView> cartasDeMano;
	protected List<List<ImageView>> cartasEnJuego;
	
	protected Label labelEquipoUno,labelEquipoDos;
	
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
		File fileDorso = new File(armarRutaImagen(((CartaHandlerGeneral)carta.getOnMouseClicked()).cartaQueSoy));
		Image imagenDorso = new Image(fileDorso.toURI().toString());
		carta.setImage(imagenDorso);
	}
	
	public void actualizar() {
		if(Controller.juegoTruco.manoFinalizada()){
			Controller.juegoTruco.sumarPuntos();
			this.labelEquipoUno.setText( Integer.toString(Controller.juegoTruco.puntosEquipoUno()) );
			this.labelEquipoDos.setText( Integer.toString(Controller.juegoTruco.puntosEquipoDos()) );
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
			mostrarCartasMano();
		}
	}
	private void restaurarCartas(List<ImageView> cartasVista, Mano mano) {
		int i = 0;
		for(Carta carta : mano.getCartas()) {
			File fileDorso = new File("src/fiuba/algo3/vista/recursos/carta/CARTA_JUMBO_BICYCLE_52_EN_1_DORSO_AZUL_-_DORSO.jpg");
			Image imagenDorso = new Image(fileDorso.toURI().toString());
			ImageView cartaVista = cartasVista.get(i);
			cartaVista.setImage(imagenDorso);
			cartaVista.setVisible(true);
			cartaVista.setDisable(true);
			((CartaHandler)cartaVista.getOnMouseClicked()).cartaQueSoy=carta;
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
			File fileDorso = new File(armarRutaImagen(((CartaHandler)carta.getOnMouseClicked()).cartaQueSoy));
			Image imagenDorso = new Image(fileDorso.toURI().toString());
			carta.setImage(imagenDorso);
			carta.setDisable(false);
		}
	}
	

}

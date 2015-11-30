package fiuba.algo3.vista.controller.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import fiuba.algo3.vista.controller.Controller;
import fiuba.algo3.vista.controller.MenuPrincipalController;
import fiuba.algo3.vista.controller.MesaController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaHandler implements EventHandler<Event> {

	private ImageView contenedorAsociado;
	private List<ImageView> cartasDeMano;
	private Carta cartaQueSoy;
	List<List<ImageView>> cartasEnJuego;
	
	public CartaHandler(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando, Carta cartaQueRepresenta) {
		this.cartasDeMano = cartasDeMano;
		int posicionSiguiente = cartasJugando.indexOf(cartasDeMano)+1;
		if(posicionSiguiente == cartasJugando.size()) {
			posicionSiguiente = 0;
		}
		cartaQueSoy= cartaQueRepresenta;
		this.cartasEnJuego = cartasJugando;
	}
	
	public void setContendorEnMesa(ImageView contenedor) {
		contenedorAsociado = contenedor;
	}
	
	@Override
	public void handle(Event event) {
		try{
			Controller.getJuegoTruco().jugadorDeTurnoJuegaCarta(this.cartaQueSoy);
			ImageView cartaJugada = (ImageView)event.getSource();
			cartasDeMano.remove(cartaJugada);
			cartaJugada.setVisible(false);
			jugarCarta(cartaJugada);
			habilitarCartas();
		} catch(AccionInvalidaException exception){
			System.out.println("no se puede jugar carta"); //esto es temporal
		}
	}
	
	private void habilitarCartas() {
		List<ImageView> cartasAHabilitar = new ArrayList<ImageView>();
		cartasAHabilitar.addAll(cartasDeMano);
		
		int posicionDeManoSiguiente = MesaController.obtenerManosIntercaladas().indexOf(Controller.getJuegoTruco().jugadorDeTurno().getMano());
		List<ImageView> cartasSiguientes = this.cartasEnJuego.get(posicionDeManoSiguiente);

		cartasAHabilitar.addAll(cartasSiguientes);
		for(ImageView carta : cartasAHabilitar) {
			if(!carta.isDisable()) {
				File fileDorso = new File("src/fiuba/algo3/vista/recursos/carta/CARTA_JUMBO_BICYCLE_52_EN_1_DORSO_AZUL_-_DORSO.jpg");
				Image imagenDorso = new Image(fileDorso.toURI().toString());
				carta.setImage(imagenDorso);
			} else {
				File fileDorso = new File(armarRutaImagen(((CartaHandler)carta.getOnMouseClicked()).cartaQueSoy));
				Image imagenDorso = new Image(fileDorso.toURI().toString());
				carta.setImage(imagenDorso);
			}
			carta.setDisable(!carta.isDisable());
		}
	}
	
	private String armarRutaImagen(Carta carta) {
		return new StringBuilder().append("src/fiuba/algo3/vista/recursos/carta/")
				.append(carta.getPalo().toString().toLowerCase())
				.append("/").append(String.valueOf(carta.getTipoCarta().getValorRealCarta()))
				.append(".png").toString();
	}
	
	private void jugarCarta(ImageView cartaJugada) {	
		Image imagenCarta = cartaJugada.getImage();
		contenedorAsociado.setImage(imagenCarta);
	}	
}

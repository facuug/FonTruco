package fiuba.algo3.vista.controller.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mano;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaHandler implements EventHandler<Event> {

	private ImageView contenedorAsociado;
	private List<ImageView> cartasDeMano;
	private List<ImageView> cartasSiguientes;
	private Mano miMano;
	private Mano manoSiguiente;
	
	public CartaHandler(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando) {
		this.cartasDeMano = cartasDeMano;
		int posicionSiguiente = cartasJugando.indexOf(cartasDeMano)+1;
		if(posicionSiguiente == cartasJugando.size()) {
			posicionSiguiente = 0;
		}
		
		this.cartasSiguientes = cartasJugando.get(posicionSiguiente);
	}
	
	public CartaHandler(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando, Mano miMano, Mano manoSiguiente) {
		this.cartasDeMano = cartasDeMano;
		int posicionSiguiente = cartasJugando.indexOf(cartasDeMano)+1;
		if(posicionSiguiente == cartasJugando.size()) {
			posicionSiguiente = 0;
		}
		
		this.cartasSiguientes = cartasJugando.get(posicionSiguiente);
		this.miMano = miMano;
		this.manoSiguiente = manoSiguiente;
	}
	public void setContendorEnMesa(ImageView contenedor) {
		contenedorAsociado = contenedor;
	}
	
	@Override
	public void handle(Event event) {
		ImageView cartaJugada = (ImageView)event.getSource();
		cartasDeMano.remove(cartaJugada);
		cartaJugada.setVisible(false);
		jugarCarta(cartaJugada);
		habilitarCartas();
	}
	
	private void habilitarCartas() {
		List<ImageView> cartasAHabilitar = new ArrayList<>();
		cartasAHabilitar.addAll(cartasDeMano);
		cartasAHabilitar.addAll(cartasSiguientes);
		
		for(ImageView carta : cartasAHabilitar) {
			carta.setDisable(!carta.isDisable());
		}
	}
	
	private void jugarCarta(ImageView cartaJugada) {
		Image imagenCarta = cartaJugada.getImage();
		contenedorAsociado.setImage(imagenCarta);
	}
	
}

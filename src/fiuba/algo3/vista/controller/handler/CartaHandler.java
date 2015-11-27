package fiuba.algo3.vista.controller.handler;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaHandler implements EventHandler<Event> {

	private ImageView contenedorAsociado;
	private List<ImageView> cartasDeMano;
	private List<ImageView> cartasSiguientes;
		
	public CartaHandler(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando) {
		this.cartasDeMano = cartasDeMano;
		int posicionSiguiente = cartasJugando.indexOf(cartasDeMano)+1;
		if(posicionSiguiente == cartasJugando.size()) {
			posicionSiguiente = 0;
		}
		
		this.cartasSiguientes = cartasJugando.get(posicionSiguiente);
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
		List<ImageView> cartasAHabilitar = new ArrayList();
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

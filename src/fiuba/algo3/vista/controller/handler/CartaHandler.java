package fiuba.algo3.vista.controller.handler;

import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CartaHandler implements EventHandler<Event> {

	private List<ImageView> cartasEnMano;
	
	private List<ImageView> cartasTotales;
	
	private ImageView cartaSeleccionada;
	
	private static int posicionCartaJugador = 0;
	
	private ImageView contenedorEnMesa;
	
	public CartaHandler(ImageView cartaSeleccionada, List<ImageView> cartasEnMano) {
		this.cartaSeleccionada = cartaSeleccionada;
		this.cartasTotales = cartasEnMano;
		this.cartasEnMano = new ArrayList<ImageView>(cartasEnMano);
		this.cartasEnMano.remove(cartaSeleccionada);
	}
	
	public void setContendorEnMesa(ImageView contenedor) {
		contenedorEnMesa = contenedor;
	}
	
	@Override
	public void handle(Event event) {
		ImageView cartaJugada = (ImageView)event.getSource(); 
		cartaJugada.setVisible(false);
		cartasEnMano.remove(event.getSource());
		cartasTotales.remove(event.getSource());
	}
	
	
}

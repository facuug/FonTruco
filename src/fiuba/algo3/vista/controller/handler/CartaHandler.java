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
	
	private ImageView cartaSeleccionada;
	
	public CartaHandler(ImageView cartaSeleccionada, List<ImageView> cartasEnMano) {
		this.cartaSeleccionada = cartaSeleccionada;
		this.cartasEnMano = new ArrayList<ImageView>(cartasEnMano);
		this.cartasEnMano.remove(cartaSeleccionada);
	}
	
	@Override
	public void handle(Event event) {
		 cartaSeleccionada.setEffect(new DropShadow(20, Color.BLACK));
		 for(ImageView cartaEnMano : cartasEnMano) {
			 cartaEnMano.setEffect(null);
		 }
	}
}

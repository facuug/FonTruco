package fiuba.algo3.vista.controller.handler;

import java.util.List;

import fiuba.algo3.modelo.Carta;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CartaDeSeisHandler extends CartaHandlerGeneral {

	private List<ImageView> cartasEnfrentadas;
	
	
	public CartaDeSeisHandler(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando,
			Carta cartaQueRepresenta, Label puntosEquipoUno, Label puntosEquipoDos) {
		super(cartasDeMano, cartasJugando, cartaQueRepresenta, puntosEquipoUno, puntosEquipoDos);
		int i = 0;
		for(List<ImageView> mano : cartasJugando) {
			if(cartasJugando.indexOf(mano)==i) {
				if(i%2==0) {
					cartasEnfrentadas = cartasJugando.get(i+1);
				} else {
					cartasEnfrentadas = cartasJugando.get(i-1);	
				}
			}
			i++;
		}
	}
}

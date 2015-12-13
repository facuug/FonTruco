package fiuba.algo3.vista.controller.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import fiuba.algo3.vista.controller.Controller;
import fiuba.algo3.vista.controller.MesaController;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaHandler extends CartaHandlerGeneral {

	public CartaHandler(List<ImageView> cartasDeMano, List<List<ImageView>> cartasJugando, Carta cartaQueRepresenta, Label lblEquipoUno, Label lblEquipoDos) {
		super(cartasDeMano, cartasJugando, cartaQueRepresenta,lblEquipoUno,lblEquipoDos);
	}
	
	@Override
	public void handle(Event event) {
		try{
			Controller.juegoTruco.jugadorDeTurnoJuegaCarta(this.cartaQueSoy);
			ImageView cartaJugada = (ImageView)event.getSource();
			cartaJugada.setVisible(false);
			jugarCarta(cartaJugada);
			habilitarCartas();
			actualizar();
			
		} catch(AccionInvalidaException exception){
			System.out.println("no se puede jugar carta"); //esto es temporal 
		}
	}

	private void habilitarCartas() {
		List<ImageView> cartasAHabilitar = new ArrayList<ImageView>();
		cartasAHabilitar.addAll(cartasDeMano);
		
		int posicionDeManoSiguiente = MesaController.obtenerManosIntercaladas().indexOf(Controller.juegoTruco.jugadorDeTurno().getMano());
		List<ImageView> cartasSiguientes = this.cartasEnJuego.get(posicionDeManoSiguiente);

		cartasAHabilitar.addAll(cartasSiguientes);
		for(ImageView carta : cartasAHabilitar) {
			if(!carta.isDisable()) {
				File fileDorso = new File("src/fiuba/algo3/vista/recursos/carta/CARTA_JUMBO_BICYCLE_52_EN_1_DORSO_AZUL_-_DORSO.jpg");
				Image imagenDorso = new Image(fileDorso.toURI().toString());
				carta.setImage(imagenDorso);
			} else {
				mostrarCarta(carta);
			}
			carta.setDisable(!carta.isDisable());
		}
	}
	
	private void jugarCarta(ImageView cartaJugada) {	
		Image imagenCarta = cartaJugada.getImage();
		contenedorAsociado.setImage(imagenCarta);
	}	
}

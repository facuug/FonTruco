package fiuba.algo3.modelo.interfaces;

import java.io.File;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.vista.controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MesaGeneralController extends Controller{
	public String armarRutaImagen(Carta carta) {
		return new StringBuilder().append("src/fiuba/algo3/vista/recursos/carta/")
				.append(carta.getPalo().toString().toLowerCase())
				.append("/").append(String.valueOf(carta.getTipoCarta().getValorRealCarta()))
				.append(".png").toString();
	}	
	
	public void plasmarCartaEnImageView(Mano unaMano, List<ImageView> cartasAMostrar) {
		
		int posicionCarta = 0;
		for(Carta carta : unaMano.getCartas()) {
			String rutaImagen = armarRutaImagen(carta);
			File archivoCarta = new File(rutaImagen);
			Image pngCarta = new Image(archivoCarta.toURI().toString());
			cartasAMostrar.get(posicionCarta).setImage(pngCarta);
			posicionCarta+=1;
		}
	}
}

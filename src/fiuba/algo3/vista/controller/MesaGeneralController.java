package fiuba.algo3.vista.controller;

import java.io.File;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mano;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MesaGeneralController extends Controller{
	
	@FXML
	protected Button btnSalir;
	@FXML
    protected Button btnVolver;
	@FXML
    protected Button btnEnvido3;
	@FXML
    protected Button btnTruco;
	@FXML
    protected Button btnFlor;
	@FXML
    protected Button btnTruco1;
	@FXML
    protected Button btnTruco11;
	@FXML
    protected Button btnEnvido1;
	@FXML
    protected Button btnEnvido4;
	@FXML
    protected Button btnNoQuiero;
	@FXML
    protected Button btnQuiero;
	@FXML
    protected Button btnFlor1;
	@FXML
    protected Button btnFlor11;
	
	protected List<List<ImageView>> cartasJugando;
	protected List<ImageView> contenedores;
	
	
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

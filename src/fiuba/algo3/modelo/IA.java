package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.estados.Envido;
import fiuba.algo3.modelo.estados.EstadoJuego;
import fiuba.algo3.modelo.estados.FaltaEnvido;
import fiuba.algo3.modelo.estados.ReTruco;
import fiuba.algo3.modelo.estados.RealEnvido;
import fiuba.algo3.modelo.estados.Truco;
import fiuba.algo3.modelo.estados.ValeCuatro;
import fiuba.algo3.modelo.tipoJuego.JuegoTruco;

public class IA extends Jugador{

	private List<Carta> cartasPorJugar;
	
	public IA() {
		super("IA");
		cartasPorJugar = new ArrayList<>();
	}
	
	public Carta jugar(Carta cartaRival){
		if(cartasPorJugar.isEmpty()) {
			cartasPorJugar = new ArrayList<>(this.getMano().getCartas());
		}
		if(cartaRival== null) {
			return jugarPrimero();
		} else {
			return jugarSegundo(cartaRival);
		}
			
	}
	
	public void vaciar() {
		cartasPorJugar.removeAll(cartasPorJugar);
	}
	
	private Carta jugarPrimero() {
		return buscarMenorCarta();
	}
	
	private Carta jugarSegundo(Carta cartaRival) {
		return buscarMejorCartaMenorA(cartaRival);
	}
	
	private Carta buscarMejorCartaMenorA(Carta otraCarta) {
		Carta resultado = null;
		if(otraCarta == null) {
			 resultado = buscarMenorCarta();
		} else {
			resultado = buscarCartaQueGana(otraCarta);
		}
		return resultado;
	}
	
	private Carta buscarMenorCarta() {
		Carta cartaQueJuego=null;
		for(Carta unaCarta: cartasPorJugar) {
			if(cartaQueJuego==null || cartaQueJuego.comparar(unaCarta)>0) {
				cartaQueJuego = unaCarta;
			}
		}
		cartasPorJugar.remove(cartaQueJuego);
		return cartaQueJuego;
	}
		
	private Carta buscarCartaQueGana(Carta otraCarta) {
		Carta cartaQueJuego = null;
		for(Carta unaCarta : cartasPorJugar) {
			if(cartaQueJuego == null) {
				cartaQueJuego = unaCarta;
			} else if (cartaQueJuego.comparar(unaCarta)>0 && cartaQueJuego.comparar(otraCarta)>0) {
				cartaQueJuego = unaCarta;
			}
		}
		if(cartaQueJuego.comparar(otraCarta)>0){
			cartasPorJugar.remove(cartaQueJuego);
			return cartaQueJuego;
		}
		else
			return buscarMenorCarta();
	}

	public String responderCanto(JuegoTruco truco) {
		boolean querer = false;
		String quiero = "quiero";
		String noQuiero = "noQuiero";
		EstadoJuego canto = truco.getEstadoJuego();

		for(Carta carta:  this.getMano().getCartas() ){
			if( carta.getTipoCarta().getValor() >= 9 ){
				querer = true;
			}
		}

		if( (canto instanceof Truco) || (canto instanceof ReTruco) || (canto instanceof ValeCuatro)){
			if(querer){
				truco.quiero();
				return quiero;
			}
			else{
				truco.noQuiero();
				return noQuiero;
			}
		}

		if ( (canto instanceof Envido) || (canto  instanceof RealEnvido) ){
			if(this.puntosDeEnvido() >= 25) {
				truco.quiero();
				return quiero;
			}
			else{
				truco.noQuiero();
				return noQuiero;
			}
		}
		else if( canto instanceof FaltaEnvido ){
			if(this.puntosDeEnvido() >= 30){
				truco.quiero();
				return quiero;
			}
			else{
				truco.noQuiero();
				return noQuiero;
			}
		}
		return "";
	}
}

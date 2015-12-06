package fiuba.algo3.modelo;

import fiuba.algo3.modelo.estados.TrucoSinFlor;
import fiuba.algo3.modelo.interfaces.JuegoTruco;

import java.util.List;
import java.util.Random;

public class IA extends Jugador{

	public IA() {
		super("IA");
	}
	
	public Carta jugar(Carta cartaRival){
		if(cartaRival== null) {
			return jugarPrimero();
		} else {
			return jugarSegundo(cartaRival);
		}
			
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
		List<Carta> misCartas = getMano().getCartas();
		Carta cartaQueJuego=null;
		for(Carta unaCarta: misCartas) {
			if(cartaQueJuego==null || cartaQueJuego.comparar(unaCarta)>0) {
				cartaQueJuego = unaCarta;
			}
		}
		return getMano().sacarCarta(misCartas.indexOf(cartaQueJuego));
	}
		
	private Carta buscarCartaQueGana(Carta otraCarta) {
		Carta cartaQueJuego = null;
		List<Carta> misCartas = getMano().getCartas();
		for(Carta unaCarta : misCartas) {
			if(cartaQueJuego == null) {
				cartaQueJuego = unaCarta;
			} else if (cartaQueJuego.comparar(unaCarta)>0 && cartaQueJuego.comparar(otraCarta)>0) {
				cartaQueJuego = unaCarta;
			}
		}
		if(cartaQueJuego.comparar(otraCarta)>0)
			return cartaQueJuego;
		else
			return buscarMenorCarta();
	}

	public String responderCanto(String canto, JuegoTruco truco) {
		boolean querer = false;

		for(Carta carta:  this.getMano().getCartas() ){
			if( carta.getTipoCarta().getValor() >= 9 ){
				querer = true;
			}
		}

		if( (canto.equals("truco")) || (canto.equals("re truco")) || (canto.equals("vale cuatro")) ){
			if(querer){
				truco.quiero();
				return "Quiero!";
			}
			else{
				truco.noQuiero();
				return "No Quiero!";
			}
		}

		if ( (canto.equals("envido")) || (canto.equals("real envido")) ){
			if(this.puntosDeEnvido() >= 25) {
				truco.quiero();
				return "Quiero!";
			}
			else{
				truco.noQuiero();
				return "No Quiero!";
			}
		}
		else if( canto.equals("falta envido") ){
			if(this.puntosDeEnvido() >= 30){
				truco.quiero();
				return "Quiero!";
			}
			else{
				truco.noQuiero();
				return "No Quiero!";
			}
		}
		return "";
	}
}

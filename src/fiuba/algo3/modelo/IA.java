package fiuba.algo3.modelo;

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
}

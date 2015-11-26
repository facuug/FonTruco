package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.excepciones.NoHayMasCartasException;

public class Mazo {

	private List<Carta> mazoDeCartas;
	
	public Mazo() {
		
		mazoDeCartas = new ArrayList<Carta>();
		
		agregarCarta( new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA) );
		agregarCarta( new Carta(TipoCarta.ANCHO_BASTO, Palo.BASTO) );
		agregarCarta( new Carta(TipoCarta.SIETE_ESPADA, Palo.ESPADA) );
		agregarCarta( new Carta(TipoCarta.SIETE_ORO, Palo.ORO) );
		agregarCarta( new Carta(TipoCarta.FALSO_ANCHO, Palo.ORO) );
		agregarCarta( new Carta(TipoCarta.FALSO_ANCHO, Palo.COPA) );
		agregarCarta( new Carta(TipoCarta.FALSO_SIETE, Palo.BASTO) );
		agregarCarta( new Carta(TipoCarta.FALSO_SIETE, Palo.COPA) );
		
		for(Palo palo: Palo.values()){
			
			for(int i = 6; i < 14; i++) {

				agregarCarta( new Carta (TipoCarta.values()[i], palo) );
			}
		}
	}
	
	private void agregarCarta(Carta carta) {
		
		this.mazoDeCartas.add(carta);
	}
	
	
	
	
	void mezclar() {
		
		Collections.shuffle(mazoDeCartas);
	}

	public Carta repartirCarta() {
		
		if(this.cantidadDeCartas() == 0) throw new NoHayMasCartasException();
		
		return this.mazoDeCartas.remove(0);
	}
	
	public int cantidadDeCartas() {
		
		return mazoDeCartas.size();
	}
}
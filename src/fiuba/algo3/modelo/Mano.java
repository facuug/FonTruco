package fiuba.algo3.modelo;

import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.excepciones.NoHayFlorException;
import fiuba.algo3.modelo.excepciones.NoHayMasCartasException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mano {

	private List<Carta> cartas;

	public Mano() {
		
		this.cartas = new ArrayList<Carta>();
	}
	
	public void recibirCarta(Carta carta) {
		
		this.cartas.add(carta);
	}
	
	public Carta sacarCarta(int posicion) {
		if( this.cartas.size() == 0 ) throw new NoHayMasCartasException();

		if(posicion>cartas.size()) {
			posicion = cartas.size()-1;
		}
		return cartas.remove(posicion);
	}
	
	public int cantidadDeCartas() {
		
		return this.cartas.size();
	}

	public int puntosDeEnvido() {
		if(this.cantidadDeCartas() == 0) throw new NoHayMasCartasException();

		int i = 0, j = 0;
		Carta cartaActual;
		int puntajeEnvido = 0;

		while( i < this.cantidadDeCartas() ){
			cartaActual = this.cartas.get(i++);
			j = i;
			while( j < this.cantidadDeCartas() ){
				if( this.cartas.get(j).getPalo().equals(cartaActual.getPalo()) ) {
					if( this.cartas.get(j).valorDeEnvido() + cartaActual.valorDeEnvido() + 20 > puntajeEnvido ) {
						
						puntajeEnvido = this.cartas.get(j).valorDeEnvido() + cartaActual.valorDeEnvido() + 20;
					}
				}
				j++;
			}
		}

		return puntajeEnvido;
	}

	public int puntosDeFlor() {
		
		if(this.cantidadDeCartas() == 0) throw new NoHayMasCartasException();

		if( !(this.hayFlor()) ) throw new NoHayFlorException();
		
		int puntajeFlor = 20;
		Iterator<Carta> i = this.cartas.iterator();
		
		while ( i.hasNext() ) {
			
			puntajeFlor += i.next().valorDeEnvido();
		}
		
		return puntajeFlor;
	}
	
	private boolean hayFlor() {
		
		boolean cartasDelMismoPalo = true;
		
		Iterator<Carta> i = this.cartas.iterator();
		Palo paloCartaActual = i.next().getPalo();
		
		while( (i.hasNext()) && (cartasDelMismoPalo) ) {
			
			cartasDelMismoPalo = ( paloCartaActual == i.next().getPalo() );
		}
		
		return cartasDelMismoPalo;
	}
}
package fiuba.algo3.modelo;

import java.util.ArrayList;
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
		if(posicion>cartas.size()) {
			posicion = cartas.size()-1;
		}
		return cartas.remove(posicion);
	}
	
	public int cantidadDeCartas() {
		
		return this.cartas.size();
	}
}

package fiuba.algo3.modelo;

import java.util.List;

public class Mano {

	List<Carta> cartas;

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
	
	public Carta sacarCarta(int posicion) {
		if(posicion>cartas.size()) {
			posicion = cartas.size()-1;
		}
		return cartas.remove(posicion);
	}
}

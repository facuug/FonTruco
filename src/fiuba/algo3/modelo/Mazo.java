package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.excepciones.NoHayMasCartasException;

public class Mazo {

	private List<Carta> mazoDeCartas;
	
	public Mazo() {
		
		mazoDeCartas = new ArrayList<Carta>();
		
		for(Palo palo: Palo.values()){
			
			for(int numero = 1; numero < 8; numero++){
				
				mazoDeCartas.add(new Carta(numero,palo));
			}
			for(int numero = 10; numero < 13; numero++){
				
				mazoDeCartas.add(new Carta(numero,palo));
			}
		}
	}
	
	void mezclar() {
		
		Collections.shuffle(mazoDeCartas);
	}
	
	public int cantidadDeCartas() {
		
		return mazoDeCartas.size();
	}
	
	public Carta repartirCarta() {
		
		if(this.cantidadDeCartas() == 0) throw new NoHayMasCartasException();
		
		return this.mazoDeCartas.remove(0);
	}
	
	//	Esto hay que borrarlo, quiero ver si se crean
	//	todas las cartas, no se como probarlo si no.
	
	public void verCartas() {
		
		for (int i = 0; i < 40; i++) {
			
			System.out.println(mazoDeCartas.get(i).getNombre());
		}
	}
}
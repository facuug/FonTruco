package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fiuba.algo3.modelo.enums.Palo;

public class Mazo {

	private List<Carta> mazoDeCartas;
	
	public Mazo() {
		
		mazoDeCartas = new ArrayList<Carta>();
		
		for(Palo palo: Palo.values()){
			
			for(int numero = 1; numero < 8; numero++){
				
				mazoDeCartas.add(new Carta());
			}
			for(int numero = 10; numero < 13; numero++){
				
				mazoDeCartas.add(new Carta());
			}
		}
	}
	
	void mezclar() {
		
		Collections.shuffle(mazoDeCartas);
	}
}

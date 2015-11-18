package fiuba.algo3.modelo.enums;

import java.util.List;

public enum Ganador { EquipoUno, EquipoDos, Emparda;

	public static Ganador definir(List<Ganador> ganadores) {
		Ganador elGanadorDeLaRonda = Emparda; 
		for(Ganador ganador : ganadores) {
			if(elGanadorDeLaRonda.equals(Emparda))
				elGanadorDeLaRonda = ganador;
			else if(!ganador.equals(elGanadorDeLaRonda))
				elGanadorDeLaRonda = Emparda;
			else break; 
		}
		return elGanadorDeLaRonda;
	}
}

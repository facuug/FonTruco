package fiuba.algo3.modelo.interfaces;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;

public interface Ronda {

	void jugarCarta(Equipo equipo, Carta carta);

	Resultado ganadorDeRonda();

	Resultado determinarGanadorDeMano();

	Ronda calcularRondaSiguiente();

	Ronda siguiente();
}

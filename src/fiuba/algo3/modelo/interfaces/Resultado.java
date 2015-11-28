package fiuba.algo3.modelo.interfaces;

import fiuba.algo3.modelo.Equipo;

public interface Resultado {

	Equipo verEquipo();

	Ronda calcularRondaSiguiente(Ronda rondaActual);

}

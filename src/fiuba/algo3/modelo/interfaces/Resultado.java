package fiuba.algo3.modelo.interfaces;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.interfaces.Ronda;

public interface Resultado {

	Equipo verEquipo();

	Ronda calcularRondaSiguiente(Ronda primeraRonda);

	String situacion();

	void sumarPuntos(int i);
}

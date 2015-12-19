package fiuba.algo3.modelo.resultado;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.rondas.Ronda;

public interface Resultado {

	Equipo verEquipo();

	Ronda calcularRondaSiguiente(Ronda primeraRonda);

	String situacion();

	void sumarPuntos(int i);
}

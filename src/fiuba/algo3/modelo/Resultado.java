package fiuba.algo3.modelo;

import fiuba.algo3.modelo.interfaces.Ronda;

public interface Resultado {

	Equipo verEquipo();

	Ronda calcularRondaSiguiente(Ronda primeraRonda);

}

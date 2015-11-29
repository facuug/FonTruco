package fiuba.algo3.modelo;

import fiuba.algo3.modelo.interfaces.Resultado;
import fiuba.algo3.modelo.interfaces.Ronda;

public class Emparda implements Resultado {

	@Override
	public Equipo verEquipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RondaFinal calcularRondaSiguiente(Ronda unaRonda) {
		return new RondaFinal(unaRonda);
	}

	@Override
	public String situacion() {
		return "empardada";
	}

	@Override
	public void sumarPuntos(int i) {

	}

}

package fiuba.algo3.modelo.resultado;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.rondas.Ronda;
import fiuba.algo3.modelo.rondas.RondaFinal;

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

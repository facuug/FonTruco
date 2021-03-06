package fiuba.algo3.modelo;

import fiuba.algo3.modelo.interfaces.Resultado;
import fiuba.algo3.modelo.interfaces.Ronda;

public class EquipoGanador implements Resultado {
	
	private Equipo equipo;

	public EquipoGanador(Equipo unEquipo) {
		this.equipo = unEquipo;
	}

	@Override
	public Equipo verEquipo() {
		return this.equipo;
	}

	@Override
	public Ronda calcularRondaSiguiente(Ronda unaRonda) {
		return unaRonda.siguiente();
	}

	@Override
	public String situacion() {
		return "hayGanador";
	}

	@Override
	public void sumarPuntos(int puntos) {
		this.equipo.sumarPuntos(puntos);
	}

}

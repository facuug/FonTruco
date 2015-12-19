package fiuba.algo3.modelo.rondas;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.resultado.Emparda;
import fiuba.algo3.modelo.resultado.EquipoGanador;
import fiuba.algo3.modelo.resultado.PrimeraEmparda;
import fiuba.algo3.modelo.resultado.Resultado;

public class PrimeraRonda extends Ronda {

	private int cantidadDeJugadores;

	public PrimeraRonda(int jugadores) {
		super();
		this.cantidadDeJugadores = jugadores;
	}

	@Override
	public Resultado ganadorDeRonda() {
		List<Carta> cartas = new ArrayList<Carta>(this.cartas.keySet());

		Carta cartaMayor = cartas.get(0);
		for(Carta carta: cartas){
			if( carta.comparar(cartaMayor) > 0){
				cartaMayor = carta;
			}
		}
		if(this.empardan()) return new PrimeraEmparda(this.cartas.get(cartas.get(0)));
		else return new EquipoGanador(this.cartas.get(cartaMayor));
	}

	@Override
	public Ronda rondaAnterior() {
		return this;
	}

	@Override
	public boolean manoFinalizada() {
		return false;
	}

	@Override
	public Resultado determinarGanadorDeMano() {
		return new Emparda();
	}

	public Ronda siguiente() {
		return new SegundaRonda(this);
	}

	public int cantidadDeJugadores() {
		return this.cantidadDeJugadores;
	}
}

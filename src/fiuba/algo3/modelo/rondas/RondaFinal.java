package fiuba.algo3.modelo.rondas;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.resultado.EquipoGanador;
import fiuba.algo3.modelo.resultado.Resultado;

public class RondaFinal extends Ronda {

	private Ronda rondaAnterior;

	public RondaFinal(Ronda unaRonda){
		this.rondaAnterior = unaRonda;
	}

	@Override
	public Resultado determinarGanadorDeMano() {
		return this.ganadorDeRonda();
	}

	@Override
	public Ronda siguiente() {
		return new PrimeraRonda(this.cantidadDeJugadores());
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
		if(this.empardan()) return this.rondaAnterior.rondaAnterior().ganadorDeRonda(); //ganador de la primera ronda
		else return new EquipoGanador(this.cartas.get(cartaMayor));
	}

	@Override
	public Ronda rondaAnterior() {
		return this.rondaAnterior;
	}

	@Override
	public boolean manoFinalizada() {
		return ( this.cartasJugadas() == this.cantidadDeJugadores() );
	}

	@Override
	public int cantidadDeJugadores() {
		return this.rondaAnterior.cantidadDeJugadores();
	}

}

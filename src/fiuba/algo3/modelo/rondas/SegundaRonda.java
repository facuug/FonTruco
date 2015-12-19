package fiuba.algo3.modelo.rondas;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.resultado.Emparda;
import fiuba.algo3.modelo.resultado.EquipoGanador;
import fiuba.algo3.modelo.resultado.Resultado;

public class SegundaRonda extends Ronda {

	private Ronda rondaAnterior;

	public SegundaRonda(PrimeraRonda primeraRonda) {
		super();
		this.rondaAnterior = primeraRonda;
	}

	@Override
	public Resultado determinarGanadorDeMano() {
		if(this.ganadorDeRonda().verEquipo().equals(this.rondaAnterior.ganadorDeRonda().verEquipo())) return this.rondaAnterior.ganadorDeRonda();
		else return new Emparda();
	}

	public Ronda siguiente() {
		return new RondaFinal(this);
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
		if(this.empardan()) return this.rondaAnterior.ganadorDeRonda();
		else return new EquipoGanador(this.cartas.get(cartaMayor));
	}

	@Override
	public Ronda rondaAnterior() {
		return this.rondaAnterior;
	}

	@Override
	public boolean manoFinalizada() {
		return ( (this.cartasJugadas() == this.cantidadDeJugadores()) && ( !this.determinarGanadorDeMano().situacion().equals("empardada") ) );
	}

	@Override
	public int cantidadDeJugadores() {
		return this.rondaAnterior.cantidadDeJugadores();
	}

}

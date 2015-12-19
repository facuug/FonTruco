package fiuba.algo3.modelo.rondas;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.resultado.Resultado;

public abstract class Ronda {

	protected LinkedHashMap<Carta,Equipo> cartas;

	public Ronda(){
		this.cartas = new LinkedHashMap<Carta,Equipo>();
	}

	public void jugarCarta(Equipo equipo, Carta carta){
		this.cartas.put(carta,equipo);
	}

	public Boolean empardan(){
		Carta cartaActual = new ArrayList<Carta>(this.cartas.keySet()).get(0);
		Equipo equipoActual = new ArrayList<Equipo>(this.cartas.values()).get(0);

		for(Map.Entry<Carta,Equipo> carta: this.cartas.entrySet()){
			if( (cartaActual.comparar(carta.getKey()) != 0) && (!carta.getValue().equals(equipoActual)) ) return false;
		}
		return true;
	}

	public Ronda calcularRondaSiguiente() {
		if(this.cartasJugadas() < this.cantidadDeJugadores()) return this;

		return this.ganadorDeRonda().calcularRondaSiguiente(this);
	}

	protected int cartasJugadas() {
		return this.cartas.keySet().size();
	}

	public abstract Resultado determinarGanadorDeMano();

	public abstract Ronda siguiente();

	public abstract Resultado ganadorDeRonda();

	public abstract Ronda rondaAnterior();

	public abstract boolean manoFinalizada();

	public abstract int cantidadDeJugadores();
}

package fiuba.algo3.modelo;

import fiuba.algo3.modelo.excepciones.JugadorInexistenteException;
import fiuba.algo3.modelo.excepciones.NoHayJugadoresException;

import java.util.ArrayList;
import java.util.List;


public class Equipo {
	
	private List<Jugador> jugadores;
	private int puntos;
	private int posicion;

	public Equipo() {
		this.puntos = 0;
		this.posicion = 0;
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public List<Jugador> getJugadores() {
		return this.jugadores;
	}

	public int obtenerPuntos() {
		return this.puntos;
	}

	public void sumarPuntos(int puntos) {
		this.puntos += puntos;
	}

	public void agregarJugador(Jugador unJugador){
		this.jugadores.add(unJugador);
	}

	public Jugador jugadorDeTurno() {
		if(this.getJugadores().size() == 0) throw new NoHayJugadoresException();

		try{
			return this.getJugadores().get(this.posicion++);
		} catch (Exception exception){
			this.posicion = 0;
			return this.getJugadores().get(this.posicion++);
		}
	}


	public int puntosDeEnvido() {
		int mayorPuntaje = 0;

		for(Jugador jugador: this.jugadores){
			if ( jugador.puntosDeEnvido() > mayorPuntaje ) mayorPuntaje = jugador.puntosDeEnvido();
		}
		return mayorPuntaje;
	}

	public int puntosDeFlor() {
		int mayorPuntaje = 0;

		for(Jugador jugador: this.jugadores){
			if ( jugador.puntosDeFlor() > mayorPuntaje ) mayorPuntaje = jugador.puntosDeFlor();
		}
		return mayorPuntaje;
	}

	public int cantidadDeJugadores() {
		return this.jugadores.size();
	}

	public void establecerJugadorDeTurno(Jugador jugador) {
		if(!this.jugadores.contains(jugador)) throw new JugadorInexistenteException();

		this.posicion = this.jugadores.indexOf(jugador);
	}
}

package fiuba.algo3.modelo;

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
		try{
			return this.getJugadores().get(this.posicion++);
		} catch (Exception exception){
			this.posicion = 0;
			return this.getJugadores().get(this.posicion++);
		}
	}
}

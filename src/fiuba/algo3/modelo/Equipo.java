package fiuba.algo3.modelo;

import java.util.List;

public class Equipo {
	
	private List<Jugador> jugadores;
	private int puntos;

	public Equipo(List<Jugador> jugadores) {
		
		this.jugadores = jugadores;
		this.puntos = 0;
	}
	
	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}

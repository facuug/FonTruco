package fiuba.algo3.modelo;

import java.util.List;

public class Equipo {
	private List<Jugador> jugadores;
	private int puntos;

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Equipo(List<Jugador> jugadores, int puntos) {
		super();
		this.jugadores = jugadores;
		this.puntos = puntos;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

}

package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fiuba.algo3.modelo.interfaces.Resultado;
import fiuba.algo3.modelo.interfaces.Ronda;

public class Mesa {
	
	private Mazo mazo;
	private List<Equipo> equipos;
	private Equipo equipoMano;
	private Ronda ronda;

    public Mesa(Equipo unEquipo, Equipo otroEquipo) {
		this.equipoMano = unEquipo;
		mazo = new Mazo();
		this.equipos = Arrays.asList(unEquipo,otroEquipo);
		this.ronda = new PrimeraRonda(unEquipo.cantidadDeJugadores() * 2);
	}

    public void repartir() {
    	List<Jugador> jugadores = new ArrayList<Jugador>();
    	mazo.mezclar();
    	for(Equipo equipo : equipos) {
    		jugadores.addAll(equipo.getJugadores());
    	}
    	for(Jugador unJugador : jugadores) {
    		Mano unaMano = new Mano();
    		for(int i = 0 ; i<3 ; i++) {
    			unaMano.recibirCarta(mazo.repartirCarta());
    		}
    		unJugador.setMano(unaMano);
    	}
		this.rotarMano();
    }

	private void rotarMano() {
		int posicion = this.equipos.indexOf(this.equipoMano);

		try{
			this.equipoMano = this.equipos.get(posicion);
		} catch (Exception exception){
			this.equipoMano = this.equipos.get(0);
		}
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void jugarCarta(Equipo equipo, Carta unaCarta) {
		this.ronda = this.ronda.calcularRondaSiguiente();
		this.ronda.jugarCarta(equipo,unaCarta);
	}
	
	public Resultado determinarGanadorDeRonda() {
		return this.ronda.ganadorDeRonda();
	}

	public Equipo equipoMano() {
		return this.equipoMano;
	}

	public Resultado determinarGanadorDeMano() {
		return this.ronda.determinarGanadorDeMano();
	}

	public boolean manoFinalizada() {
		return this.ronda.manoFinalizada();
	}

	public void restablecer() {
		this.ronda = new PrimeraRonda(this.equipoMano.cantidadDeJugadores() * 2);
	}
}
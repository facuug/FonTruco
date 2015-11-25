package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.excepciones.MesaLlenaException;
import fiuba.algo3.modelo.interfaces.Ronda;

public class Mesa {
	
	private Mazo mazo;
    private LinkedHashMap<Jugador, List<Carta>> cartasJugadas;
    private Equipo equipoUno,equipoDos;
    private int cantidadDeJugadores;
	private Ronda ronda;

    public Mesa(Equipo unEquipo, Equipo otroEquipo) {
    	this.equipoUno = unEquipo;
    	this.equipoDos = otroEquipo;
    	this.ronda = new PrimeraRonda(this.equipoUno,this.equipoDos);
    	this.cantidadDeJugadores = unEquipo.cantidadDeJugadores() + otroEquipo.cantidadDeJugadores();
	}

	public void jugarCarta(Jugador jugador, Carta unaCarta) {
    	List<Carta> cartasJugador;
        if (this.mesaLlena()) throw new MesaLlenaException();
        if(cartasJugadas.containsKey(jugador)) {
        	cartasJugador = cartasJugadas.get(jugador);
        	cartasJugador.add(unaCarta);

        }
        else {
        	cartasJugador = new ArrayList<>();
        	cartasJugador.add(unaCarta);
        }
    	cartasJugadas.put(jugador, cartasJugador);
    }

    public boolean mesaLlena() {
        return ( cartasJugadas.size() == 3 * cantidadDeJugadores );
    }

	public Resultado determinarGanadorDeMano() {
		return this.ronda.determinarGanadorDeMano();
    }
	
	private Ganador comparar(List<Carta> cartasEquipoUno, List<Carta> cartasEquipoDos) {
		int puntosEquipoUno = 0;
		int puntosEquipoDos = 0;
		for(int i = 0; i<cartasEquipoUno.size() && puntosEquipoUno<2 ; i++){
			if(cartasEquipoUno.get(i).comparar(cartasEquipoDos.get(i))==1) {
				puntosEquipoUno++;
			} else if (cartasEquipoUno.get(i).comparar(cartasEquipoDos.get(i))==-1) {
				puntosEquipoDos++;
			}
		}
		
		if(puntosEquipoUno>puntosEquipoDos) return Ganador.EquipoUno;
		else if (puntosEquipoDos>puntosEquipoUno)return Ganador.EquipoDos;
		else return Ganador.Emparda;
	}
	
	private Ganador definirGanador(List<Ganador> ganadores) {
		return Ganador.definir(ganadores);
	}
	
    public void limpiar() {
        this.cartasJugadas.clear();
    }

    public int cartasEnMesa() {
        return this.cartasJugadas.size();
    }

	public void jugarCarta(Equipo equipo, Carta unaCarta) {
		this.ronda.jugarCarta(equipo,unaCarta);
		this.ronda = this.ronda.calcularRondaSiguiente();
	}
	
	public Resultado determinarGanadorDeRonda() {
		return this.ronda.ganadorDeRonda();
	}
}
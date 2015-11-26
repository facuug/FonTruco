package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.excepciones.MesaLlenaException;
import fiuba.algo3.modelo.interfaces.Ronda;

public class Mesa {
	
	private Mazo mazo;
    private LinkedHashMap<Jugador, List<Carta>> cartasJugadas;
    private Equipo equipoUno,equipoDos;
	private List<Equipo> equipos;
    private int cantidadDeJugadores;

	private Equipo equipoMano;
	private Ronda ronda;

    public Mesa(Equipo unEquipo, Equipo otroEquipo) {
    	this.equipoUno = unEquipo;
    	this.equipoDos = otroEquipo;
		this.equipoMano = unEquipo;

		this.equipos = Arrays.asList(unEquipo,otroEquipo);
		this.ronda = new PrimeraRonda(this.equipoUno,this.equipoDos);
    	this.cantidadDeJugadores = unEquipo.cantidadDeJugadores() + otroEquipo.cantidadDeJugadores();
		this.cartasJugadas = new LinkedHashMap<Jugador, List<Carta>>();
	}

	public void jugarCarta(Jugador jugador, Carta unaCarta) {
    	List<Carta> cartasJugador;
        if (this.mesaLlena()) throw new MesaLlenaException();
        if(cartasJugadas.containsKey(jugador)) {
        	cartasJugador = cartasJugadas.get(jugador);
        	cartasJugador.add(unaCarta);

        }
        else {
        	cartasJugador = new ArrayList<Carta>();
        	cartasJugador.add(unaCarta);
        }
    	cartasJugadas.put(jugador, cartasJugador);
    }

    public boolean mesaLlena() {
        return ( cartasJugadas.size() == 3 * cantidadDeJugadores );
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

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Ganador determinarGanadorDeMano() {
		List<List<Carta>> cartas = new ArrayList(cartasJugadas.values());
		List<Ganador> ganadores = new ArrayList<Ganador>();
		for(int j = 0; j<cartas.get(0).size();j++){
			for(int i = 0; i<cantidadDeJugadores/2 ; i+=2) {
				ganadores.add(comparar(cartas.get(i), cartas.get(i+1)));
			}
		}
		return definirGanador(ganadores);

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

	public Equipo equipoMano() {
		return this.equipoMano;
	}

	public Resultado ganadorDeMano() {
		return this.ronda.determinarGanadorDeMano();
	}
}
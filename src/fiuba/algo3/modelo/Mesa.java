package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.excepciones.MesaLlenaException;

public class Mesa {
	
	private Mazo mazo;
    private LinkedHashMap<Jugador, List<Carta>> cartasJugadas;
    private List<Equipo> equipos;
    private int cantidadDeJugadores;

    public Mesa(List<Equipo> equipos){
    	this.mazo = new Mazo();
        this.cartasJugadas = new LinkedHashMap<>();
        this.equipos = equipos;
        cantidadDeJugadores = equipos.size() * equipos.get(0).getJugadores().size();
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

    public void repartir() {
    	List<Jugador> jugadores = new ArrayList<>();
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
    }
    
    public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Ganador determinarGanadorDeMano() {
		List<List<Carta>> cartas = new ArrayList(cartasJugadas.values());
		List<Ganador> ganadores = new ArrayList<>();
		for(int j = 0; j<3;j++){
		for(int i = 0; i<cantidadDeJugadores/2 ; i+=2) {
			ganadores.add(comparar(cartas.get(i), cartas.get(i+1)));
		}}
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
		
		if(puntosEquipoUno>=2) return Ganador.EquipoUno;
		else if (puntosEquipoDos>=2)return Ganador.EquipoDos;
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
}
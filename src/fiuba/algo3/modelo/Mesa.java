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
		List<Object> listCartasJugadas = Arrays.asList(cartasJugadas.values().toArray());
		int vecesGanadasEquipoUno = 0;
		int vecesEmpatadas = 0;
		List<Carta> cartasJugadorUno = (List<Carta>)listCartasJugadas.get(0);
		List<Carta> cartasJugadorDos = (List<Carta>)listCartasJugadas.get(1);
		List<Carta> cartasJugadorTres = null;
		List<Carta> cartasJugadorCuatro = null;
		if(listCartasJugadas.size()>=3) {
			 cartasJugadorTres = (List<Carta>)listCartasJugadas.get(2);
		} if(listCartasJugadas.size()>=4){
			 cartasJugadorCuatro = (List<Carta>)listCartasJugadas.get(3);
		}
		for(int i = 0 ; i < 3 && vecesGanadasEquipoUno != 2; i++) {
			
			if(cartasJugadorTres != null && cartasJugadorCuatro != null) {
				if(compararDosCartasContraUna(cartasJugadorUno.get(i), cartasJugadorDos.get(i), cartasJugadorCuatro.get(i))==1
						|| compararDosCartasContraUna(cartasJugadorTres.get(i), cartasJugadorDos.get(i), cartasJugadorCuatro.get(i))==1) {
					vecesGanadasEquipoUno++;
				} else if (compararDosCartasContraUna(cartasJugadorUno.get(i), cartasJugadorDos.get(i), cartasJugadorCuatro.get(i))==0
						&& compararDosCartasContraUna(cartasJugadorTres.get(i), cartasJugadorDos.get(i), cartasJugadorCuatro.get(i))==0) {
					vecesEmpatadas++;
				}
			} else {
				if(compararDosCartasContraUna(cartasJugadorUno.get(i), cartasJugadorDos.get(i), null)==1) {
					vecesGanadasEquipoUno++;
				} else if (compararDosCartasContraUna(cartasJugadorUno.get(i), cartasJugadorDos.get(i), null)==0) {
					vecesEmpatadas++;
				}
			}
		}
		if(vecesEmpatadas==2) {
			return Ganador.EquipoUno;
		} else if(vecesEmpatadas == 3 || (vecesGanadasEquipoUno==1 && vecesEmpatadas == 1)) {
			return Ganador.Emparda;
		}
		return Ganador.EquipoDos;
    }
	
	/*
	 * Devuelve -1 si la primera pierde
	 * Devuelve 0 si las tres son iguales
	 * Devuelve 1 si la primera gana
	 */
	
	private int compararDosCartasContraUna(Carta unaCarta, Carta otraCarta, Carta otraCartaMas) {
		int resultadoPrimerComparacion = unaCarta.comparar(otraCarta);
		int resultadoSegundaComparacion = 0;
		if(otraCartaMas != null){
			resultadoSegundaComparacion = unaCarta.comparar(otraCartaMas);
		}
		 
		if(resultadoPrimerComparacion == 0 && resultadoSegundaComparacion == 0) {
			return 0;
		} else if (resultadoPrimerComparacion == -1 || resultadoSegundaComparacion == -1) {
			return -1;
		} else {
			return 1;
		}
		
	}
	
	
    public void limpiar() {
        this.cartasJugadas.clear();
    }

    public int cartasEnMesa() {
        return this.cartasJugadas.size();
    }
}
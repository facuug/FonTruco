package fiuba.algo3.modelo;


import fiuba.algo3.modelo.excepciones.JugadorInexistenteException;

import java.util.*;

/**
 * Created by Facundo on 20-Nov-15.
 */
public class CambiadorDeTurno {

    private List<Equipo> equipos;
    private Equipo equipoDeTurno;
    private int posicion,posicionDeJugadores;

    private HashMap<Jugador,Carta> cartasJugadas;
    private List<Jugador> jugadores;

    public CambiadorDeTurno(Equipo unEquipo, Equipo otroEquipo) {
        equipos = Arrays.asList(unEquipo,otroEquipo);
        this.equipoDeTurno = unEquipo;
        this.posicion = 1;
        this.posicionDeJugadores = 0;

        this.cartasJugadas = new HashMap<Jugador,Carta>();

        this.jugadores = new ArrayList<Jugador>();
        while( this.jugadores.size() < unEquipo.cantidadDeJugadores() * 2 ){
            this.jugadores.add( unEquipo.jugadorDeTurno() );
            this.jugadores.add( otroEquipo.jugadorDeTurno() );
        }
    }

    public void rotarEquipoDeTurno() {
        try{
            this.equipoDeTurno = this.equipos.get(this.posicion++);
        } catch (Exception exception){
            this.posicion = 0;
            this.rotarEquipoDeTurno();
        }
    }

    public Equipo equipoDeTurno() {
        return this.equipoDeTurno;
    }

    public void jugadorJuegaCarta(Jugador jugador, Carta carta) {
        this.cartasJugadas.put(jugador,carta);
    }

    public Jugador calcularJugadorDeTurno(){
        try{
            return this.jugadores.get(this.posicionDeJugadores);
        } catch (IndexOutOfBoundsException exception){
            this.posicionDeJugadores = 0;
            this.reordenar( this.jugadorConCartaMayor() );
            return this.jugadores.get(this.posicionDeJugadores);
        }
    }

    public void rotarJugador(){
        this.posicionDeJugadores++;
    }

    private void reordenar(Jugador jugadorQueVaPrimero){
        List<Jugador> listaReOrdenada = new ArrayList<Jugador>();

        for( int i = this.jugadores.indexOf(jugadorQueVaPrimero); listaReOrdenada.size() < this.jugadores.size(); i++){
            try{
                listaReOrdenada.add( this.jugadores.get(i) );
            } catch( IndexOutOfBoundsException exception){
                i = 0;
                listaReOrdenada.add( this.jugadores.get(i) );
            }
        }
        this.jugadores = listaReOrdenada;
    }

    private Jugador jugadorConCartaMayor(){
        Jugador duenioDeCartaMayor = this.jugadores.get(0);
        Carta cartaMayor = this.cartasJugadas.get(duenioDeCartaMayor);

        for(Map.Entry<Jugador,Carta> cartaJugada: this.cartasJugadas.entrySet()){
            if( cartaJugada.getValue().comparar(cartaMayor) > 0 ){	//si es mayor
                cartaMayor = cartaJugada.getValue();
                duenioDeCartaMayor = cartaJugada.getKey();
            }
        }
        return duenioDeCartaMayor;
    }

    public void establecerJugadorDeTurno(Jugador jugador) {
        if( !this.jugadores.contains(jugador) ) throw new JugadorInexistenteException();

        this.posicionDeJugadores = 0;
        reordenar(jugador);
    }
}

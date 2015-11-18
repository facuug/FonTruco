package fiuba.algo3.modelo;

import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.estados.*;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

import java.util.*;

/**
 * Created by Facundo on 11-Nov-15.
 */
public class JuegoTruco {

    private EstadoJuego estadoDeJuego;
    private Boolean trucoCantado,reTrucoCantado,hayFlor;

    private Equipo equipoUno;
    private Equipo equipoDos;

    private int puntosEnvido,puntosTruco;

    private Mesa mesa;
    private Jugador jugadorDeTurno;
    private List<Jugador> jugadores;

    public JuegoTruco(List<Jugador> jugadores, Boolean hayFlor){
        
        this.estadoDeJuego = new EstadoSinCanto();
        this.trucoCantado = false;
        this.reTrucoCantado = false;
        this.jugadores = new ArrayList<Jugador>();
        this.hayFlor = hayFlor;

        List<Jugador> jugadoresOtroEquipo = new ArrayList<>();
        for( int i = 0; i < jugadores.size()/2; i++){
            jugadoresOtroEquipo.add(jugadores.remove(i));
        }

        this.equipoUno = new Equipo(jugadoresOtroEquipo);
        this.equipoDos = new Equipo(jugadores);

        this.mesa = new Mesa(Arrays.asList(this.equipoUno,this.equipoDos));

        for( int i = 0; i < this.equipoUno.getJugadores().size(); i++){
            this.jugadores.add(this.equipoUno.getJugadores().get(i));
            this.jugadores.add(this.equipoDos.getJugadores().get(i));
        }

        this.jugadorDeTurno = this.jugadores.get(0);
    }

    public void envido() {
        estadoDeJuego.quiero(); // si hubo un envido previo, se debe aceptar
        estadoDeJuego = new Envido(this.cuantosPuntos());
    }

    public void noQuiero() {
        estadoDeJuego.noQuiero();

        if(this.trucoCantado) this.puntosTruco = this.cuantosPuntos();
        else this.puntosEnvido = this.cuantosPuntos();
    }

    public int cuantosPuntos() {
        return this.estadoDeJuego.cuantosPuntos();
    }

    public void quiero(){
        this.estadoDeJuego.quiero();

        if(this.trucoCantado) this.puntosTruco = this.cuantosPuntos();
        else this.puntosEnvido = this.cuantosPuntos();
    }

    public void realEnvido() {
        this.estadoDeJuego.quiero();
        estadoDeJuego = new RealEnvido(this.cuantosPuntos());
    }

    public void faltaEnvido() {
        this.estadoDeJuego.quiero();
        estadoDeJuego = new FaltaEnvido(this.cuantosPuntos());
    }

    public void truco() {
        this.estadoDeJuego = new Truco();
        this.trucoCantado = true;
    }

    public void reTruco(){
        if(!this.trucoCantado) throw new CantoInvalidoException();

        this.estadoDeJuego = new ReTruco();
        this.reTrucoCantado = true;
    }

    public void valeCuatro() {
        if(!this.reTrucoCantado) throw new CantoInvalidoException();

        this.estadoDeJuego = new ValeCuatro();
    }

    public int puntosEquipoUno() {
        return this.equipoUno.getPuntos();
    }

    public int puntosEquipoDos() {
        return this.equipoDos.getPuntos();
    }

    public void sumarPuntos() {
        if( this.puntosTruco == 0 ) this.puntosTruco = 1; // si no hubo cantos, el ganador de la mano recibe un punto

        if ( this.mesa.determinarGanadorDeMano().equals(Ganador.EquipoUno) ) this.equipoUno.setPuntos(this.puntosTruco);
        else  if ( this.mesa.determinarGanadorDeMano().equals(Ganador.EquipoDos) ) this.equipoDos.setPuntos(this.puntosTruco);
    }
    
    public void jugadorDeTurnoJuegaCarta(Carta carta) {
        mesa.jugarCarta(jugadorDeTurno,carta);
        this.rotarTurno();
    }

    private void rotarTurno() {
        int posicion = this.jugadores.indexOf(this.jugadorDeTurno) + 1;
        if( posicion == this.jugadores.size() ){    // si esta fuera de rango de la lista
            posicion = 0;
        }
        this.jugadorDeTurno = this.jugadores.get(posicion);
    }

    public void flor() {
        if(!this.hayFlor) throw new CantoInvalidoException();
    }
}

package fiuba.algo3.modelo.interfaces;

import fiuba.algo3.modelo.*;
import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.estados.EstadoSinCanto;

/**
 * Created by Facundo on 20-Nov-15.
 */
public abstract class JuegoTruco {

    protected EstadoJuego estadoDeJuego;

    protected Equipo equipoUno;
    protected Equipo equipoDos;

    protected int puntosEnvido, puntosDeTruco,puntosDeMano;

    protected Mesa mesa;

    protected Turno turnoParaCarta;
    protected Turno turnoParaCanto;

    protected Equipo equipoCantador;

    public void envido() {
        estadoDeJuego = estadoDeJuego.envido();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotar();
    }

    public void noQuiero() {
        estadoDeJuego.noQuiero();
        this.equipoCantador.sumarPuntos(this.estadoDeJuego.cuantosPuntos());
        this.estadoDeJuego = new EstadoSinCanto();
        this.puntosDeMano = 1;
    }

    public int cuantosPuntos() {
        return this.estadoDeJuego.cuantosPuntos();
    }

    public void quiero(){
        this.estadoDeJuego.quiero();
        this.puntosDeTruco = this.estadoDeJuego.cuantosPuntos();
        this.estadoDeJuego = new EstadoSinCanto();
    }

    public void realEnvido() {
        this.estadoDeJuego = this.estadoDeJuego.realEnvido();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotar();
    }

    public void faltaEnvido() {
        this.estadoDeJuego = this.estadoDeJuego.faltaEnvido();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotar();
    }

    public void truco() {
        this.estadoDeJuego = this.estadoDeJuego.truco();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotar();
        this.puntosDeMano = 0;
    }

    public void reTruco(){
        this.estadoDeJuego = this.estadoDeJuego.reTruco();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotar();
    }

    public void valeCuatro() {
        this.estadoDeJuego = this.estadoDeJuego.valeCuatro();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotar();
    }

    public int puntosEquipoUno() {
        return this.equipoUno.obtenerPuntos();
    }

    public int puntosEquipoDos() {
        return this.equipoDos.obtenerPuntos();
    }

    public void sumarPuntos() {

        Ganador ganador = mesa.determinarGanadorDeMano();

        if ( ganador.equals(Ganador.EquipoUno) ) this.equipoUno.sumarPuntos(puntosDeTruco + this.puntosDeMano);
        else  if ( ganador.equals(Ganador.EquipoDos) ) this.equipoDos.sumarPuntos(puntosDeTruco + this.puntosDeMano);
    }

    public void jugadorDeTurnoJuegaCarta(Carta carta) {
        mesa.jugarCarta(this.turnoParaCarta.equipoDeTurno().jugadorDeTurno(),carta);
        this.turnoParaCarta.rotar();
        this.turnoParaCanto.rotar();
    }

    public abstract void flor();
}

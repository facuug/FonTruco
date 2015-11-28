package fiuba.algo3.modelo.interfaces;

import fiuba.algo3.modelo.*;
import fiuba.algo3.modelo.estados.EstadoSinCanto;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;


/**
 * Created by Facundo on 20-Nov-15.
 */
public abstract class JuegoTruco {

    protected EstadoJuego estadoDeTruco,estadoDeEnvido;

    protected Equipo equipoUno;
    protected Equipo equipoDos;

    protected int puntosDeEnvido, puntosDeTruco,puntosDeMano;

    protected Mesa mesa;

    protected Equipo equipoDeTurno;
    protected CambiadorDeTurno turnoParaCanto;
    protected CambiadorDeTurno turnoParaCarta;

    protected Equipo equipoCantador;

    protected boolean finDeMano;

    protected Jugador ultimoJugadorDeCarta;

    public JuegoTruco(Equipo unEquipo, Equipo otroEquipo){
        this.puntosDeMano = 1;  //si no hay cantos la mano vale 1 punto

        this.equipoUno = unEquipo;
        this.equipoDos = otroEquipo;

        this.mesa = new Mesa(this.equipoUno,this.equipoDos);

        this.equipoDeTurno = this.equipoUno;

        this.estadoDeTruco = new EstadoSinCanto();
        this.estadoDeEnvido = new EstadoSinCanto();

        this.turnoParaCanto = new CambiadorDeTurno(this.equipoUno,this.equipoDos);
        this.turnoParaCarta = new CambiadorDeTurno(this.equipoUno,this.equipoDos);

        this.ultimoJugadorDeCarta = unEquipo.jugadorDeTurno();
        unEquipo.establecerJugadorDeTurno(this.ultimoJugadorDeCarta);
    }

    public void envido() {
        this.estadoDeEnvido = this.estadoDeEnvido.envido();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public void noQuiero() {
        this.estadoDeTruco.noQuiero();
        this.estadoDeEnvido.noQuiero();

        this.equipoCantador.sumarPuntos(this.estadoDeTruco.cuantosPuntos());
        this.equipoCantador.sumarPuntos(this.estadoDeEnvido.cuantosPuntos());

        this.estadoDeEnvido = new EstadoSinCanto();
        this.puntosDeMano = 1;

        this.finDeMano = this.estadoDeTruco.fueNoQuerido();
    }

    public int cuantosPuntos() {
        return this.estadoDeTruco.cuantosPuntos();
    }

    public void quiero(){
        this.estadoDeTruco.quiero();
        this.estadoDeEnvido.quiero();

        this.puntosDeTruco = this.estadoDeTruco.cuantosPuntos();
        this.puntosDeEnvido += this.estadoDeEnvido.cuantosPuntos();

        this.estadoDeEnvido = new EstadoSinCanto();
    }

    public void realEnvido() {
        this.estadoDeEnvido = this.estadoDeEnvido.realEnvido();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public void faltaEnvido() {
        this.estadoDeEnvido = this.estadoDeEnvido.faltaEnvido(this.determinarGanadorDeEnvido().obtenerPuntos());
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public void truco() {
        this.estadoDeTruco = this.estadoDeTruco.truco();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
        this.puntosDeMano = 0;
    }

    public void reTruco(){
        this.estadoDeTruco = this.estadoDeTruco.reTruco();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public void valeCuatro() {
        this.estadoDeTruco = this.estadoDeTruco.valeCuatro();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public int puntosEquipoUno() {
        return this.equipoUno.obtenerPuntos();
    }

    public int puntosEquipoDos() {
        return this.equipoDos.obtenerPuntos();
    }

    public void sumarPuntos() {
        this.determinarGanadorDeEnvido().sumarPuntos(this.puntosDeEnvido);
        this.determinarGanadorDeMano().sumarPuntos(this.puntosDeTruco + this.puntosDeMano);
    }

    public void jugadorDeTurnoJuegaCarta(Carta carta) {
        if( (!this.estadoDeEnvido.fueRespondido()) || (!this.estadoDeTruco.fueRespondido()) || (this.manoFinalizada()) ) throw new AccionInvalidaException();

        Jugador jugadorDeTurno = this.turnoParaCarta.calcularJugadorDeTurno();
        this.turnoParaCarta.jugadorJuegaCarta(jugadorDeTurno,carta);
        mesa.jugarCarta(jugadorDeTurno.miEquipo(),carta);

        if( this.ultimoJugadorDeCarta == jugadorDeTurno ) this.turnoParaCanto.rotarEquipoDeTurno();
        this.ultimoJugadorDeCarta = jugadorDeTurno;
    }

    public boolean manoFinalizada() {
        return ( this.mesa.manoFinalizada() || ( this.finDeMano ) );
    }

    private Equipo determinarGanadorDeMano(){
        return this.mesa.determinarGanadorDeMano().verEquipo();
    }

    private Equipo determinarGanadorDeEnvido(){
        if( this.equipoUno.puntosDeEnvido() > this.equipoDos.puntosDeEnvido() ) return this.equipoUno;
        else if( this.equipoUno.puntosDeEnvido() < this.equipoDos.puntosDeEnvido() ) return this.equipoDos;
            else return this.mesa.equipoMano();
    }

    public abstract void flor();
}

package fiuba.algo3.modelo.interfaces;

import fiuba.algo3.modelo.*;
import fiuba.algo3.modelo.estados.EstadoSinCanto;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;


/**
 * Created by Facundo on 20-Nov-15.
 */
public abstract class JuegoTruco {

    protected EstadoJuego estadoJuego;

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
    private Jugador jugadorDeTurno;

    public JuegoTruco(Equipo unEquipo, Equipo otroEquipo){
        this.puntosDeMano = 1;  //si no hay cantos la mano vale 1 punto

        this.equipoUno = unEquipo;
        this.equipoDos = otroEquipo;

        this.mesa = new Mesa(this.equipoUno,this.equipoDos);

        this.equipoDeTurno = this.equipoUno;

        this.estadoJuego = new EstadoSinCanto();

        this.turnoParaCanto = new CambiadorDeTurno(this.equipoUno,this.equipoDos);
        this.turnoParaCarta = new CambiadorDeTurno(this.equipoUno,this.equipoDos);

        this.ultimoJugadorDeCarta = unEquipo.jugadorDeTurno();
        unEquipo.establecerJugadorDeTurno(this.ultimoJugadorDeCarta);
    }

    public void envido() {
        this.estadoJuego = this.estadoJuego.envido();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public void noQuiero() {
        this.estadoJuego.noQuiero();
        this.equipoCantador.sumarPuntos(this.estadoJuego.cuantosPuntos());
        this.puntosDeMano = 1;
        this.finDeMano = this.estadoJuego.fueNoQuerido();
        this.estadoJuego = new EstadoSinCanto();
    }

    public int cuantosPuntos() {
        return this.estadoJuego.cuantosPuntos();
    }

    public void quiero(){
        this.estadoJuego.quiero();

        if( this.estadoJuego.esTruco() )this.puntosDeTruco = this.estadoJuego.cuantosPuntos();
        else{
            this.puntosDeEnvido += this.estadoJuego.cuantosPuntos();
            this.estadoJuego = new EstadoSinCanto();
        }
    }

    public void realEnvido() {
        this.estadoJuego = this.estadoJuego.realEnvido();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public void faltaEnvido() {
        this.estadoJuego = this.estadoJuego.faltaEnvido(this.determinarGanadorDeEnvido().obtenerPuntos());
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public void truco() {
        this.estadoJuego = this.estadoJuego.truco();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
        this.puntosDeMano = 0;
    }

    public void reTruco(){
        this.estadoJuego = this.estadoJuego.reTruco();
        this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    public void valeCuatro() {
        this.estadoJuego = this.estadoJuego.valeCuatro();
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
        if( (!this.estadoJuego.fueRespondido()) || (this.manoFinalizada()) ) throw new AccionInvalidaException();

        this.jugadorDeTurno = this.turnoParaCarta.calcularJugadorDeTurno();
        this.turnoParaCarta.rotarJugador();
        this.turnoParaCarta.jugadorJuegaCarta(jugadorDeTurno,carta);
        mesa.jugarCarta(jugadorDeTurno.miEquipo(),carta);

        if( this.ultimoJugadorDeCarta == jugadorDeTurno ) this.turnoParaCanto.rotarEquipoDeTurno();
        this.ultimoJugadorDeCarta = jugadorDeTurno;
    }

    public boolean manoFinalizada() {
        return ( this.mesa.manoFinalizada() || ( this.finDeMano ) );
    }

    protected Resultado determinarGanadorDeMano(){
        return this.mesa.determinarGanadorDeMano();
    }

    protected Equipo determinarGanadorDeEnvido(){
        if( this.equipoUno.puntosDeEnvido() > this.equipoDos.puntosDeEnvido() ) return this.equipoUno;
        else if( this.equipoUno.puntosDeEnvido() < this.equipoDos.puntosDeEnvido() ) return this.equipoDos;
            else return this.mesa.equipoMano();
    }

    public abstract void flor();

    public abstract void contraFlorAlResto();

    public abstract void contraFlor();

    public Jugador jugadorDeTurno(){
        return this.turnoParaCarta.calcularJugadorDeTurno();
    }

    public Mesa obtenerMesa(){
        return this.mesa;
    }

    public void restablecer(){
        this.estadoJuego = new EstadoSinCanto();
        this.puntosDeEnvido = 0;
        this.puntosDeTruco = 0;

        this.finDeMano = false;
        this.mesa.restablecer();
        this.turnoParaCarta.establecerJugadorDeTurno(this.mesa.equipoMano().jugadorDeTurno());
    }
}

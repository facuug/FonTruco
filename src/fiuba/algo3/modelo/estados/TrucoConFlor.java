package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.interfaces.EstadoJuego;
import fiuba.algo3.modelo.interfaces.JuegoTruco;

/**
 * Created by Facundo on 25-Nov-15.
 */
public class TrucoConFlor extends JuegoTruco {

    private EstadoJuego estadoFlor;
    private int puntosFlor = 0;

    public TrucoConFlor(Equipo unEquipo, Equipo otroEquipo) {
        super(unEquipo,otroEquipo);
        this.estadoFlor = new EstadoSinCanto();
    }

    @Override
    public void flor() {
        this.estadoFlor = this.estadoFlor.flor();
        this.determinarGanadorDeFlor().sumarPuntos(this.estadoFlor.cuantosPuntos());
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    @Override
    public void quiero(){
        this.estadoDeTruco.quiero();
        this.estadoDeEnvido.quiero();
        this.estadoFlor.quiero();

        this.puntosDeTruco = this.estadoDeTruco.cuantosPuntos();
        this.puntosDeEnvido += this.estadoDeEnvido.cuantosPuntos();
        this.puntosFlor = this.estadoFlor.cuantosPuntos();

        this.estadoDeEnvido = new EstadoSinCanto();
        this.estadoFlor = new EstadoSinCanto();
    }

    @Override
    public void sumarPuntos() {
        this.determinarGanadorDeEnvido().sumarPuntos(this.puntosDeEnvido);
        this.determinarGanadorDeMano().sumarPuntos(this.puntosDeTruco + this.puntosDeMano);
        this.determinarGanadorDeFlor().sumarPuntos(this.puntosFlor);
    }

    @Override
    public void contraFlorAlResto() {
        this.estadoFlor = this.estadoFlor.contraFlorAlResto(this.determinarGanadorDeFlor().obtenerPuntos());
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    private Equipo determinarGanadorDeFlor() {
        if(!this.turnoParaCanto.equipoDeTurno().tieneFlor()){
            this.turnoParaCanto.rotarEquipoDeTurno();
            return this.turnoParaCanto.equipoDeTurno();
        }
        if( this.equipoUno.puntosDeFlor() > this.equipoDos.puntosDeFlor() ) return this.equipoUno;
        else  return this.equipoDos;
    }
}

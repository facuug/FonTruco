package fiuba.algo3.modelo.tipoJuego;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.estados.EstadoJuego;
import fiuba.algo3.modelo.estados.EstadoSinCanto;

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
    public void quiero(){
    	this.estadoJuego.quiero();

        if( this.estadoJuego.esTruco() ) {
        	this.puntosDeTruco = this.estadoJuego.cuantosPuntos();
        }
        else if( this.estadoJuego.esEnvido() ) {
            this.puntosDeEnvido += this.estadoJuego.cuantosPuntos();
            this.estadoJuego = new EstadoSinCanto();
        	} else {
	            this.puntosFlor = this.estadoJuego.cuantosPuntos();
	            this.estadoJuego = new EstadoSinCanto();
        	}
    }

    @Override
    public void flor() {
        this.estadoJuego = this.estadoJuego.flor();
        this.determinarGanadorDeFlor().sumarPuntos(this.estadoJuego.cuantosPuntos());
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    @Override
    public void sumarPuntos() {
        this.determinarGanadorDeEnvido().sumarPuntos(this.puntosDeEnvido);
        this.determinarGanadorDeMano().sumarPuntos(this.puntosDeTruco + this.puntosDeMano);
        this.determinarGanadorDeFlor().sumarPuntos(this.puntosFlor);
    }

    @Override
    public void contraFlorAlResto() {
        this.estadoJuego = this.estadoJuego.contraFlorAlResto(this.determinarGanadorDeFlor().obtenerPuntos());
        this.turnoParaCanto.rotarEquipoDeTurno();
    }

    @Override
    public void contraFlor() {
        this.estadoFlor = this.estadoFlor.contraFlor();
        this.determinarGanadorDeFlor().sumarPuntos(this.estadoFlor.cuantosPuntos());
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
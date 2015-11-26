package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.interfaces.JuegoTruco;

/**
 * Created by Facundo on 25-Nov-15.
 */
public class TrucoConFlor extends JuegoTruco {

    public TrucoConFlor(Equipo unEquipo, Equipo otroEquipo) {
        super(unEquipo,otroEquipo);
    }

    @Override
    public void flor() {
        this.estadoDeEnvido = this.estadoDeEnvido.flor();
        this.determinarGanadorDeFlor().sumarPuntos(3);
    }

    private Equipo determinarGanadorDeFlor() {
        if( this.equipoUno.puntosDeFlor() > this.equipoDos.puntosDeFlor() ) return this.equipoUno;
        else return this.equipoDos;
    }
}

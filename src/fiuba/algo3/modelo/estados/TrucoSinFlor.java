package fiuba.algo3.modelo.estados;

import java.util.Arrays;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.Turno;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.interfaces.JuegoTruco;

/**
 * Created by Facundo on 11-Nov-15.
 */
public class TrucoSinFlor extends JuegoTruco{


    public TrucoSinFlor(Equipo unEquipo, Equipo otroEquipo){
        this.puntosDeMano = 1;  //si no hay cantos la mano vale 1 punto
        
        this.estadoDeJuego = new EstadoSinCanto();

        this.equipoUno = unEquipo;
        this.equipoDos = otroEquipo;

        this.mesa = new Mesa(Arrays.asList(this.equipoUno,this.equipoDos));

        this.turnoParaCanto = new Turno(this.equipoUno,this.equipoDos);
        this.turnoParaCarta = new Turno(this.equipoUno,this.equipoDos);
    }

    public void flor() {
        throw new CantoInvalidoException();
    }
}
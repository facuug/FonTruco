package fiuba.algo3.modelo;

import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.interfaces.*;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

import java.util.*;

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
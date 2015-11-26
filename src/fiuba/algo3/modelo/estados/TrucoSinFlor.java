package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.interfaces.JuegoTruco;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 11-Nov-15.
 */
public class TrucoSinFlor extends JuegoTruco{


    public TrucoSinFlor(Equipo unEquipo, Equipo otroEquipo){
        super(unEquipo,otroEquipo);
    }

    public void flor() {
        throw new CantoInvalidoException();
    }
}
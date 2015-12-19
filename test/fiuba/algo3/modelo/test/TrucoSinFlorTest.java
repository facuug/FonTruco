package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.tipoJuego.TrucoSinFlor;

import org.junit.Before;
import org.junit.Test;


/**
 * Created by Facundo on 14-Nov-15.
 */
public class TrucoSinFlorTest {

    private TrucoSinFlor trucoSinFlor;

    @Before
    public void setup(){

        Equipo equipoUno = new Equipo();
        equipoUno.agregarJugador(new Jugador("Facu"));
        equipoUno.agregarJugador(new Jugador("Agus"));

        Equipo equipoDos = new Equipo();
        equipoDos.agregarJugador(new Jugador("Homero"));
        equipoDos.agregarJugador(new Jugador("Lisa"));

        this.trucoSinFlor = new TrucoSinFlor(equipoUno,equipoDos);
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorLanzaExcepcion() {
        trucoSinFlor.flor();
    }
}


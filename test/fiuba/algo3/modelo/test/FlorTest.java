package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.estados.EstadoSinCanto;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.interfaces.EstadoJuego;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Facundo on 25-Nov-15.
 */
public class FlorTest {

    private EstadoJuego estadoJuego;

    @Before
    public void setup(){
        estadoJuego = new EstadoSinCanto();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorMasFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.flor();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorYDespuesEnvidoLanzaExcepcion(){
       estadoJuego = estadoJuego.flor();
       estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorMasFaltaEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.faltaEnvido(0);
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.valeCuatro();
    }
}

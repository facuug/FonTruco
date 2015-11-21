package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import fiuba.algo3.modelo.interfaces.EstadoJuego;
import fiuba.algo3.modelo.EstadoSinCanto;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class TrucoTest {

    private EstadoJuego estadoJuego;

    @Before
    public void setup(){
        estadoJuego = new EstadoSinCanto();
    }

    @Test
    public void trucoMasNoQuieroOtorgaUnPunto(){
        estadoJuego = estadoJuego.truco();
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 1 );
    }

    @Test
    public void trucoMasQuieroOtorgaDosPuntos(){
        estadoJuego = estadoJuego.truco();
        estadoJuego.quiero();
        assertEquals( estadoJuego.cuantosPuntos(), 2 );
    }

    @Test
    public void trucoMasReTrucoMasNoQuieroOtorgaDosPuntos(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 2 );
    }

    @Test
    public void trucoMasReTrucoMasQuieroOtorgaTresPuntos(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego.quiero();
        assertEquals( estadoJuego.cuantosPuntos(), 3 );
    }

    @Test
    public void trucoMasReTrucoMasValeCuatroMasNoQuieroOtorgaTresPuntos(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 3 );
    }

    @Test
    public void trucoMasReTrucoMasValeCuatroMasQuieroOtorgaCuatroPuntos(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego.quiero();
        assertEquals( estadoJuego.cuantosPuntos(), 4 );
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasFaltaEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.faltaEnvido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reRrucoSinTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasFaltaEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.faltaEnvido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroSinReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasFaltaEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.faltaEnvido();
    }

}
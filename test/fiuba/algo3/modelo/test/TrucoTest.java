package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import fiuba.algo3.modelo.estados.EstadoJuego;
import fiuba.algo3.modelo.estados.EstadoSinCanto;
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
        estadoJuego = estadoJuego.faltaEnvido(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.flor();
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
        estadoJuego = estadoJuego.faltaEnvido(0);
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
    public void reTrucoMasFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.flor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroSinReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasFaltaEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.faltaEnvido(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.flor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasContraFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasContraFlorAlRestoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasContraFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasContraFlorAlRestoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasContraFlorAlRestoLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasContraFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasQuieroMasNoQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego.quiero();
        estadoJuego.noQuiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void trucoMasNoQuieroMasQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego.noQuiero();
        estadoJuego.quiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasQuieroMasNoQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego.quiero();
        estadoJuego.noQuiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void reTrucoMasNoQuieroMasQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego.noQuiero();
        estadoJuego.quiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasQuieroMasNoQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego.quiero();
        estadoJuego.noQuiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void valeCuatroMasNoQuieroMasQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.truco();
        estadoJuego = estadoJuego.reTruco();
        estadoJuego = estadoJuego.valeCuatro();
        estadoJuego.noQuiero();
        estadoJuego.quiero();
    }
}
package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.estados.EstadoJuego;
import fiuba.algo3.modelo.estados.EstadoSinCanto;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Facundo on 25-Nov-15.
 */
public class FlorTest {

    private EstadoJuego estadoJuego;

    @Before
    public void setup(){
        estadoJuego = new EstadoSinCanto();
    }

    @Test
    public void cantarFlorMasFlorOtorgaCuatroAlGanadorYDosAlCantador(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.flor();
        assertEquals(6,estadoJuego.cuantosPuntos());
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarTresVecesFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
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
    public void cantarFlorMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarContraFlorSinFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorYLuegoNoQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego.noQuiero();
    }

    @Test
    public void cantarFlorMasContraFlorMasQuieroOtorgaSeisPuntos(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego.quiero();

        assertEquals(6,estadoJuego.cuantosPuntos());
    }

    @Test
    public void cantarFlorMasContraFlorMasNoQuieroOtorgaTresPuntos(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego.noQuiero();

        assertEquals(3,estadoJuego.cuantosPuntos());
    }

    @Test
    public void cantarFlorMasFlorMasContraFlorMasQuieroOtorgaNuevePuntos(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego.quiero();

        assertEquals(9,estadoJuego.cuantosPuntos());
    }

    @Test
    public void cantarFlorMasFlorMasContraFlorMasNoQuieroOtorgaSeisPuntos(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego.noQuiero();

        assertEquals(6,estadoJuego.cuantosPuntos());
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarContraFlorAlRestoSinFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.contraFlorAlResto(0);
    }

    @Test
    public void cantarFlorMasFlorMasContraFlorAlRestoMasNoQuieroOtorgaTresPuntos(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego.noQuiero();

        assertEquals(3,estadoJuego.cuantosPuntos());
    }

    @Test
    public void cantarFlorMasFlorMasContraFlorAlRestoMasNoQuieroOtorgaSeisPuntos(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego.noQuiero();

        assertEquals(6,estadoJuego.cuantosPuntos());
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasContraFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasFaltaEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego = estadoJuego.faltaEnvido(0);
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorAlRestoMasContraFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorAlRestoMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorAlRestoMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorAlRestoMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorAlRestoMasEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorAlRestoMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorAlRestoMasFaltaEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego = estadoJuego.faltaEnvido(0);
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasQuieroMasNoQuiero(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego.quiero();
        estadoJuego.noQuiero();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasNoQuieroMasQuiero(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlor();
        estadoJuego.noQuiero();
        estadoJuego.quiero();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorAlRestoMasQuieroMasNoQuiero(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego.quiero();
        estadoJuego.noQuiero();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void contraFlorMasAlRestoNoQuieroMasQuiero(){
        estadoJuego = estadoJuego.flor();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
        estadoJuego.noQuiero();
        estadoJuego.quiero();
    }
}

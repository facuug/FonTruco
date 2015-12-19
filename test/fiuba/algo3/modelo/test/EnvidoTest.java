package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import fiuba.algo3.modelo.estados.EstadoJuego;
import fiuba.algo3.modelo.estados.EstadoSinCanto;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Facundo on 11-Nov-15.
 */
public class EnvidoTest {

    private EstadoJuego estadoJuego;

    @Before
    public void setup(){
        this.estadoJuego = new EstadoSinCanto();
    }

   @Test
    public void envidoConNoquieroOtorgaUnPunto(){
       estadoJuego = estadoJuego.envido();
       estadoJuego.noQuiero();
       assertEquals( estadoJuego.cuantosPuntos(), 1 );
    }

    @Test
    public void envidoConQuieroOtorgaDosPuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego.quiero();
        assertEquals( estadoJuego.cuantosPuntos(), 2 );
    }

    @Test
    public void cantarEnvidoLuegoDeCantarEnvidoYLuegoQuieroOtorgaCuatroPuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.envido();
        estadoJuego.quiero();
        assertEquals( estadoJuego.cuantosPuntos(), 4 );
    }

    @Test
    public void cantarEnvidoLuegoDeCantarEnvidoYLuegoNoQuieroOtorgaDosPuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.envido();
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 2 );
    }

    @Test
    public void cantarEnvidoLuegoRealEnvidoLuegoQuieroOtorgaCincoPuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego.quiero();
        assertEquals( estadoJuego.cuantosPuntos(), 5 );
    }

    @Test
    public void cantarEnvidoLuegoRealEnvidoLuegoNoQuieroOtorgaDosPuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 2 );
    }

    @Test
    public void envidoMasEnvidoMasRealEnvidoMasQuieroDevuelveSietePuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego.quiero();
        assertEquals( estadoJuego.cuantosPuntos(), 7 );
    }

    @Test
    public void envidoMasEnvidoMasRealEnvidoMasNoQuieroDevuelveCuatroPuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 4 );
    }

    @Test
    public void CantarRealEnvidoMasQuieroOtorgaTresPuntos(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego.quiero();
        assertEquals( estadoJuego.cuantosPuntos(), 3 );
    }

    @Test
    public void CantarRealEnvidoMasNoQuieroOtorgaUnPunto(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 1 );
    }

    @Test
    public void cantarEnvidoLuegoFaltaEnvidoLuegoNoQuieroOtorgaUnPunto(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 2 );
    }

    @Test
    public void envidoMasEnvidoMasFaltaEnvidoMasNoQuieroOtorgaCuatroPuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 4 );
    }

    @Test
    public void faltaEnvidoMasNoQuieroOtorgaUnPunto(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 1 );
    }

    @Test
    public void envidoMasEnvidoMasRealEnvidoMasFaltaEnvidoMasNoQuieroOtorgaSietePuntos(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego.noQuiero();
        assertEquals( estadoJuego.cuantosPuntos(), 7 );
    }

    @Test ( expected = CantoInvalidoException.class )
    public void envidoMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void envidoMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void envidoMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void envidoMasFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.flor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.flor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.envido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasFaltaEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.faltaEnvido(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasRealEnvidoLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.truco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasReTrucoLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasValeCuatroLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.flor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void envidoMasContraFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void envidoMasContraFlorAlRestoLanzaExcepcion(){
        estadoJuego = estadoJuego.envido();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasContraFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasContraFlorAlRestoLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego = estadoJuego.contraFlorAlResto(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasContraFlorLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.contraFlor();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasContraFlorAlRestoLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego = estadoJuego.contraFlorAlResto(0);
    }

    @Test ( expected = CantoInvalidoException.class )
    public void envidoMasQuieroMasNoQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.envido();
        estadoJuego.quiero();
        estadoJuego.noQuiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void envidoMasNoQuieroMasQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.envido();
        estadoJuego.noQuiero();
        estadoJuego.quiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasQuieroMasNoQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego.quiero();
        estadoJuego.noQuiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void realEnvidoMasNoQuieroMasQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.realEnvido();
        estadoJuego.noQuiero();
        estadoJuego.quiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasQuieroMasNoQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego.quiero();
        estadoJuego.noQuiero();
    }

    @Test ( expected = CantoInvalidoException.class )
    public void faltaEnvidoMasNoQuieroMasQuieroLanzaExcepcion(){
        estadoJuego = estadoJuego.faltaEnvido(0);
        estadoJuego.noQuiero();
        estadoJuego.quiero();
    }
}

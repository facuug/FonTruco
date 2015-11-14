package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import fiuba.algo3.modelo.JuegoTruco;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Facundo on 11-Nov-15.
 */
public class EnvidoTest {

    private JuegoTruco juegoTruco;

    @Before
    public void setup(){
        this.juegoTruco = new JuegoTruco();
    }

   @Test
    public void envidoConNoquieroOtorgaUnPunto(){
       juegoTruco.envido();
       juegoTruco.noQuiero();
       assertEquals( juegoTruco.cuantosPuntos(), 1 );
    }

    @Test
    public void envidoConQuieroOtorgaDosPuntos(){
        juegoTruco.envido();
        juegoTruco.quiero();
        assertEquals( juegoTruco.cuantosPuntos(), 2 );
    }

    @Test
    public void cantarEnvidoLuegoDeCantarEnvidoYLuegoQuieroOtorgaCuatroPuntos(){
        juegoTruco.envido();
        juegoTruco.envido();
        juegoTruco.quiero();
        assertEquals( juegoTruco.cuantosPuntos(), 4 );
    }

    @Test
    public void cantarEnvidoLuegoDeCantarEnvidoYLuegoNoQuieroOtorgaDosPuntos(){
        juegoTruco.envido();
        juegoTruco.envido();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 2 );
    }

    @Test
    public void cantarEnvidoLuegoRealEnvidoLuegoQuieroOtorgaCincoPuntos(){
        juegoTruco.envido();
        juegoTruco.realEnvido();
        juegoTruco.quiero();
        assertEquals( juegoTruco.cuantosPuntos(), 5 );
    }

    @Test
    public void cantarEnvidoLuegoRealEnvidoLuegoNoQuieroOtorgaDosPuntos(){
        juegoTruco.envido();
        juegoTruco.realEnvido();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 2 );
    }

    @Test
    public void envidoMasEnvidoMasRealEnvidoMasQuieroDevuelveSietePuntos(){
        juegoTruco.envido();
        juegoTruco.envido();
        juegoTruco.realEnvido();
        juegoTruco.quiero();
        assertEquals( juegoTruco.cuantosPuntos(), 7 );
    }

    @Test
    public void envidoMasEnvidoMasRealEnvidoMasNoQuieroDevuelveCuatroPuntos(){
        juegoTruco.envido();
        juegoTruco.envido();
        juegoTruco.realEnvido();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 4 );
    }

    @Test
    public void CantarRealEnvidoMasQuieroOtorgaTresPuntos(){
        juegoTruco.realEnvido();
        juegoTruco.quiero();
        assertEquals( juegoTruco.cuantosPuntos(), 3 );
    }

    @Test
    public void CantarRealEnvidoMasNoQuieroOtorgaUnPunto(){
        juegoTruco.realEnvido();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 1 );
    }

    @Test
    public void cantarEnvidoLuegoFaltaEnvidoLuegoNoQuieroOtorgaUnPunto(){
        juegoTruco.envido();
        juegoTruco.faltaEnvido();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 2 );
    }

    @Test
    public void envidoMasEnvidoMasFaltaEnvidoMasNoQuieroOtorgaCuatroPuntos(){
        juegoTruco.envido();
        juegoTruco.envido();
        juegoTruco.faltaEnvido();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 4 );
    }

    @Test
    public void faltaEnvidoMasNoQuieroOtorgaUnPunto(){
        juegoTruco.faltaEnvido();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 1 );
    }

    @Test
    public void envidoMasEnvidoMasRealEnvidoMasFaltaEnvidoMasNoQuieroOtorgaSietePuntos(){
        juegoTruco.envido();
        juegoTruco.envido();
        juegoTruco.realEnvido();
        juegoTruco.faltaEnvido();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 7 );
    }
}

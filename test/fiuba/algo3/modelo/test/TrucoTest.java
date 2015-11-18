package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import fiuba.algo3.modelo.JuegoTruco;

import fiuba.algo3.modelo.Jugador;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class TrucoTest {

    private JuegoTruco juegoTruco;
    private Boolean sinFlor;
    private int dosJugadores = 2;

    @Before
    public void setup(){
        this.sinFlor = false;

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("facu"));
        jugadores.add(new Jugador("agus"));

        this.juegoTruco = new JuegoTruco(jugadores,sinFlor);
    }

    @Test
    public void trucoMasNoQuieroOtorgaUnPunto(){
        juegoTruco.truco();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 1 );
    }

    @Test
    public void trucoMasQuieroOtorgaDosPuntos(){
        juegoTruco.truco();
        juegoTruco.quiero();
        assertEquals( juegoTruco.cuantosPuntos(), 2 );
    }

    @Test
    public void trucoMasReTrucoMasNoQuieroOtorgaDosPuntos(){
        juegoTruco.truco();
        juegoTruco.reTruco();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 2 );
    }

    @Test
    public void trucoMasReTrucoMasQuieroOtorgaTresPuntos(){
        juegoTruco.truco();
        juegoTruco.reTruco();
        juegoTruco.quiero();
        assertEquals( juegoTruco.cuantosPuntos(), 3 );
    }

    @Test
    public void trucoMasReTrucoMasValeCuatroMasNoQuieroOtorgaTresPuntos(){
        juegoTruco.truco();
        juegoTruco.reTruco();
        juegoTruco.valeCuatro();
        juegoTruco.noQuiero();
        assertEquals( juegoTruco.cuantosPuntos(), 3 );
    }

    @Test
    public void trucoMasReTrucoMasValeCuatroMasQuieroOtorgaCuatroPuntos(){
        juegoTruco.truco();
        juegoTruco.reTruco();
        juegoTruco.valeCuatro();
        juegoTruco.quiero();
        assertEquals( juegoTruco.cuantosPuntos(), 4 );
    }
}
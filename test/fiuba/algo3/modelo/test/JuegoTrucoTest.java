package fiuba.algo3.modelo.test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.JuegoTruco;

import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Facundo on 14-Nov-15.
 */
public class JuegoTrucoTest {

    private JuegoTruco juegoTruco;
    private Boolean sinFlor;

    @Before
    public void setup(){
        this.sinFlor = false;

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("facu"));
        jugadores.add(new Jugador("agus"));
        jugadores.add(new Jugador("alguien"));
        jugadores.add(new Jugador("otroAlguien"));

        this.juegoTruco = new JuegoTruco(jugadores,sinFlor);
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorCuandoSeJuegaSinEllaLanzaExcepcion(){
        juegoTruco.flor();
    }

    @Test
    public void sumarPuntosSinTerminarManoNoSumaPuntos(){
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        juegoTruco.sumarPuntos();

        assertEquals( juegoTruco.puntosEquipoUno(), 0 );
        assertEquals( juegoTruco.puntosEquipoDos(), 0 );
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarReTrucoSinQueSeHallaCantadoTrucoLanzaExcepcion(){
        juegoTruco.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarValeCuatroSinQueSeHallaCantadoReTrucoLanzaExcepcion(){
        juegoTruco.truco();
        juegoTruco.valeCuatro();
    }
}


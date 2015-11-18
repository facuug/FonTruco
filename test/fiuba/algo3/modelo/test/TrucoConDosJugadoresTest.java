package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.JuegoTruco;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Facundo on 18-Nov-15.
 */
public class TrucoConDosJugadoresTest {

    private JuegoTruco juegoTruco;
    private Boolean sinFlor;

    @Before
    public void setup(){
        this.sinFlor = false;

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("facu"));
        jugadores.add(new Jugador("agus"));

        this.juegoTruco = new JuegoTruco(jugadores,sinFlor);
    }

    @Test
    public void juegoSinCantosOtorgaUnPuntoAlGanadorDeLaMano(){
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        juegoTruco.sumarPuntos();

        assertEquals( juegoTruco.puntosEquipoUno(), 1 );
        assertEquals( juegoTruco.puntosEquipoDos(), 0 );
    }

    @Test
    public void elTrucoLoGanaELGanadorDeLaMano(){
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        juegoTruco.truco();
        juegoTruco.reTruco();
        juegoTruco.quiero();

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        juegoTruco.sumarPuntos();

        assertEquals( juegoTruco.puntosEquipoUno(), 3 );
        assertEquals( juegoTruco.puntosEquipoDos(), 0 );
    }

    @Test
    public void juegoConEnvidoYTruco(){
        juegoTruco.envido();
        juegoTruco.quiero();

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        juegoTruco.truco();
        juegoTruco.reTruco();
        juegoTruco.valeCuatro();
        juegoTruco.quiero();

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));

        juegoTruco.sumarPuntos();

        assertEquals( juegoTruco.puntosEquipoUno(), 2 );
        assertEquals( juegoTruco.puntosEquipoDos(), 4 );
    }

    @Test
    public void juegoConRondaEmpardada(){
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        juegoTruco.truco();
        juegoTruco.noQuiero();

        juegoTruco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));

        juegoTruco.sumarPuntos();

        assertEquals( juegoTruco.puntosEquipoUno(), 2 );
        assertEquals( juegoTruco.puntosEquipoDos(), 0 );
    }


}

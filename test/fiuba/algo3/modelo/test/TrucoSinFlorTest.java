package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.TrucoSinFlor;

import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
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

    @Test
    public void sumarPuntosSinTerminarManoNoSumaPuntos(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.sumarPuntos();

        assertEquals( trucoSinFlor.puntosEquipoUno(), 0 );
        assertEquals( trucoSinFlor.puntosEquipoDos(), 0 );
    }

    @Test ( expected = CantoInvalidoException.class)
    public void cantarFlorLanzaExcepcion() {
        trucoSinFlor.flor();
    }
}


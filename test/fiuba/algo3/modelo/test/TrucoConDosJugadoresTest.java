package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.estados.TrucoSinFlor;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Facundo on 18-Nov-15.
 */
public class TrucoConDosJugadoresTest {

    private TrucoSinFlor trucoSinFlor;
    private Boolean sinFlor;
    
    private Equipo equipoUno;
    private Equipo equipoDos;

    @Before
    public void setup(){
        this.equipoUno = new Equipo();
        equipoUno.agregarJugador(new Jugador("Facu"));

        this.equipoDos = new Equipo();
        equipoDos.agregarJugador(new Jugador("Homero"));

        this.trucoSinFlor = new TrucoSinFlor(equipoUno,equipoDos);
    }

    @Test
    public void juegoSinCantosOtorgaUnPuntoAlGanadorDeLaMano(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.sumarPuntos();

        assertEquals( trucoSinFlor.puntosEquipoUno(), 1 );
        assertEquals( trucoSinFlor.puntosEquipoDos(), 0 );
    }

    @Test
    public void elTrucoLoGanaELGanadorDeLaMano(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.truco();
        trucoSinFlor.reTruco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        trucoSinFlor.sumarPuntos();

        assertEquals( trucoSinFlor.puntosEquipoUno(), 3 );
        assertEquals( trucoSinFlor.puntosEquipoDos(), 0 );
    }

    @Test
    public void juegoConEnvidoYTruco(){
        trucoSinFlor.envido();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        trucoSinFlor.truco();
        trucoSinFlor.reTruco();
        trucoSinFlor.valeCuatro();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));

        trucoSinFlor.sumarPuntos();

        
        assertEquals( 4, trucoSinFlor.puntosEquipoDos() );
        assertEquals( 2, trucoSinFlor.puntosEquipoUno());
        
    }

    @Test
    public void juegoConRondaEmpardada(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoSinFlor.truco();
        trucoSinFlor.noQuiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        trucoSinFlor.sumarPuntos();

        assertEquals(1, trucoSinFlor.puntosEquipoUno());
        assertEquals(1, trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void ganadorDeRondaNoSeLLevaPuntosDeTrucoSiDiceNoQuiero(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.truco();
        trucoSinFlor.noQuiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.ORO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));

        trucoSinFlor.sumarPuntos();

        assertEquals(1, trucoSinFlor.puntosEquipoUno());
        assertEquals(1, trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void ganadorDeRondaNoSeLLevaPuntosDeReTrucoSiDiceNoQuiero() {
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.truco();
        trucoSinFlor.reTruco();
        trucoSinFlor.noQuiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.ORO));

        trucoSinFlor.sumarPuntos();

        assertEquals(1, trucoSinFlor.puntosEquipoUno());
        assertEquals(2, trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void ganadorDeRondaNoSeLLevaPuntosDeValeTrucoSiDiceNoQuiero(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.truco();
        trucoSinFlor.reTruco();
        trucoSinFlor.valeCuatro();
        trucoSinFlor.noQuiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.ORO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

        trucoSinFlor.sumarPuntos();

        assertEquals(3, trucoSinFlor.puntosEquipoUno());
        assertEquals(1, trucoSinFlor.puntosEquipoDos());
    }
}

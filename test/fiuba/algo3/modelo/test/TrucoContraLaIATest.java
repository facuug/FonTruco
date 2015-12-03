package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.*;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.estados.TrucoSinFlor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Facundo on 03-Dec-15.
 */
public class TrucoContraLaIATest {

    private TrucoSinFlor trucoSinFlor;
    private Equipo equipoUno,equipoDos;
    private IA ia;

    @Before
    public void setup(){

        Jugador facu = new Jugador("Facu");
        facu.recibirCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        facu.recibirCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        facu.recibirCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        ia = new IA();
        ia.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        ia.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.BASTO));
        ia.recibirCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

        this.equipoUno = new Equipo();
        facu.asignarEquipo(equipoUno);
        equipoUno.agregarJugador(facu);

        this.equipoDos = new Equipo();
        ia.asignarEquipo(equipoDos);
        equipoDos.agregarJugador(ia);

        this.trucoSinFlor = new TrucoSinFlor(equipoUno,equipoDos);
    }

    @Test
    public void responderTrucoCuandoIATieneDeDoceParaArribaDevuelveQuiero(){
        trucoSinFlor.truco();
        ia.responderCanto("truco",trucoSinFlor);

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(ia.jugar(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA)));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(ia.jugar(new Carta(TipoCarta.SEIS,Palo.COPA)));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(ia.jugar(null));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.sumarPuntos();

        assertEquals(0,trucoSinFlor.puntosEquipoUno());
        assertEquals(2,trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void responderReTrucoCuandoIATieneDeDoceParaArribaDevuelveQuiero(){
        trucoSinFlor.truco();
        ia.responderCanto("truco",trucoSinFlor);

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(ia.jugar(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA)));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(ia.jugar(new Carta(TipoCarta.SEIS,Palo.COPA)));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(ia.jugar(null));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.sumarPuntos();

        assertEquals(0,trucoSinFlor.puntosEquipoUno());
        assertEquals(2,trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void responderTrucoCuandoIANoTieneAlmenosCartaDeDoceDevuelveNoQuiero(){
        IA ia = new IA();
        ia.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        ia.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.BASTO));
        ia.recibirCarta(new Carta(TipoCarta.CINCO, Palo.ORO));

        trucoSinFlor.truco();
        ia.responderCanto("truco",trucoSinFlor);

        trucoSinFlor.sumarPuntos();

        assertEquals(1,trucoSinFlor.puntosEquipoUno());
        assertEquals(0,trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void responderReTrucoCuandoIANoTieneAlmenosCartaDeDoceDevuelveNoQuiero(){
        IA ia = new IA();
        ia.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        ia.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.BASTO));
        ia.recibirCarta(new Carta(TipoCarta.CINCO, Palo.ORO));

        trucoSinFlor.truco();
        ia.responderCanto("re truco",trucoSinFlor);

        trucoSinFlor.sumarPuntos();

        assertEquals(1,trucoSinFlor.puntosEquipoUno());
        assertEquals(0,trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void responderEnvidoCuandoTieneAlMenosVeintiCincoDevuelveQuiero(){
        IA ia = new IA();
        ia.recibirCarta(new Carta(TipoCarta.SEIS, Palo.ESPADA));
        ia.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.BASTO));
        ia.recibirCarta(new Carta(TipoCarta.CINCO, Palo.ESPADA));

        this.equipoDos.agregarJugador(ia);

        trucoSinFlor.envido();
        ia.responderCanto("envido",trucoSinFlor);

        trucoSinFlor.sumarPuntos();

        assertEquals(0,trucoSinFlor.puntosEquipoUno());
        assertEquals(2,trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void responderRealEnvidoCuandoTieneAlMenosVeintiCincoDevuelveQuiero(){
        IA ia = new IA();
        ia.recibirCarta(new Carta(TipoCarta.SEIS, Palo.ESPADA));
        ia.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.BASTO));
        ia.recibirCarta(new Carta(TipoCarta.CINCO, Palo.ESPADA));

        this.equipoDos.agregarJugador(ia);

        trucoSinFlor.envido();
        ia.responderCanto("real envido",trucoSinFlor);

        trucoSinFlor.sumarPuntos();

        assertEquals(0,trucoSinFlor.puntosEquipoUno());
        assertEquals(2,trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void responderEnvidoCuantoNoTieneAlMenosVeinticincoPuntosDevuleveNoQuiero(){
        trucoSinFlor.envido();
        ia.responderCanto("envido",trucoSinFlor);

        trucoSinFlor.sumarPuntos();

        assertEquals(1,trucoSinFlor.puntosEquipoUno());
        assertEquals(0,trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void responderRealEnvidoCuantoNoTieneAlMenosVeinticincoPuntosDevuleveNoQuiero(){
        trucoSinFlor.envido();
        ia.responderCanto("real envido",trucoSinFlor);

        trucoSinFlor.sumarPuntos();

        assertEquals(1,trucoSinFlor.puntosEquipoUno());
        assertEquals(0,trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void responderAFaltaEnvidoConMasDeTreintaPuntosDevuelveQuiero(){
        IA ia = new IA();
        ia.recibirCarta(new Carta(TipoCarta.SEIS, Palo.ESPADA));
        ia.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.BASTO));
        ia.recibirCarta(new Carta(TipoCarta.SIETE_ESPADA, Palo.ESPADA));

        this.equipoDos.agregarJugador(ia);

        trucoSinFlor.faltaEnvido();
        ia.responderCanto("falta envido",trucoSinFlor);

        trucoSinFlor.sumarPuntos();

        assertEquals(0,trucoSinFlor.puntosEquipoUno());
        assertEquals(15,trucoSinFlor.puntosEquipoDos());
    }
}

package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.estados.TrucoConFlor;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.estados.TrucoSinFlor;
import fiuba.algo3.modelo.interfaces.JuegoTruco;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Facundo on 18-Nov-15.
 */
public class TrucoConCuatroJugadores {

    private JuegoTruco trucoConFlor;

    @Before
    public void setup(){

        Jugador facu = new Jugador("Facu");
        facu.recibirCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        facu.recibirCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        facu.recibirCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        Jugador agus = new Jugador("Agus");
        agus.recibirCarta(new Carta(TipoCarta.FALSO_ANCHO, Palo.COPA));
        agus.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.COPA));
        agus.recibirCarta(new Carta(TipoCarta.CINCO,Palo.COPA));

        Jugador homero = new Jugador("Homero");
        homero.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        homero.recibirCarta(new Carta(TipoCarta.REY, Palo.BASTO));
        homero.recibirCarta(new Carta(TipoCarta.SOTA, Palo.ORO));

        Jugador lisa = new Jugador("Lisa");
        lisa.recibirCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));
        lisa.recibirCarta(new Carta(TipoCarta.REY, Palo.ORO));
        lisa.recibirCarta(new Carta(TipoCarta.ANCHO_BASTO, Palo.BASTO));

        Equipo equipoUno = new Equipo();
        facu.asignarEquipo(equipoUno);
        agus.asignarEquipo(equipoUno);
        equipoUno.agregarJugador(facu);
        equipoUno.agregarJugador(agus);

        Equipo equipoDos = new Equipo();
        homero.asignarEquipo(equipoDos);
        lisa.asignarEquipo(equipoDos);
        equipoDos.agregarJugador(homero);
        equipoDos.agregarJugador(lisa);

        this.trucoConFlor = new TrucoConFlor(equipoUno,equipoDos);
    }

    @Test
    public void cantarFlorOtorgaTresPuntosAlPoseedor(){
        this.trucoConFlor.flor();

        assertEquals(3, this.trucoConFlor.puntosEquipoUno());
        assertEquals(0, this.trucoConFlor.puntosEquipoDos());
    }

    @Test
    public void juegoConEnvidoYTruco(){
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoConFlor.envido();
        trucoConFlor.envido();
        trucoConFlor.quiero();

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO,Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.ORO));

        trucoConFlor.truco();
        trucoConFlor.reTruco();
        trucoConFlor.quiero();

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.FALSO_ANCHO, Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_BASTO, Palo.BASTO));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.ORO));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CABALLO, Palo.COPA));

        trucoConFlor.sumarPuntos();

        assertEquals(0, this.trucoConFlor.puntosEquipoUno());
        assertEquals(7, this.trucoConFlor.puntosEquipoDos());
    }

    @Test
    public void juegoConCantosIntercalados(){
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoConFlor.truco();
        trucoConFlor.quiero();

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.ORO));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.ORO));

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        trucoConFlor.reTruco();
        trucoConFlor.quiero();

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO,Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.FALSO_ANCHO, Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.TRES, Palo.COPA));

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO,Palo.COPA));

        trucoConFlor.sumarPuntos();

        assertEquals(3, this.trucoConFlor.puntosEquipoUno());
        assertEquals(0, this.trucoConFlor.puntosEquipoDos());
    }

    @Test
    public void cantarFlorSinTenerOtorgaTresPuntosAlOtroEquipo(){
        Jugador facu = new Jugador("Facu");
        facu.recibirCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        facu.recibirCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        facu.recibirCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        Jugador agus = new Jugador("Agus");
        agus.recibirCarta(new Carta(TipoCarta.FALSO_ANCHO, Palo.COPA));
        agus.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.COPA));
        agus.recibirCarta(new Carta(TipoCarta.CINCO,Palo.ORO));

        Equipo equipoUno = new Equipo();
        facu.asignarEquipo(equipoUno);
        equipoUno.agregarJugador(facu);

        Equipo equipoDos = new Equipo();
        agus.asignarEquipo(equipoDos);
        equipoDos.agregarJugador(agus);

        TrucoConFlor trucoConFlor = new TrucoConFlor(equipoUno,equipoDos);

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO,Palo.COPA));

        trucoConFlor.flor(); // canta el equipo dos

        assertEquals(3,trucoConFlor.puntosEquipoUno());
    }

    @Test
    public void cantarFlorMasContraFlorAlRestoOtorgaLosPuntosNecesaiosParaGanar(){
        trucoConFlor.flor();
        trucoConFlor.contraFlorAlResto();
        trucoConFlor.quiero();

        trucoConFlor.sumarPuntos();

        assertEquals(30, this.trucoConFlor.puntosEquipoUno());
        assertEquals(0, this.trucoConFlor.puntosEquipoDos());
    }

    @Test
    public void juegoConFlorYTruco(){
        trucoConFlor.flor();

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoConFlor.truco();
        trucoConFlor.quiero();

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.ORO));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.ORO));

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        trucoConFlor.reTruco();
        trucoConFlor.quiero();

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO,Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.FALSO_ANCHO, Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.TRES, Palo.COPA));

        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        trucoConFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO,Palo.COPA));

        trucoConFlor.sumarPuntos();

        assertEquals(6, this.trucoConFlor.puntosEquipoUno());
        assertEquals(0, this.trucoConFlor.puntosEquipoDos());
    }
}

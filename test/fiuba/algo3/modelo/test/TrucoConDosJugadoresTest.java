package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.tipoJuego.TrucoSinFlor;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Facundo on 18-Nov-15.
 */
public class TrucoConDosJugadoresTest {

    private TrucoSinFlor trucoSinFlor;
    private Equipo equipoUno,equipoDos;

    @Before
    public void setup(){

        Jugador facu = new Jugador("Facu");
        facu.recibirCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        facu.recibirCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        facu.recibirCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        Jugador homero = new Jugador("Homero");
        homero.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        homero.recibirCarta(new Carta(TipoCarta.REY, Palo.BASTO));
        homero.recibirCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

        this.equipoUno = new Equipo();
        facu.asignarEquipo(equipoUno);
        equipoUno.agregarJugador(facu);

        this.equipoDos = new Equipo();
        homero.asignarEquipo(equipoDos);
        equipoDos.agregarJugador(homero);

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

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        trucoSinFlor.sumarPuntos();

        
        assertEquals( 4, trucoSinFlor.puntosEquipoDos() );
        assertEquals( 2, trucoSinFlor.puntosEquipoUno());
        
    }

    @Test
    public void juegoConRondaEmpardada(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));

        trucoSinFlor.sumarPuntos();

        assertEquals(1, trucoSinFlor.puntosEquipoUno());
        assertEquals(0, trucoSinFlor.puntosEquipoDos());
    }

    @Test (expected = AccionInvalidaException.class)
    public void jugarCartaDespuesDeNoQuererTrucoLanzaExcepcion(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.truco();
        trucoSinFlor.noQuiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));
    }

    @Test (expected = AccionInvalidaException.class)
    public void jugarCartaDespuesDeNoQuererReTrucoLanzaExcepcion(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.truco();
        trucoSinFlor.reTruco();
        trucoSinFlor.noQuiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));
    }

    @Test (expected = AccionInvalidaException.class)
    public void jugarCartaDespuesDeNoQuererValeCuatroLanzaExcepcion(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS,Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));

        trucoSinFlor.truco();
        trucoSinFlor.reTruco();
        trucoSinFlor.valeCuatro();
        trucoSinFlor.noQuiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));
    }

    @Test
    public void cantosYcartasIntercalados(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));

        trucoSinFlor.envido();
        trucoSinFlor.noQuiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        trucoSinFlor.truco();
        trucoSinFlor.reTruco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));

        trucoSinFlor.sumarPuntos();

        assertEquals(0, trucoSinFlor.puntosEquipoUno());
        assertEquals(4, trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void juegoSoloConEnvido(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));

        trucoSinFlor.envido();
        trucoSinFlor.realEnvido();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO,Palo.ORO));

        trucoSinFlor.sumarPuntos();

        assertEquals(5, trucoSinFlor.puntosEquipoUno());
        assertEquals(1, trucoSinFlor.puntosEquipoDos());
    }

    @Test ( expected = AccionInvalidaException.class)
    public void jugarCartaSinResponderEnvidoLanzaExcepcion(){
        trucoSinFlor.envido();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
    }

    @Test ( expected = AccionInvalidaException.class)
    public void jugarCartaSinResponderTrucoLanzaExcepcion(){
        trucoSinFlor.truco();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
    }

    @Test
    public void cantarReTrucoYValeCuatroDespuesDeUnQuiero() {
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));

        trucoSinFlor.truco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.reTruco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

        trucoSinFlor.valeCuatro();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoSinFlor.sumarPuntos();

        assertEquals(4, trucoSinFlor.puntosEquipoUno());
        assertEquals(0, trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void jugadorCantaTrucoLuegoDeJugarCarta(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.truco();
        trucoSinFlor.noQuiero();

        assertEquals(0, trucoSinFlor.puntosEquipoUno());
        assertEquals(1, trucoSinFlor.puntosEquipoDos());
    }

    @Test
    public void enLasMalasGanarFaltaEnvidoOtorgaLosPuntosNecesariosParaLlegarAQuince(){
        trucoSinFlor.faltaEnvido();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

        trucoSinFlor.sumarPuntos();

        assertEquals(15, trucoSinFlor.puntosEquipoUno());
        assertEquals(1, trucoSinFlor.puntosEquipoDos());
    }

    @Test ( expected = AccionInvalidaException.class )
    public void jugarCartaDespuesDeFinalizarLaManoLanzaExcepcion(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
    }

    @Test
    public void embidoEmpardadoLoGanaElEquipoMano(){
        Jugador facu = new Jugador("Facu");
        facu.recibirCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        facu.recibirCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        facu.recibirCarta(new Carta(TipoCarta.SEIS,Palo.COPA));

        Jugador homero = new Jugador("Homero");
        homero.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
        homero.recibirCarta(new Carta(TipoCarta.REY, Palo.BASTO));
        homero.recibirCarta(new Carta(TipoCarta.SEIS, Palo.BASTO));

        Equipo equipoUno = new Equipo();
        facu.asignarEquipo(equipoUno);
        equipoUno.agregarJugador(facu);

        Equipo equipoDos = new Equipo();
        homero.asignarEquipo(equipoDos);
        equipoDos.agregarJugador(homero);

        TrucoSinFlor truco = new TrucoSinFlor(equipoUno,equipoDos);

        truco.envido();
        truco.quiero();

        truco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        truco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        truco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
        truco.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.BASTO));

        truco.sumarPuntos();

        assertEquals(2, truco.puntosEquipoUno());
        assertEquals(1, truco.puntosEquipoDos());

    }

    @Test
    public void sumarPuntosSinFinalizarLaManoNoSumaPuntos(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.sumarPuntos();

        assertEquals(0, trucoSinFlor.puntosEquipoUno());
        assertEquals(0, trucoSinFlor.puntosEquipoDos());

    }

    @Test ( expected = CantoInvalidoException.class)
    public void equipoQueCantaTrucoNoPuedeDecirReTruco(){
        trucoSinFlor.truco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));

        trucoSinFlor.reTruco();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void equipoQueCantaReTrucoNoPuedeCantarValeCuatro(){
        trucoSinFlor.truco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.reTruco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.valeCuatro();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void despuesDeEnvidoQueridoNoPuedeHaberRealEnvido(){
        trucoSinFlor.envido();
        trucoSinFlor.quiero();
        trucoSinFlor.realEnvido();
    }

    @Test ( expected = CantoInvalidoException.class)
    public void despuesDeEnvidoQueridoNoPuedeHaberFaltaEnvido() {
        trucoSinFlor.envido();
        trucoSinFlor.quiero();
        trucoSinFlor.faltaEnvido();
    }

    @Test
    public void hayGanadorDevuelveFalsoSiNoHayGanador(){
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));

        trucoSinFlor.truco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

        trucoSinFlor.reTruco();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

        trucoSinFlor.valeCuatro();
        trucoSinFlor.quiero();

        trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

        trucoSinFlor.sumarPuntos();

        assertFalse(trucoSinFlor.hayGanador());
    }

    @Test
    public void hayGanadorDeVuelveTrueCuandoUnEquipoAlcanzaTreintaPuntos(){
        for(int i = 0; i < 20; i++){
            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));

            trucoSinFlor.truco();
            trucoSinFlor.quiero();

            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

            trucoSinFlor.reTruco();
            trucoSinFlor.quiero();

            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));
            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

            trucoSinFlor.sumarPuntos();

            trucoSinFlor.restablecer();
        }

        assertTrue(trucoSinFlor.hayGanador());
    }

    @Test
    public void ganadorDeJuegoDevuelveEquipoGanador(){
        for(int i = 0; i < 20; i++){
            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SEIS, Palo.COPA));
            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));

            trucoSinFlor.truco();
            trucoSinFlor.quiero();

            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.COPA));

            trucoSinFlor.reTruco();
            trucoSinFlor.quiero();

            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));

            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));
            trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));

            trucoSinFlor.sumarPuntos();

            trucoSinFlor.restablecer();
        }

        assertEquals("Equipo 1",trucoSinFlor.ganadorDeJuego());
    }
}

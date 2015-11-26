package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.CambiadorDeTurno;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Facundo on 20-Nov-15.
 */
public class TurnoTest {

    private CambiadorDeTurno turno;
    Equipo equipoUno;
    Equipo equipoDos;

    Jugador facu = new Jugador("Facu");
    Jugador agus = new Jugador("Agus");
    Jugador homero = new Jugador("Homero");
    Jugador lisa = new Jugador("Lisa");

    @Before
    public void setup(){

        equipoUno = new Equipo();
        equipoUno.agregarJugador(this.facu);
        equipoUno.agregarJugador(this.agus);

        equipoDos = new Equipo();
        equipoDos.agregarJugador(this.homero);
        equipoDos.agregarJugador(this.lisa);

         this.turno = new CambiadorDeTurno(equipoUno,equipoDos);
    }

    @Test
    public void rotarCambiaElEquipoDeTurno(){
        assertEquals(this.equipoUno,this.turno.equipoDeTurno());
        this.turno.rotarEquipoDeTurno();
        assertEquals(this.equipoDos,this.turno.equipoDeTurno());
    }

    @Test
    public void calcularJugadorDeTurnoMuestraProximoJugador(){
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno() );
        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
    }

    @Test
    public void calcularJugadorDeTurnoAlFinalDeLaRondaComienzaDeNuevo(){
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno() );
        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
        assertEquals(this.agus, this.turno.calcularJugadorDeTurno() );
        assertEquals(this.lisa, this.turno.calcularJugadorDeTurno() );
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno() );
    }

    @Test
    public void calcularJugadorDeTurnoAlFinalDeLaRondaComienzaPorElQueJuegaCartaMayor(){
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CINCO,Palo.ESPADA));
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CUATRO,Palo.ORO));
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CINCO,Palo.COPA));

        assertEquals(this.homero,this.turno.calcularJugadorDeTurno());
    }
}

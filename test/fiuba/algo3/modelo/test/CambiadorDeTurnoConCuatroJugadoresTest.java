package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.CambiadorDeTurno;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.excepciones.JugadorInexistenteException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Facundo on 20-Nov-15.
 */
public class CambiadorDeTurnoConCuatroJugadoresTest {

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
        this.turno.rotarJugador();
        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
    }

    @Test
    public void calcularJugadorDeTurnoAlFinalDeLaRondaComienzaDeNuevo(){
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        this.turno.rotarJugador();
        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();
        assertEquals(this.agus, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();
        assertEquals(this.lisa, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno() );
    }

    @Test
    public void calcularJugadorDeTurnoAlFinalDeLaRondaComienzaPorElQueJuegaCartaMayor(){
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CINCO,Palo.ESPADA));
        this.turno.rotarJugador();
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));
        this.turno.rotarJugador();
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CUATRO,Palo.ORO));
        this.turno.rotarJugador();
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CINCO,Palo.COPA));
        this.turno.rotarJugador();

        assertEquals(this.homero,this.turno.calcularJugadorDeTurno());
    }

    @Test
    public void establecerJugadorDeTurnoCambiaLaRonda(){
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        this.turno.rotarJugador();
        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();
        assertEquals(this.agus, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();
        assertEquals(this.lisa, this.turno.calcularJugadorDeTurno() );

        this.turno.establecerJugadorDeTurno(this.homero);

        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();
        assertEquals(this.agus, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();
        assertEquals(this.lisa, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
    }

    @Test ( expected = JugadorInexistenteException.class)
    public void establecerJugadorDeTurnoInexistenteLanzaExcepcion(){
        this.turno.establecerJugadorDeTurno(new Jugador("no existe"));
    }

    @Test
    public void calcualaJugadorDeTurnoSinRotarJugadorNoRota(){
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
    }
}

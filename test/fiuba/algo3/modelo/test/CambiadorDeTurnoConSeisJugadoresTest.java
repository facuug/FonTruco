package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.CambiadorDeTurno;
import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.excepciones.JugadorInexistenteException;

public class CambiadorDeTurnoConSeisJugadoresTest {
	
    private CambiadorDeTurno turno;
    Equipo equipoUno;
    Equipo equipoDos;

    Jugador facu = new Jugador("Facu");
    Jugador agus = new Jugador("Agus");
    Jugador homero = new Jugador("Homero");
    Jugador lisa = new Jugador("Lisa");
    Jugador ana = new Jugador("Ana");
    Jugador ruben = new Jugador("Ruben");

	@Before
	public void setUp() throws Exception {
		
        equipoUno = new Equipo();
        equipoUno.agregarJugador(this.facu);
        equipoUno.agregarJugador(this.agus);
        equipoUno.agregarJugador(this.ana);

        equipoDos = new Equipo();
        equipoDos.agregarJugador(this.homero);
        equipoDos.agregarJugador(this.lisa);
        equipoDos.agregarJugador(this.ruben);

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
        this.turno.rotarJugador();		//	Homero
        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Agus
        assertEquals(this.agus, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Lisa
        assertEquals(this.lisa, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Ana
        assertEquals(this.ana, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Ruben
        assertEquals(this.ruben, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Facu
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno() );
    }

    @Test
    public void calcularJugadorDeTurnoAlFinalDeLaRondaComienzaPorElQueJuegaCartaMayor(){
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CINCO,Palo.ESPADA));
        this.turno.rotarJugador();		//	Homero
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));
        this.turno.rotarJugador();		//	Agus
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CUATRO,Palo.ORO));
        this.turno.rotarJugador();		// 	Lisa
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CINCO,Palo.COPA));
        this.turno.rotarJugador();		//	Ana
        this.turno.jugadorJuegaCarta(this.turno.calcularJugadorDeTurno(),new Carta(TipoCarta.CINCO,Palo.COPA));
        this.turno.rotarJugador();		//	Ruben

        assertEquals(this.ruben,this.turno.calcularJugadorDeTurno());
    }

    @Test
    public void establecerJugadorDeTurnoCambiaLaRonda(){
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        this.turno.rotarJugador();		//	Homero
        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Agus
        assertEquals(this.agus, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Lisa
        assertEquals(this.lisa, this.turno.calcularJugadorDeTurno() );

        this.turno.establecerJugadorDeTurno(this.homero);

        assertEquals(this.homero, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Agus
        assertEquals(this.agus, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Lisa
        assertEquals(this.lisa, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Ana
        assertEquals(this.ana, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Ruben
        assertEquals(this.ruben, this.turno.calcularJugadorDeTurno() );
        this.turno.rotarJugador();		//	Facu	
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
    }

    @Test ( expected = JugadorInexistenteException.class)
    public void establecerJugadorDeTurnoInexistenteLanzaExcepcion(){
        this.turno.establecerJugadorDeTurno(new Jugador("no existe"));
    }

    @Test
    public void calcularJugadorDeTurnoSinRotarJugadorNoRota(){
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
        assertEquals(this.facu, this.turno.calcularJugadorDeTurno());
    }
}

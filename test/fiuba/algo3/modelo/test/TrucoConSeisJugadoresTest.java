package fiuba.algo3.modelo.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.tipoJuego.JuegoTruco;
import fiuba.algo3.modelo.tipoJuego.implementaciones.TrucoSinFlor;

public class TrucoConSeisJugadoresTest {

	private JuegoTruco trucoSinFlor;
	
	@Before
	public void setUp() throws Exception {
		
		Jugador agus    = new Jugador("Agus");
		agus.recibirCarta(new Carta(TipoCarta.CINCO, Palo.COPA));
		agus.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.COPA));
		agus.recibirCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));
		
		Jugador benja   = new Jugador("Benja");
		benja.recibirCarta(new Carta(TipoCarta.REY, Palo.COPA));
		benja.recibirCarta(new Carta(TipoCarta.CINCO, Palo.BASTO));
		benja.recibirCarta(new Carta(TipoCarta.REY, Palo.ORO));
		
		Jugador carla   = new Jugador("Carla");
		carla.recibirCarta(new Carta(TipoCarta.DOS, Palo.BASTO));
		carla.recibirCarta(new Carta(TipoCarta.CINCO, Palo.ESPADA));
		carla.recibirCarta(new Carta(TipoCarta.DOS, Palo.COPA));
		
		Jugador daniel  = new Jugador("Daniel");
		daniel.recibirCarta(new Carta(TipoCarta.FALSO_ANCHO, Palo.ORO));
		daniel.recibirCarta(new Carta(TipoCarta.FALSO_SIETE, Palo.BASTO));
		daniel.recibirCarta(new Carta(TipoCarta.DOS, Palo.ORO));
		
		Jugador esteban = new Jugador("Esteban");
		esteban.recibirCarta(new Carta(TipoCarta.SOTA, Palo.BASTO));
		esteban.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.ORO));
		esteban.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.BASTO));
		
		Jugador flor	= new Jugador("Flor");
		flor.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));
		flor.recibirCarta(new Carta(TipoCarta.TRES, Palo.ORO));
		flor.recibirCarta(new Carta(TipoCarta.REY, Palo.BASTO));
		
		
		Equipo equipoUno = new Equipo();
		
		agus.asignarEquipo(equipoUno);
		equipoUno.agregarJugador(agus);
		benja.asignarEquipo(equipoUno);
		equipoUno.agregarJugador(benja);
		carla.asignarEquipo(equipoUno);
		equipoUno.agregarJugador(carla);
		
		Equipo equipoDos = new Equipo();
		
		daniel.asignarEquipo(equipoDos);
		equipoDos.agregarJugador(daniel);
		esteban.asignarEquipo(equipoDos);
		equipoDos.agregarJugador(esteban);
		flor.asignarEquipo(equipoDos);
		equipoDos.agregarJugador(flor);
		
		this.trucoSinFlor = new TrucoSinFlor(equipoUno,equipoDos);
	}
	
	public void jugarUnaRonda() {
		
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO, Palo.COPA));			//	Agus
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.FALSO_SIETE, Palo.BASTO));	//	Daniel
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.COPA));				//	Benja
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.SOTA, Palo.BASTO));			//	Esteban
		
		trucoSinFlor.envido();
		trucoSinFlor.noQuiero();					//	Equipo 1: 1 - Equipo 2: 0
		
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.DOS, Palo.BASTO));			//	Carla
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.TRES, Palo.ORO));				//	Flor <- GANA RONDA
		
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CUATRO, Palo.ESPADA));		//	Flor
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CABALLO, Palo.COPA));			//	Agus
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.FALSO_ANCHO, Palo.ORO));		//	Daniel
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO, Palo.BASTO));			//	Benja
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CABALLO, Palo.ORO));			//	Esteban
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.DOS, Palo.COPA));				//	Carla <- GANA RONDA
		
		trucoSinFlor.truco();
		trucoSinFlor.reTruco();
		trucoSinFlor.valeCuatro();
		trucoSinFlor.quiero();
		
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CINCO, Palo.ESPADA));			//	Carla
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.BASTO));			//	Flor
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA));	//	Agus <- GANA RONDA	
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.DOS, Palo.ORO));				//	Daniel
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.REY, Palo.ORO));				//	Benja
		trucoSinFlor.jugadorDeTurnoJuegaCarta(new Carta(TipoCarta.CABALLO, Palo.BASTO));		//	Esteban
		
		trucoSinFlor.sumarPuntos();					//	Equipo 1: 5 - Equipo 2: 0
	}

	@Test
	public void elJuegoComienzaConRondaRedondaTest() {
		
		Assert.assertFalse(this.trucoSinFlor.esPicaPica());
	}
	
	@Test
	public void alLlegarACincoPuntosLaSiguienteRondaEsPicaPicaTest() {
		
		this.jugarUnaRonda();			//	Equipo 1: 5 - Equipo 2: 0
				
		trucoSinFlor.restablecer();
		
		Assert.assertTrue(trucoSinFlor.esPicaPica());
	}
	
	@Test
	public void entreLosCincoYVeinticincoPuntosSeJuegaUnaManoRedondaYOtraPicaPicaTest() {
		
		this.jugarUnaRonda();			//	Equipo 1: 5 - Equipo 2: 0
		
		trucoSinFlor.restablecer();
		
		this.jugarUnaRonda();			//	Equipo 1: 10 - Equipo 2: 0
		
		trucoSinFlor.restablecer();
		
		Assert.assertFalse(this.trucoSinFlor.esPicaPica());
	}
	
	@Test
	public void pasadosLosVeinticincoPuntosSoloSeJuegaManoRedondaTest(){
		
		this.jugarUnaRonda();			//	Equipo 1: 5 - Equipo 2: 0
		
		trucoSinFlor.restablecer();
		
		this.jugarUnaRonda();			//	Equipo 1: 10 - Equipo 2: 0
		
		this.trucoSinFlor.restablecer();
		
		this.jugarUnaRonda();			//	Equipo 1: 15 - Equipo 2: 0
		
		this.trucoSinFlor.restablecer();
		
		this.jugarUnaRonda();			//	Equipo 1: 20 - Equipo 2: 0
		
		this.trucoSinFlor.restablecer();
		
		Assert.assertFalse(this.trucoSinFlor.esPicaPica());
	}
}
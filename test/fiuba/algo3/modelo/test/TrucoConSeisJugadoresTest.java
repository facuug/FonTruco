package fiuba.algo3.modelo.test;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.estados.TrucoSinFlor;
import fiuba.algo3.modelo.interfaces.JuegoTruco;

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
		esteban.recibirCarta(new Carta(TipoCarta.CABALLO, Palo.ORO));
		
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

	@Test
	public void elJuegoComienzaConRondaRedondaTest() {
		
		int cantidadDeJugadores = 0;
		
		Iterator<Equipo> i = this.trucoSinFlor.obtenerMesa().getEquipos().iterator();
		
		while (i.hasNext()) {
			
			Equipo equipoActual = i.next();
			
			cantidadDeJugadores += equipoActual.cantidadDeJugadores();
		}
		
		Assert.assertEquals(6, cantidadDeJugadores);
	}
	
	@Test
	public void alLlegarACincoPuntosLaSiguienteRondaEsPicaPicaTest() {
		
		Assert.assertTrue(false);
	}
	
	@Test
	public void entreLosCincoYVeinticincoPuntosSeJuegaUnaManoRedondaYOtraPicaPicaTest() {
		
		Assert.assertTrue(false);
	}
	
	@Test
	public void pasadosLosVeinticincoPuntosSoloSeJuegaManoRedondaTest(){
		
		Assert.assertTrue(false);
	}

}

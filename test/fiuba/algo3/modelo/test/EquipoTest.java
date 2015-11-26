package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.excepciones.JugadorInexistenteException;
import fiuba.algo3.modelo.excepciones.NoHayJugadoresException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;

public class EquipoTest {

	private Equipo equipoPrueba;
	
	@Before
	public void setUp() {

		Jugador agus = new Jugador("Agus");
		agus.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.ORO));
		agus.recibirCarta(new Carta(TipoCarta.CINCO, Palo.ORO));
		agus.recibirCarta(new Carta(TipoCarta.SIETE_ORO, Palo.ORO));

		Jugador facu = new Jugador("Facu");
		facu.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));
		facu.recibirCarta(new Carta(TipoCarta.CUATRO, Palo.BASTO));
		facu.recibirCarta(new Carta(TipoCarta.CINCO, Palo.BASTO));

		this.equipoPrueba = new Equipo();
		this.equipoPrueba.agregarJugador(facu);
		this.equipoPrueba.agregarJugador(agus);
	}
	
	@Test
	public void elEquipoSeCreaConCeroPunto() {
		
		Assert.assertEquals(0, this.equipoPrueba.obtenerPuntos());
	}

	@Test
	public void jugadorDeTurnoDevuelveJugadorSiguiente(){
		Assert.assertEquals( "Facu",this.equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "Agus",this.equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "Facu",this.equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "Agus",this.equipoPrueba.jugadorDeTurno().getNombre() );
	}

	@Test
	public void puntosDeEnvidoDevuelveMayorPuntajeDeEnvido(){
		Assert.assertEquals(32, this.equipoPrueba.puntosDeEnvido());
	}

	@Test
	public void PuntosDeEnvidoDevuelveCeroSiNoHayPuntosDeEnvido(){
		Equipo otroEquipo = new Equipo();
		otroEquipo.agregarJugador(new Jugador("Alguien"));

		Assert.assertEquals(0,otroEquipo.puntosDeEnvido());
	}

	@Test
	public void siNoHayJugadoresPuntosDeEnvidoDevuelveCero(){
		Equipo otroEquipo = new Equipo();
		Assert.assertEquals(0,otroEquipo.puntosDeEnvido());
	}

	@Test ( expected = NoHayJugadoresException.class)
	public void siNoHayJugadoresJugadorDeTurnoLanzaExcepcion(){
		Equipo otroEquipo = new Equipo();
		otroEquipo.jugadorDeTurno();
	}

	@Test
	public void puntosDeFlorDevuelveMayorPuntajeDeFlor(){
		Assert.assertEquals(36, this.equipoPrueba.puntosDeFlor() );
	}

	@Test
	public void establecerJugadorDeTurnoLoGuardaComoProximoJugadorDeTurno(){
		Jugador alguien = new Jugador("Alguien");
		this.equipoPrueba.agregarJugador(alguien);

		this.equipoPrueba.establecerJugadorDeTurno(alguien);
		Assert.assertEquals(alguien,this.equipoPrueba.jugadorDeTurno());
	}

	@Test ( expected = JugadorInexistenteException.class)
	public void establecerJugadorDeTurnoConJugadorInexistenteEnEquipoLanzaExcepcion(){
		this.equipoPrueba.establecerJugadorDeTurno(new Jugador("Alguien"));
	}
}

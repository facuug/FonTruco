package fiuba.algo3.modelo.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;

public class EquipoTest {

	private Equipo equipoPrueba;
	
	@Before
	public void setUp() {

		this.equipoPrueba = new Equipo();
		equipoPrueba.agregarJugador(new Jugador("Jorge"));
		equipoPrueba.agregarJugador(new Jugador("Homero"));
	}
	
	@Test
	public void elEquipoSeCreaConCeroPuntoTest() {
		
		Assert.assertEquals(0, this.equipoPrueba.obtenerPuntos());
	}

	@Test
	public void jugadorDeTurnoDevuelveJugadorSiguienteTest(){
		Assert.assertEquals( "Jorge" , equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "Homero", equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "Jorge" , equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "Homero", equipoPrueba.jugadorDeTurno().getNombre() );
	}
	
	@Test
	public void cantidadDeJugadoresDevuelveLaCantidadCorrectaDeIntegrantesDelEquipoTest() {
		
		Assert.assertEquals(2, this.equipoPrueba.cantidadDeJugadores());
	}
}

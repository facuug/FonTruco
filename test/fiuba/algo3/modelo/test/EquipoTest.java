package fiuba.algo3.modelo.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;

public class EquipoTest {

	private Equipo equipoPrueba;
	
	@Before
	public void setUp() {

		Equipo equipoPrueba = new Equipo();
		equipoPrueba.agregarJugador(new Jugador("Facu"));
	}
	
	@Test
	public void elEquipoSeCreaConCeroPunto() {
		
		Assert.assertEquals(0, this.equipoPrueba.obtenerPuntos());
	}

	@Test
	public void jugadorDeTurnoDevuelveJugadorSiguiente(){
		Assert.assertEquals( "Jorge",equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "homero",equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "Jorge",equipoPrueba.jugadorDeTurno().getNombre() );
		Assert.assertEquals( "homero",equipoPrueba.jugadorDeTurno().getNombre() );
	}
}

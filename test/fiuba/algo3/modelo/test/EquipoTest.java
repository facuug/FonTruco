package fiuba.algo3.modelo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;

public class EquipoTest {

	private Equipo equipoPrueba;
	
	@Before
	public void setUp() {
		
		Jugador jugadorPrueba = new Jugador("Jorge");
		
		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugadorPrueba);
		
		this.equipoPrueba = new Equipo(jugadores);
	}
	
	@Test
	public void elEquipoSeCreaConCeroPunto() {
		
		Assert.assertEquals(0, this.equipoPrueba.getPuntos());
	}
}

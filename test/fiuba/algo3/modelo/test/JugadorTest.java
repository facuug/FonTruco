package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.excepciones.NoHayMasCartasException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.Jugador;

public class JugadorTest {

	private Jugador jugador;
	
	@Before
	public void setUp() {
		
		this.jugador = new Jugador("Jorge");
	}
	
	@Test
	public void elJugadorSeCreaCorrectamenteYSinCartasTest() {
		
		Assert.assertEquals("Jorge", this.jugador.getNombre());
		Assert.assertEquals(0, this.jugador.cartasEnMano());
	}

	@Test (expected = NoHayMasCartasException.class)
	public void jugarCartaCuandoNoHayLanzaExcepcion(){
		this.jugador.jugarCarta(3);
	}
}

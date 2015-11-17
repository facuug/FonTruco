package fiuba.algo3.modelo.test;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Mazo;
import fiuba.algo3.modelo.excepciones.NoHayMasCartasException;

public class MazoTest {
	
	@Test
	public void elMazoSeCreaConCuarentaCartasTest() {
		
		Mazo mazo = new Mazo();
		
		Assert.assertEquals(40, mazo.cantidadDeCartas());	
	}
	
	@Test ( expected = NoHayMasCartasException.class )
	public void noSePuedeRepartirSiNoHayCartasTest() {
		
		Mazo mazo = new Mazo();
		
		for(int i = 0; i < 41; i++) {
			
			mazo.repartirCarta();
		}
	}
	
	
}
package fiuba.algo3.modelo.test;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Mazo;

public class MazoTest {
	
	@Test
	public void elMazoSeCreaConCuarentaCartasTest() {
		
		Mazo mazo = new Mazo();
		
		Assert.assertEquals(40, mazo.cantidadDeCartas());	
	}
}

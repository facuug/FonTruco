package fiuba.algo3.modelo.test;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.AnchoDeBasto;
import fiuba.algo3.modelo.AnchoDeEspada;
import fiuba.algo3.modelo.AnchoFalso;
import fiuba.algo3.modelo.Cinco;
import fiuba.algo3.modelo.Cuatro;
import fiuba.algo3.modelo.Diez;
import fiuba.algo3.modelo.Doce;
import fiuba.algo3.modelo.Dos;
import fiuba.algo3.modelo.Jugable;
import fiuba.algo3.modelo.Once;
import fiuba.algo3.modelo.Seis;
import fiuba.algo3.modelo.SieteDeEspada;
import fiuba.algo3.modelo.SieteDeOro;
import fiuba.algo3.modelo.SieteFalso;
import fiuba.algo3.modelo.Tres;

public class JugableTest {

	private Jugable anchoDeEspada = new AnchoDeEspada();
	private Jugable anchoDeBasto  = new AnchoDeBasto();
	private Jugable sieteDeEspada = new SieteDeEspada();
	private Jugable sieteDeOro    = new SieteDeOro();
	private Jugable tres          = new Tres();
	private Jugable dos           = new Dos();
	private Jugable anchoFalso    = new AnchoFalso();
	private Jugable doce          = new Doce(); 
	private Jugable once          = new Once();
	private Jugable diez          = new Diez();
	private Jugable sieteFalso    = new SieteFalso();
	private Jugable seis          = new Seis();
	private Jugable cinco         = new Cinco();
	private Jugable cuatro        = new Cuatro();
	
	@Test
	public void anchoDeEspadaGanaSiempreTest() {
		
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(anchoDeBasto));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(sieteDeEspada));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(sieteDeOro));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(tres));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(dos));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(anchoFalso));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(doce));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(once));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(diez));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(sieteFalso));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(seis));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(cinco));
		Assert.assertEquals(anchoDeEspada, anchoDeEspada.contra(cuatro));
	}
	
	@Test
	public void elCuatroPierdeConLasDemasCartasTest() {
		
		Assert.assertEquals(anchoDeEspada, cuatro.contra(anchoDeEspada));
		Assert.assertEquals(anchoDeBasto, cuatro.contra(anchoDeBasto));
		Assert.assertEquals(sieteDeEspada, cuatro.contra(sieteDeEspada));
		Assert.assertEquals(sieteDeOro, cuatro.contra(sieteDeOro));
		Assert.assertEquals(tres, cuatro.contra(tres));
		Assert.assertEquals(dos, cuatro.contra(dos));
		Assert.assertEquals(anchoFalso, cuatro.contra(anchoFalso));
		Assert.assertEquals(doce, cuatro.contra(doce));
		Assert.assertEquals(once, cuatro.contra(once));
		Assert.assertEquals(diez, cuatro.contra(diez));
		Assert.assertEquals(sieteFalso, cuatro.contra(sieteFalso));
		Assert.assertEquals(seis, cuatro.contra(seis));
		Assert.assertEquals(cinco, cuatro.contra(cinco));
	}
}
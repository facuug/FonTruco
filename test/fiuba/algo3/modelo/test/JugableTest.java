package fiuba.algo3.modelo.test;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.cartas.AnchoDeBasto;
import fiuba.algo3.modelo.cartas.AnchoDeEspada;
import fiuba.algo3.modelo.cartas.AnchoFalso;
import fiuba.algo3.modelo.cartas.Cinco;
import fiuba.algo3.modelo.cartas.Cuatro;
import fiuba.algo3.modelo.cartas.Diez;
import fiuba.algo3.modelo.cartas.Doce;
import fiuba.algo3.modelo.cartas.Dos;
import fiuba.algo3.modelo.cartas.Once;
import fiuba.algo3.modelo.cartas.Seis;
import fiuba.algo3.modelo.cartas.SieteDeEspada;
import fiuba.algo3.modelo.cartas.SieteDeOro;
import fiuba.algo3.modelo.cartas.SieteFalso;
import fiuba.algo3.modelo.cartas.Tres;
import fiuba.algo3.modelo.interfaces.Jugable;

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
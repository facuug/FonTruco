package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;


public class ManoTest {


	private Mano mano;
	
	@Before
	public void setUp() {

		this.mano = new Mano();
		
		this.mano.recibirCarta( new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA) );
		this.mano.recibirCarta( new Carta(TipoCarta.CUATRO, Palo.BASTO) );
	}
	
	@Test
	public void sacarCarta_posicionEnElRango_DevuelveCartaEnLaPosicion(){
		
		int posicionBuscada = 2;
		Carta cartaBuscada = new Carta(TipoCarta.SEIS,Palo.COPA);
		this.mano.recibirCarta(cartaBuscada);
		int tamanioAntesDeLaExtraccion = this.mano.cantidadDeCartas();
		
		Carta otraCarta = mano.sacarCarta(posicionBuscada);
		
		assertEquals(cartaBuscada.getPalo(),otraCarta.getPalo());
		assertEquals(cartaBuscada.getTipoCarta(), otraCarta.getTipoCarta());
		assertEquals(tamanioAntesDeLaExtraccion-1, this.mano.cantidadDeCartas());
	}
	
	@Test
	public void sacarCarta_ConPosicionFueraDelRango_DevuelveUltimaCarta() {
		int posicion = this.mano.cantidadDeCartas() + 100;
		Carta cartaEsperada = new Carta(TipoCarta.CUATRO,Palo.BASTO);
		
		
		Carta ultimaCarta = mano.sacarCarta(posicion);
		
		assertEquals(cartaEsperada.getPalo(),ultimaCarta.getPalo());
		assertEquals(cartaEsperada.getTipoCarta(),ultimaCarta.getTipoCarta());
	}
}
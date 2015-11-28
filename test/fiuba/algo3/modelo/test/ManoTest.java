package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import fiuba.algo3.modelo.excepciones.NoHayFlorException;
import fiuba.algo3.modelo.excepciones.NoHayMasCartasException;
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

	@Test (expected = NoHayMasCartasException.class)
	public void sacarCartaCuandoNoHayLanzaExcepcion(){
		Mano mano = new Mano();
		mano.sacarCarta(0);
	}

	@Test
	public void puntosDeEnvidoConManoConFlorDevuelveLaMayorPuntuacion(){
		Mano mano = new Mano();
		mano.recibirCarta(new Carta(TipoCarta.ANCHO_ESPADA,Palo.ESPADA));
		mano.recibirCarta(new Carta(TipoCarta.CUATRO,Palo.ESPADA));
		mano.recibirCarta(new Carta(TipoCarta.TRES,Palo.ESPADA));

		assertEquals(27,mano.puntosDeEnvido());
	}

	@Test ( expected = NoHayMasCartasException.class)
	public void puntosDeEnvidoSinCartasLanzaExcepcion(){
		Mano mano = new Mano();
		mano.puntosDeEnvido();
	}

	@Test ( expected = NoHayMasCartasException.class)
	public void puntosDeFlorSinCartasLanzaExcepcion(){
		Mano mano = new Mano();
		mano.puntosDeFlor();
	}

	@Test ( expected = NoHayFlorException.class )
	public void puntosDeFlorSinFlorLanzaExcepcion(){
		this.mano.puntosDeFlor();
	}

	@Test
	public void puntosDeFlorConFlorDevuelveLosPuntos(){
		Mano mano = new Mano();
		mano.recibirCarta(new Carta(TipoCarta.ANCHO_ESPADA,Palo.ESPADA));
		mano.recibirCarta(new Carta(TipoCarta.CUATRO,Palo.ESPADA));
		mano.recibirCarta(new Carta(TipoCarta.TRES,Palo.ESPADA));

		assertEquals(28,mano.puntosDeFlor());
	}
}
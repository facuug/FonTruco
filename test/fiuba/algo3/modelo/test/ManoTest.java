package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.enums.Palo;


public class ManoTest {
	List<Carta> cartas;
	@Before
	public void setUp() {
		cartas = new ArrayList<>();
		
		cartas.add(new Carta(1,Palo.ESPADA));
		cartas.add(new Carta(4,Palo.BASTO));
		
	}
	
	@Test
	public void sacarCarta_posicionEnElRango_DevuelveCartaEnLaPosicion(){
		
		int posicionBuscada = 2;
		Carta cartaBuscada = new Carta(6,Palo.COPA);
		cartas.add(cartaBuscada);
		int tamanioAntesDeLaExtraccion = cartas.size();
		Mano mano = new Mano();
		mano.setCartas(cartas);
		
		Carta otraCarta = mano.sacarCarta(posicionBuscada);
		
		assertEquals(cartaBuscada.getPalo(),otraCarta.getPalo());
		assertEquals(cartaBuscada.getValor(), otraCarta.getValor());
		assertEquals(tamanioAntesDeLaExtraccion-1,mano.getCartas().size());
	}
	
	@Test
	public void sacarCarta_ConPosicionFueraDelRango_DevuelveUltimaCarta() {
		int posicion = cartas.size()+100;
		Carta cartaEsperada = new Carta(4,Palo.BASTO);
		Mano mano = new Mano();
		mano.setCartas(cartas);
		
		Carta ultimaCarta = mano.sacarCarta(posicion);
		
		assertEquals(cartaEsperada.getPalo(),ultimaCarta.getPalo());
		assertEquals(cartaEsperada.getValor(),ultimaCarta.getValor());
	}
	
}

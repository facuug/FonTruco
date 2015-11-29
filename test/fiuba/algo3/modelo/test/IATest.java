package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.IA;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;

public class IATest {

	@Test
	public void jugar_SiendoPrimeroLaIA_devuelveLaMenorCartaDeSuMano() {
		IA unaIA = new IA();
		Mano unaMano = new Mano();
		unaMano.setCartas(new ArrayList<>(Arrays.asList(new Carta(TipoCarta.CABALLO, Palo.BASTO),
				new Carta(TipoCarta.CABALLO, Palo.COPA),new Carta(TipoCarta.SEIS, Palo.BASTO))));
		unaIA.setMano(unaMano);
		assertEquals(TipoCarta.SEIS, unaIA.jugar(null).getTipoCarta());
	}
	
	@Test
	public void jugar_SiendoSegundoLaIA_devuelveLaMenorCartaDeSuManoQueGaneALaJugada() {
		IA unaIA = new IA();
		Mano unaMano = new Mano();
		unaMano.setCartas(new ArrayList<>(Arrays.asList(new Carta(TipoCarta.CABALLO, Palo.BASTO),
				new Carta(TipoCarta.CABALLO, Palo.COPA),new Carta(TipoCarta.SEIS, Palo.BASTO))));
		unaIA.setMano(unaMano);
		assertEquals(TipoCarta.SEIS, unaIA.jugar(new Carta(TipoCarta.CUATRO,Palo.COPA)).getTipoCarta());
	}
	
	@Test
	public void jugar_SiendoSegundoLaIASinCartaQueGane_devuelveLaMenorCartaDeSuMano() {
		IA unaIA = new IA();
		Mano unaMano = new Mano();
		unaMano.setCartas(new ArrayList<>(Arrays.asList(new Carta(TipoCarta.CABALLO, Palo.BASTO),
				new Carta(TipoCarta.CABALLO, Palo.COPA),new Carta(TipoCarta.SEIS, Palo.BASTO))));
		unaIA.setMano(unaMano);
		assertEquals(TipoCarta.SEIS, unaIA.jugar(new Carta(TipoCarta.ANCHO_ESPADA,Palo.ESPADA)).getTipoCarta());
	}
}

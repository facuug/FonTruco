package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;

public class CartaTest {
	@Test 
	public void crearCarta_conValoresCorrectos_creaCartaConValorEnJerarquiaYEnvido() {
		Carta anchoEspada = new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA);
		
		assertEquals(14,anchoEspada.getTipoCarta().getValor());
		assertEquals(Palo.ESPADA, anchoEspada.getPalo());
		assertEquals(1, anchoEspada.valorDeEnvido());
	}
	
	@Test
	public void comparar_conCartasDeMismoValor_devuelveCero(){
		Carta cuatroDeCopas = new Carta(TipoCarta.CUATRO, Palo.COPA);
		Carta cuatroDeOro = new Carta(TipoCarta.CUATRO, Palo.ORO);
		
		assertEquals(0,cuatroDeCopas.comparar(cuatroDeOro));
		assertEquals(0,cuatroDeOro.comparar(cuatroDeCopas));
	}
	
	@Test
	public void comparar_conCartasDeDistintoValor_devuelveDistintoDeCero() {
		Carta anchoDeCopas = new Carta(TipoCarta.FALSO_ANCHO, Palo.COPA);
		Carta sieteDeEspadas = new Carta(TipoCarta.SIETE_ESPADA, Palo.ESPADA);
		
		assertEquals(-1,anchoDeCopas.comparar(sieteDeEspadas));
		assertEquals(1,sieteDeEspadas.comparar(anchoDeCopas));
	}
}

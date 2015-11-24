package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mano;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;

public class MesaTest {
	private Equipo equipoUno;
	private Equipo equipoDos;
	private Mesa mesa;
	private Mano manoZim;
	private Mano manoDib;
	
	@Before
	public void setUp() {
		equipoUno = new Equipo();
		equipoUno.agregarJugador(new Jugador("Zim"));

		equipoDos = new Equipo();
		equipoDos.agregarJugador(new Jugador("Dib"));

		mesa = new Mesa(equipoUno,equipoDos);
	}
	
	@Test
	public void determinarGanadorDeRondaDevuelveGanadorDeRonda(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));
		
		assertEquals(equipoDos,this.mesa.determinarGanadorDeRonda().verEquipo());
	}
	
	@Test
	public void determinarGanadorDeRondaConUnaSolaCartaDevuelveComoGanadorAlEquipoJugadorDeLaCarta(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		assertEquals(equipoUno,this.mesa.determinarGanadorDeRonda().verEquipo());
	}
	
	@Test
	public void determinarGanadorDeManoDevuelveGanador(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));
		
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));
		
		assertEquals(equipoDos,this.mesa.determinarGanadorDeMano().verEquipo());
	}
	
	private void prepararManosParaTest() {
		manoZim = new Mano();
		manoDib = new Mano();
		Carta cartaUno = new Carta(TipoCarta.CABALLO, Palo.BASTO);
		Carta cartaDos = new Carta(TipoCarta.ANCHO_BASTO, Palo.BASTO);
		Carta cartaTres = new Carta(TipoCarta.CUATRO, Palo.COPA);
		Carta cartaCuatro = new Carta(TipoCarta.ANCHO_ESPADA, Palo.ESPADA);
		Carta cartaCinco = new Carta(TipoCarta.FALSO_ANCHO, Palo.COPA);
		Carta cartaSeis = new Carta(TipoCarta.CUATRO, Palo.ORO);
		manoZim.recibirCarta(cartaUno);
		manoZim.recibirCarta(cartaDos);
		manoZim.recibirCarta(cartaTres);
		manoDib.recibirCarta(cartaCuatro);
		manoDib.recibirCarta(cartaCinco);
		manoDib.recibirCarta(cartaSeis);
	}
	
}

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
		List<Jugador> jugadoresEquipoA = new ArrayList<>();
		List<Jugador> jugadoresEquipoB = new ArrayList<>();
		Jugador unJugador = new Jugador("Zim");
		jugadoresEquipoA.add(unJugador);
		Jugador otroJugador = new Jugador("Dib");
		jugadoresEquipoB.add(otroJugador);
		equipoUno= new Equipo(jugadoresEquipoA);
		equipoDos = new Equipo(jugadoresEquipoB);
		mesa = new Mesa(Arrays.asList(equipoUno,equipoDos));
	}
	
	@Test
	public void repartir_conDosJugadores_ambosJugadoresQuedanConTresCartas() {
		mesa.repartir();
		int cantidadDeCartasZim = mesa.getEquipos().get(0).getJugadores().get(0).getMano().cantidadDeCartas();
		int cantidadDeCartasDib = mesa.getEquipos().get(1).getJugadores().get(0).getMano().cantidadDeCartas();
		
		assertEquals(3, cantidadDeCartasZim);
		assertEquals(3,cantidadDeCartasDib);
	}
	
	@Test
	public void repartir_conCuatroJugadores_losJugadoresQuedanConTresCartas() {
		Jugador unJugador = new Jugador("Kaz");
		Jugador otroJugador = new Jugador("GIR");
		equipoUno.getJugadores().add(unJugador);
		equipoDos.getJugadores().add(otroJugador);
		mesa.repartir();
		int cantidadDeCartasZim = mesa.getEquipos().get(0).getJugadores().get(0).getMano().cantidadDeCartas();
		int cantidadDeCartasDib = mesa.getEquipos().get(1).getJugadores().get(0).getMano().cantidadDeCartas();
		int cantidadDeCartasKaz = mesa.getEquipos().get(0).getJugadores().get(1).getMano().cantidadDeCartas();
		int cantidadDeCartasGIR = mesa.getEquipos().get(1).getJugadores().get(1).getMano().cantidadDeCartas();
		assertEquals(3, cantidadDeCartasZim);
		assertEquals(3,cantidadDeCartasDib);
		assertEquals(3, cantidadDeCartasGIR);
		assertEquals(3,cantidadDeCartasKaz);
	}
	
	@Test
	public void determinarGanadorDeMano_conDosJugadoresEmpatados_devuelveEmparda() {
		prepararManosParaTest();
		equipoUno.getJugadores().get(0).setMano(manoZim);
		equipoDos.getJugadores().get(0).setMano(manoDib);
		mesa.jugarCarta(equipoUno.getJugadores().get(0), equipoUno.getJugadores().get(0).jugarCarta(0));
		mesa.jugarCarta(equipoDos.getJugadores().get(0), equipoDos.getJugadores().get(0).jugarCarta(0));
		mesa.jugarCarta(equipoUno.getJugadores().get(0), equipoUno.getJugadores().get(0).jugarCarta(1));
		mesa.jugarCarta(equipoDos.getJugadores().get(0), equipoDos.getJugadores().get(0).jugarCarta(1));
		mesa.jugarCarta(equipoUno.getJugadores().get(0), equipoUno.getJugadores().get(0).jugarCarta(2));
		mesa.jugarCarta(equipoDos.getJugadores().get(0), equipoDos.getJugadores().get(0).jugarCarta(2));
		Ganador equipoGanador = mesa.determinarGanadorDeMano();
		assertNotNull(equipoGanador);
		assertEquals(Ganador.Emparda,equipoGanador);
	}
	
	
	@Test
	public void determinarGanadorDeMano_conJugadores_devuelveEquipoDos() {
		prepararManosParaTest();
		equipoUno.getJugadores().get(0).setMano(manoZim);
		equipoDos.getJugadores().get(0).setMano(manoDib);
		mesa.jugarCarta(equipoUno.getJugadores().get(0), equipoUno.getJugadores().get(0).jugarCarta(1));
		mesa.jugarCarta(equipoDos.getJugadores().get(0), equipoDos.getJugadores().get(0).jugarCarta(0));
		mesa.jugarCarta(equipoUno.getJugadores().get(0), equipoUno.getJugadores().get(0).jugarCarta(0));
		mesa.jugarCarta(equipoDos.getJugadores().get(0), equipoDos.getJugadores().get(0).jugarCarta(1));
		mesa.jugarCarta(equipoUno.getJugadores().get(0), equipoUno.getJugadores().get(0).jugarCarta(2));
		mesa.jugarCarta(equipoDos.getJugadores().get(0), equipoDos.getJugadores().get(0).jugarCarta(2));
		Ganador equipoGanador = mesa.determinarGanadorDeMano();
		assertNotNull(equipoGanador);
		assertEquals(Ganador.EquipoDos,equipoGanador);
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

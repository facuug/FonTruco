package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;

public class MesaTest {

	private Equipo equipoUno;
	private Equipo equipoDos;
	private Mesa mesa;

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
	public void determinarGanadorDeRondaConUnaSolaCartaDevuelveEmparda(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		assertEquals("empardada",this.mesa.determinarGanadorDeRonda().situacion());
	}
	
	@Test
	public void determinarGanadorDeManoDevuelveGanador(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));
		
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.COPA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.SIETE_ORO,Palo.BASTO));
		
		assertEquals(equipoDos,this.mesa.determinarGanadorDeMano().verEquipo());
	}

	@Test
	public void ganadorDeManoDevuelveGanadorDeManoCompleta(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));

		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.DOS,Palo.COPA));
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.SIETE_ORO,Palo.ORO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.TRES,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.DOS,Palo.ESPADA));

		assertEquals(equipoUno,this.mesa.determinarGanadorDeMano().verEquipo());
	}

	@Test
	public void determinarGanadorDeManoConManoEnPrimeraRonda(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));

		assertEquals("empardada",this.mesa.determinarGanadorDeMano().situacion());
	}

	@Test
	public void determinarGanadorDeManoConManoEnSegundaRonda(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.ANCHO_ESPADA,Palo.ESPADA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.TRES,Palo.BASTO));

		assertEquals("empardada",this.mesa.determinarGanadorDeMano().situacion());
	}

	@Test
	public void manoConPrimeraRondaEmapardada(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.DOS,Palo.ORO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.COPA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));

		assertEquals(equipoDos,this.mesa.determinarGanadorDeMano().verEquipo());
	}

	@Test
	public void siSeEmpardaSegundaRondaGanaLaManoElGanadorDeLaPrimera(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ORO));

		assertEquals(equipoDos,this.mesa.determinarGanadorDeMano().verEquipo());
	}

	@Test
	public void SiSeEmpardaTerceraRondaGanaLaManoElGanadorDeLaPrimera(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ORO));

		assertEquals(equipoDos,this.mesa.determinarGanadorDeMano().verEquipo());
	}

	@Test
	public void ganadorDeManoSiSeEmpardanLasDosRondasDevuelveElGanadorDeLaTercera(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ORO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ORO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.DOS,Palo.BASTO));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.ANCHO_BASTO,Palo.BASTO));

		assertEquals(equipoDos,this.mesa.determinarGanadorDeMano().verEquipo());
	}

	@Test
	public void ganadorDeManoConTodasLasRondasEmpardadasDevuelveEquipoManoCuandoElManoEsEquipoUno(){
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ORO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ORO));

		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ORO));

		assertEquals(equipoUno,this.mesa.determinarGanadorDeMano().verEquipo());
	}

	@Test
	public void ganadorDeManoConTodasLasRondasEmpardadasDevuelveEquipoManoCuandoElManoEsEquipoDos(){
		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ORO));

		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ORO));

		this.mesa.jugarCarta(equipoDos, new Carta(TipoCarta.CINCO,Palo.ESPADA));
		this.mesa.jugarCarta(equipoUno, new Carta(TipoCarta.CINCO,Palo.ORO));

		assertEquals(equipoDos,this.mesa.determinarGanadorDeMano().verEquipo());
	}
}

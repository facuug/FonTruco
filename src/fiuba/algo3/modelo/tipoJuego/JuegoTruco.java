package fiuba.algo3.modelo.tipoJuego;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.modelo.CambiadorDeTurno;
import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.estados.EstadoJuego;
import fiuba.algo3.modelo.estados.EstadoSinCanto;
import fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.resultado.Resultado;

/**
 * Created by Facundo on 20-Nov-15.
 */
public abstract class JuegoTruco {

	protected EstadoJuego estadoJuego;

	private static final int JUGADORES_PICA_PICA = 6;
	protected Equipo equipoUno;
	protected Equipo equipoDos;

	private static final int PUNTOS_MINIMOS = 5;
	private static final int PUNTOS_MAXIMOS = 25;
	protected int puntosDeEnvido, puntosDeTruco, puntosDeMano;

	protected Mesa mesa;

	protected Equipo equipoDeTurno;
	protected CambiadorDeTurno turnoParaCanto;
	protected CambiadorDeTurno turnoParaCarta;

	protected Equipo equipoCantador;

	protected boolean finDeMano;

	private boolean envidoTerminado;

	private boolean manoPicaPica;
	private List<JuegoTruco> enfrentamientos;
	private JuegoTruco enfrentamientoActual;

	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}

	public JuegoTruco(Equipo unEquipo, Equipo otroEquipo) {
		this.puntosDeMano = 1; // si no hay cantos la mano vale 1 punto
		this.envidoTerminado = false;

		this.equipoUno = unEquipo;
		this.equipoDos = otroEquipo;

		this.mesa = new Mesa(this.equipoUno, this.equipoDos);

		this.equipoDeTurno = this.equipoUno;
		this.equipoCantador = new Equipo();

		this.estadoJuego = new EstadoSinCanto();

		this.turnoParaCanto = new CambiadorDeTurno(this.equipoUno, this.equipoDos);
		this.turnoParaCarta = new CambiadorDeTurno(this.equipoUno, this.equipoDos);
		this.turnoParaCarta.establecerJugadorDeTurno(this.mesa.equipoMano().jugadorDeTurno());
		
		this.manoPicaPica = false;
	}

	public void envido() {
		if (this.envidoTerminado)
			throw new CantoInvalidoException();
		
		this.estadoJuego = this.estadoJuego.envido();
		this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
		this.turnoParaCanto.rotarEquipoDeTurno();
	}

	public void noQuiero() {
		this.estadoJuego.noQuiero();
		this.equipoCantador.sumarPuntos(this.estadoJuego.cuantosPuntos());
		this.puntosDeMano = 1;
		this.finDeMano = this.estadoJuego.fueNoQuerido();
		this.envidoTerminado = true;
		this.estadoJuego = new EstadoSinCanto();
	}

	public int cuantosPuntos() {
		return this.estadoJuego.cuantosPuntos();
	}

	public void quiero() {
		this.estadoJuego.quiero();

		if (this.estadoJuego.esTruco())
			this.puntosDeTruco = this.estadoJuego.cuantosPuntos();
		else {
			this.puntosDeEnvido += this.estadoJuego.cuantosPuntos();
			this.estadoJuego = new EstadoSinCanto();
			this.envidoTerminado = true;
		}
	}

	public void realEnvido() {
		if (this.envidoTerminado)
			throw new CantoInvalidoException();

		this.estadoJuego = this.estadoJuego.realEnvido();
		this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
		this.turnoParaCanto.rotarEquipoDeTurno();
	}

	public void faltaEnvido() {
		if (this.envidoTerminado)
			throw new CantoInvalidoException();

		this.estadoJuego = this.estadoJuego.faltaEnvido(this.determinarGanadorDeEnvido().obtenerPuntos());
		this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
		this.turnoParaCanto.rotarEquipoDeTurno();
	}

	public void truco() {
		this.estadoJuego = this.estadoJuego.truco();
		this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
		this.turnoParaCanto.rotarEquipoDeTurno();
		this.puntosDeMano = 0;
	}

	public void reTruco() {
		if (this.equipoCantador.equals(this.turnoParaCanto.equipoDeTurno()))
			throw new CantoInvalidoException();

		this.estadoJuego = this.estadoJuego.reTruco();
		this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
		this.turnoParaCanto.rotarEquipoDeTurno();
	}

	public void valeCuatro() {
		if (this.equipoCantador.equals(this.turnoParaCanto.equipoDeTurno()))
			throw new CantoInvalidoException();

		this.estadoJuego = this.estadoJuego.valeCuatro();
		this.equipoCantador = this.turnoParaCanto.equipoDeTurno();
		this.turnoParaCanto.rotarEquipoDeTurno();
	}

	public int puntosEquipoUno() {
		return this.equipoUno.obtenerPuntos();
	}

	public int puntosEquipoDos() {
		return this.equipoDos.obtenerPuntos();
	}

	public void sumarPuntos() {
		this.determinarGanadorDeEnvido().sumarPuntos(this.puntosDeEnvido);
		this.determinarGanadorDeMano().sumarPuntos(this.puntosDeTruco + this.puntosDeMano);
	}

	public void jugadorDeTurnoJuegaCarta(Carta carta) {
		if ((!this.estadoJuego.fueRespondido()) || (this.manoFinalizada()))
			throw new AccionInvalidaException();

		Jugador jugadorDeTurno = this.turnoParaCarta.calcularJugadorDeTurno();
		this.turnoParaCarta.rotarJugador();
		this.turnoParaCarta.jugadorJuegaCarta(jugadorDeTurno, carta);
		mesa.jugarCarta(jugadorDeTurno.miEquipo(), carta);

		if (!this.turnoParaCarta.calcularJugadorDeTurno().miEquipo().equals(this.turnoParaCanto.equipoDeTurno()))
			this.turnoParaCanto.rotarEquipoDeTurno();
	}

	public boolean manoFinalizada() {
		return (this.mesa.manoFinalizada() || (this.finDeMano));
	}

	protected Resultado determinarGanadorDeMano() {
		return this.mesa.determinarGanadorDeMano();
	}

	protected Equipo determinarGanadorDeEnvido() {
		if (this.equipoUno.puntosDeEnvido() > this.equipoDos.puntosDeEnvido())
			return this.equipoUno;
		else if (this.equipoUno.puntosDeEnvido() < this.equipoDos.puntosDeEnvido())
			return this.equipoDos;
		else
			return this.mesa.equipoMano();
	}

	public abstract void flor();

	public abstract void contraFlorAlResto();

	public abstract void contraFlor();

	public Jugador jugadorDeTurno() {
		return this.turnoParaCarta.calcularJugadorDeTurno();
	}

	public Mesa obtenerMesa() {
		return this.mesa;
	}

	public void restablecer() {
		this.setTipoDeRondaProximaMano();
		this.estadoJuego = new EstadoSinCanto();
		this.puntosDeEnvido = 0;
		this.puntosDeTruco = 0;

		this.envidoTerminado = false;
		this.finDeMano = false;
		this.mesa.restablecer();
		this.turnoParaCarta.establecerJugadorDeTurno(this.mesa.equipoMano().jugadorDeTurno());
	}

	public boolean hayGanador() {
		return ((this.puntosEquipoUno() >= 30) || (this.puntosEquipoDos() >= 30));
	}

	public String ganadorDeJuego() {
		if (!hayGanador())
			return "Empate";
		else if (this.puntosEquipoUno() >= 30)
			return "Equipo 1";
		else
			return "Equipo 2";
	}

	private int cantidadDeJugadores() {

		return this.equipoUno.cantidadDeJugadores() + this.equipoDos.cantidadDeJugadores();
	}

	private boolean haySeisJugadores() {

		return (this.cantidadDeJugadores() == JUGADORES_PICA_PICA);
	}

	private boolean hayPuntosParaPicaPica() {

		int puntos = Math.max(this.puntosEquipoUno(), this.puntosEquipoDos());

		return ((puntos >= PUNTOS_MINIMOS) && (puntos <= PUNTOS_MAXIMOS));
	}

	public boolean esPicaPica() {

		return this.manoPicaPica;
	}
	
	public void setTipoDeRondaProximaMano() {
		
		this.manoPicaPica = ( !(manoPicaPica) && this.haySeisJugadores() && this.hayPuntosParaPicaPica() );
	}

	private Equipo crearEquipoPicaPica(Jugador jugador) {

		Equipo equipo = new Equipo();
		equipo.agregarJugador(jugador);

		return equipo;
	}

	public JuegoTruco getEnfrentamientoActual() {
		return enfrentamientoActual;
	}

	public void crearEnfrentamientosPicaPica() {
		if (enfrentamientos == null) {
			List<Jugador> jugadoresEquipoUno = this.equipoUno.getJugadores();
			List<Jugador> jugadoresEquipoDos = this.equipoDos.getJugadores();

			this.enfrentamientos = new ArrayList<JuegoTruco>();

			if (this instanceof TrucoConFlor) {

				this.enfrentamientos.add(new TrucoConFlor(crearEquipoPicaPica(jugadoresEquipoUno.get(0)),
						crearEquipoPicaPica(jugadoresEquipoDos.get(0))));
				this.enfrentamientos.add(new TrucoConFlor(crearEquipoPicaPica(jugadoresEquipoUno.get(1)),
						crearEquipoPicaPica(jugadoresEquipoDos.get(1))));
				this.enfrentamientos.add(new TrucoConFlor(crearEquipoPicaPica(jugadoresEquipoUno.get(2)),
						crearEquipoPicaPica(jugadoresEquipoDos.get(2))));
			} else {

				this.enfrentamientos.add(new TrucoSinFlor(crearEquipoPicaPica(jugadoresEquipoUno.get(0)),
						crearEquipoPicaPica(jugadoresEquipoDos.get(0))));
				this.enfrentamientos.add(new TrucoSinFlor(crearEquipoPicaPica(jugadoresEquipoUno.get(1)),
						crearEquipoPicaPica(jugadoresEquipoDos.get(1))));
				this.enfrentamientos.add(new TrucoSinFlor(crearEquipoPicaPica(jugadoresEquipoUno.get(2)),
						crearEquipoPicaPica(jugadoresEquipoDos.get(2))));
			}

			this.enfrentamientoActual = this.enfrentamientos.get(0);
		}
	}

	public void terminarEnfrentamiento() {
		if (enfrentamientoActual.manoFinalizada()) {
			enfrentamientoActual.sumarPuntos();
		}
		equipoUno.sumarPuntos(enfrentamientoActual.puntosEquipoUno());
		equipoDos.sumarPuntos(enfrentamientoActual.puntosEquipoDos());
		
	}
	
	public void cambiarEnfrentamiento() {
		try {
			int numeroEnfrentamiento = enfrentamientos.indexOf(enfrentamientoActual);
			enfrentamientoActual = enfrentamientos.get(numeroEnfrentamiento+1);
		} catch (Exception e) {
			enfrentamientos = null;
			crearEnfrentamientosPicaPica();
			setTipoDeRondaProximaMano();
			enfrentamientoActual = enfrentamientos.get(0);
		}
	}
}
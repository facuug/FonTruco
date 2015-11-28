package fiuba.algo3.modelo;

import fiuba.algo3.modelo.excepciones.NoHayMasCartasException;

public class Jugador {
	
	private Mano mano;
	private String nombre;
	private Equipo miEquipo;

	public Jugador(String nombre) {
		
		this.mano = new Mano();
		this.nombre = nombre;

	}

	public void asignarEquipo(Equipo unEquipo){
		this.miEquipo = unEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setMano(Mano mano) {
		this.mano = mano;
	}

	public Mano getMano() {
		return mano;
	}
	
	public void recibirCarta(Carta carta) {
		
		this.mano.recibirCarta(carta);
	}
	
	public Carta jugarCarta(int posicion) {
		if(this.cartasEnMano() == 0) throw new NoHayMasCartasException();
		
		return this.mano.sacarCarta(posicion);
	}
	
	public int cartasEnMano() {
		
		return this.mano.cantidadDeCartas();
	}

	public int puntosDeEnvido() {
		return this.mano.puntosDeEnvido();
	}

	public int puntosDeFlor() {
		return this.mano.puntosDeFlor();
	}

	public Equipo miEquipo() {
		return miEquipo;
	}

	public Boolean tieneFlor() {
		return this.mano.hayFlor();
	}
}
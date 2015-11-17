package fiuba.algo3.modelo;

public class Jugador {
	
	private Mano mano;
	private String nombre;

	public Jugador(String nombre) {
		
		this.mano = new Mano();
		this.nombre = nombre;
	}

	public void setMano(Mano mano) {
		this.mano = mano;
	}

	public Mano getMano() {
		return mano;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void recibirCarta(Carta carta) {
		
		this.mano.recibirCarta(carta);
	}
	
	public Carta jugarCarta(int posicion) {
		
		return this.mano.sacarCarta(posicion);
	}
	
	public int cartasEnMano() {
		
		return this.mano.cantidadDeCartas();
	}
}
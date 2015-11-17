package fiuba.algo3.modelo;

public class Jugador {
	
	private Mano mano;
	private String nombre;

	public Mano getMano() {
		return mano;
	}
	public void setMano(Mano mano) {
		this.mano = mano;
	}



	public Jugador(String nombre) {
		
		this.mano = new Mano();
		this.nombre = nombre;
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
}
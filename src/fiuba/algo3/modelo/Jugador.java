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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}
	
	
}

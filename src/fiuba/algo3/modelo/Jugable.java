package fiuba.algo3.modelo;

public interface Jugable {
	
	public Jugable contra(Jugable cartaJugable);
	
	public Jugable contra(AnchoDeEspada anchoDeEspada);
	
	public Jugable contra(AnchoDeBasto anchoDeBasto);
	
	public Jugable contra(SieteDeEspada sieteDeEspada);
	
	public Jugable contra(SieteDeOro sieteDeOro);
	
	public Jugable contra(Tres tres);
	
	public Jugable contra(Dos dos);
	
	public Jugable contra(AnchoFalso anchoFalso);
	
	public Jugable contra(Doce doce); 
	
	public Jugable contra(Once once);
	
	public Jugable contra(Diez diez);
	
	public Jugable contra(SieteFalso sieteFalso);
	
	public Jugable contra(Seis seis);
	
	public Jugable contra(Cinco cinco);
	
	public Jugable contra(Cuatro cuatro);
}
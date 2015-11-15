package fiuba.algo3.modelo.interfaces;

import fiuba.algo3.modelo.cartas.AnchoDeBasto;
import fiuba.algo3.modelo.cartas.AnchoDeEspada;
import fiuba.algo3.modelo.cartas.AnchoFalso;
import fiuba.algo3.modelo.cartas.Cinco;
import fiuba.algo3.modelo.cartas.Cuatro;
import fiuba.algo3.modelo.cartas.Diez;
import fiuba.algo3.modelo.cartas.Doce;
import fiuba.algo3.modelo.cartas.Dos;
import fiuba.algo3.modelo.cartas.Once;
import fiuba.algo3.modelo.cartas.Seis;
import fiuba.algo3.modelo.cartas.SieteDeEspada;
import fiuba.algo3.modelo.cartas.SieteDeOro;
import fiuba.algo3.modelo.cartas.SieteFalso;
import fiuba.algo3.modelo.cartas.Tres;

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
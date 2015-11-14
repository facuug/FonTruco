package fiuba.algo3.modelo;

public class Dos implements Jugable {

	@Override
	public Jugable contra(Jugable cartaJugable) {
		
		return cartaJugable.contra(this);
	}

	@Override
	public Jugable contra(AnchoDeEspada anchoDeEspada) {
		
		return anchoDeEspada;
	}

	@Override
	public Jugable contra(AnchoDeBasto anchoDeBasto) {
		
		return anchoDeBasto;
	}

	@Override
	public Jugable contra(SieteDeEspada sieteDeEspada) {
		
		return sieteDeEspada;
	}

	@Override
	public Jugable contra(SieteDeOro sieteDeOro) {
		
		return sieteDeOro;
	}

	@Override
	public Jugable contra(Tres tres) {
		
		return tres;
	}

	@Override
	public Jugable contra(Dos dos) {
		
		return dos;
	}

	@Override
	public Jugable contra(AnchoFalso anchoFalso) {
		
		return this;
	}

	@Override
	public Jugable contra(Doce doce) {
		
		return this;
	}

	@Override
	public Jugable contra(Once once) {
		
		return this;
	}

	@Override
	public Jugable contra(Diez diez) {
		
		return this;
	}

	@Override
	public Jugable contra(SieteFalso sieteFalso) {
		
		return this;
	}

	@Override
	public Jugable contra(Seis seis) {
		
		return this;
	}

	@Override
	public Jugable contra(Cinco cinco) {
		
		return this;
	}

	@Override
	public Jugable contra(Cuatro cuatro) {
		
		return this;
	}
}
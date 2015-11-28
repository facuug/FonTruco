package fiuba.algo3.modelo.enums;

public enum TipoCarta {
	ANCHO_ESPADA (14,1,1),
	ANCHO_BASTO (13,1,1),
	SIETE_ESPADA (12,7,7),
	SIETE_ORO (11,7,7),
	FALSO_ANCHO (8,1,1),
	FALSO_SIETE (4,7,1),
	TRES (10,3,3),
	DOS (9,2,2),
	REY (7,0,12),
	CABALLO (6,0,11),
	SOTA (5,0,10),
	SEIS (3,6,6),
	CINCO (2,5,5),
	CUATRO (1,4,4),
	INVALIDO(0,0,0);
	
	private final int valor;
	private final int valorEnvido;
	private final int valorRealCarta;
	
	public int getValorRealCarta() {
		return valorRealCarta;
	}

	TipoCarta(int valor, int valorEnvido, int valorRealCarta){
		this.valor = valor;
		this.valorEnvido = valorEnvido;
		this.valorRealCarta = valorRealCarta;
	}
	
	public int getValor() {
		return valor;
	}
	
	public int getValorEnvido() {
		return valorEnvido;
	}

	public int comparar(TipoCarta tipoCarta) {	
		if(this.valor < tipoCarta.valor) {
			return -1;
		} else if (this.valor > tipoCarta.valor) {
			return 1;
		} else {
			return 0;
		}
	}
}

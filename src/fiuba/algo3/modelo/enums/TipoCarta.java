package fiuba.algo3.modelo.enums;

public enum TipoCarta {
	ANCHO_ESPADA (14,1),
	ANCHO_BASTO (13,1),
	SIETE_ESPADA (12,7),
	SIETE_ORO (11,7),
	FALSO_ANCHO (8,1),
	FALSO_SIETE (4,7),
	TRES (10,3),
	DOS (9,2),
	REY (7,0),
	CABALLO (6,0),
	SOTA (5,0),
	SEIS (3,6),
	CINCO (2,5),
	CUATRO (1,4),
	INVALIDO(0,0);
	
	private final int valor;
	private final int valorEnvido;
	
	TipoCarta(int valor, int valorEnvido){
		this.valor = valor;
		this.valorEnvido = valorEnvido;
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

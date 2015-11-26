package fiuba.algo3.modelo;

import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;

public class Carta {

    private TipoCarta tipoCarta;
    private Palo palo;

    public Carta(TipoCarta tipoCarta, Palo palo) {
        this.tipoCarta = tipoCarta;
        this.palo = palo;
    }

    public TipoCarta getTipoCarta() {
        return this.tipoCarta;
    }

    public Palo getPalo() {
        return this.palo;
    }
    
    public int comparar(Carta otraCarta){
    	return this.tipoCarta.comparar(otraCarta.tipoCarta);
    }

    public int valorDeEnvido() {
        return this.tipoCarta.getValorEnvido();
    }
}

package fiuba.algo3.modelo;

import java.util.Random;

/**
 * Created by Facundo on 07-Nov-15.
 */
public class Carta {

    public enum Palo { ESPADA, BASTO, ORO, COPA }

    private int valor;
    private Palo palo;

    public Carta(int unValor, Palo unTipo) {
        if ( unValor <= 0 ) throw new CartaInvalidaException();

        this.valor = unValor;
        this.palo = unTipo;
    }

    //crea una carta aleatoria
    public Carta(){
        Random rnd = new Random();

        int maximoValorDeCarta = 12;
        int minimoValorDeCarta = 1;

        this.valor = (int) (rnd.nextDouble() * maximoValorDeCarta + minimoValorDeCarta);

        int maximoValorDePalo = 3;

        int paloElegido = (int) (rnd.nextDouble() * maximoValorDePalo);

        this.palo = Palo.values()[paloElegido];
    }

    public int getValor() {
        return this.valor;
    }

    public Palo getPalo() {
        return this.palo;
    }

}

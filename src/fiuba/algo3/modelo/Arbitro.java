package fiuba.algo3.modelo;

import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.excepciones.CartaInvalidaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Facundo on 09-Nov-15.
 */
public class Arbitro {

    private List<String> jerarquiaDeCartas;

    public Arbitro(){
        jerarquiaDeCartas = new ArrayList<String>();

        jerarquiaDeCartas.add("1 de espada");
        jerarquiaDeCartas.add("1 de basto");

        jerarquiaDeCartas.add("7 de espada");
        jerarquiaDeCartas.add("7 de oro");

        jerarquiaDeCartas.add("3 de copa"); jerarquiaDeCartas.add("3 de oro"); jerarquiaDeCartas.add("3 de basto"); jerarquiaDeCartas.add("3 de espada");
        jerarquiaDeCartas.add("2 de copa");  jerarquiaDeCartas.add("2 de oro");  jerarquiaDeCartas.add("2 de basto");  jerarquiaDeCartas.add("2 de espada");

        jerarquiaDeCartas.add("1 de copa"); jerarquiaDeCartas.add("1 de oro");

        jerarquiaDeCartas.add("12 de copa"); jerarquiaDeCartas.add("12 de oro"); jerarquiaDeCartas.add("12 de basto"); jerarquiaDeCartas.add("12 de espada");
        jerarquiaDeCartas.add("11 de copa"); jerarquiaDeCartas.add("11 de oro"); jerarquiaDeCartas.add("11 de basto"); jerarquiaDeCartas.add("11 de espada");
        jerarquiaDeCartas.add("10 de copa");  jerarquiaDeCartas.add("10 de oro");  jerarquiaDeCartas.add("10 de basto");  jerarquiaDeCartas.add("10 de espada");

        jerarquiaDeCartas.add("7 de copa");  jerarquiaDeCartas.add("7 de basto");

        jerarquiaDeCartas.add("6 de copa");  jerarquiaDeCartas.add("6 de oro");  jerarquiaDeCartas.add("6 de basto");  jerarquiaDeCartas.add("6 de espada");
        jerarquiaDeCartas.add("5 de copa");  jerarquiaDeCartas.add("5 de oro");  jerarquiaDeCartas.add("5 de basto");  jerarquiaDeCartas.add("5 de espada");
        jerarquiaDeCartas.add("4 de copa"); jerarquiaDeCartas.add("4 de oro"); jerarquiaDeCartas.add("4 de basto"); jerarquiaDeCartas.add("4 de espada");
    }

    public Carta ganadorEntre(Carta carta, Carta otraCarta) {
        if( ( !jerarquiaDeCartas.contains(carta.getNombre()) ) || ( !jerarquiaDeCartas.contains(otraCarta.getNombre()) ) ) throw new CartaInvalidaException();

        int valorDeCarta = jerarquiaDeCartas.indexOf(carta.getNombre());
        int valorDeOtraCarta = jerarquiaDeCartas.indexOf(otraCarta.getNombre());

        if ( valorDeCarta < valorDeOtraCarta ) return carta;
        else{
            if ( valorDeCarta > valorDeOtraCarta ) return otraCarta;
            else return null;
        }
    }
}

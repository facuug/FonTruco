package fiuba.algo3.modelo.test;

import org.junit.Test;
import static org.junit.Assert.*;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Carta.Palo;
import fiuba.algo3.modelo.CartaInvalidaException;


/**
 * Created by Facundo on 07-Nov-15.
 */
public class CartaTest {

    @Test
    public void creacionDeCartaExitosa() {
        Carta anchoEspada = new Carta( 1, Palo.ESPADA );
        assertTrue( anchoEspada.getValor() == 1 );
        assertTrue( anchoEspada.getPalo() == Palo.ESPADA );
    }

    @Test
    public void getValorDevuelveValorDeCarta(){
        Carta carta = new Carta( 3, Palo.BASTO );
        assertTrue( carta.getValor() == 3 );
    }

    @Test
    public void getPaloDevuelveTipoDePaloDeCarta(){
        Carta carta = new Carta( 3, Palo.BASTO );
        assertTrue( carta.getPalo() == Palo.BASTO );
    }

    @Test (expected = CartaInvalidaException.class)
    public void cartaInvalidaNoSeCrea(){
        new Carta( 0, Palo.ESPADA );
    }

}

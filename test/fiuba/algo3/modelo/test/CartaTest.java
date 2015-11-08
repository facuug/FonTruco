package fiuba.algo3.modelo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fiuba.algo3.enums.Palo;
import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.excepciones.CartaInvalidaException;

public class CartaTest {

    @Test
    public void creacionDeCartaExitosa() {
        Carta anchoEspada = new Carta( 1, Palo.ESPADA );
        assertEquals(1, anchoEspada.getValor());
        assertEquals(Palo.ESPADA, anchoEspada.getPalo());
    }

    @Test
    public void getValorDevuelveValorDeCarta(){
        Carta carta = new Carta( 3, Palo.BASTO );
        assertEquals(3, carta.getValor());
    }

    @Test
    public void getPaloDevuelveTipoDePaloDeCarta(){
        Carta carta = new Carta( 3, Palo.BASTO );
        assertEquals(Palo.BASTO, carta.getPalo());
    }

    @Test (expected = CartaInvalidaException.class)
    public void cartaInvalidaNoSeCrea(){
        new Carta( 0, Palo.ESPADA );
    }

}

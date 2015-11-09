package fiuba.algo3.modelo.test;

import static org.junit.Assert.*;

import fiuba.algo3.modelo.Arbitro;
import fiuba.algo3.modelo.Carta;

import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.excepciones.CartaInvalidaException;
import org.junit.Test;

/**
 * Created by Facundo on 09-Nov-15.
 */
public class ArbitroTest {

    @Test
    public void ganadorEntreDevuelveCartaMasAlta(){
        Carta tresDeOro = new Carta(3, Palo.ORO);
        Carta sieteDeEspada = new Carta(7, Palo.ESPADA);

        Arbitro arbitro = new Arbitro();
        assertEquals( sieteDeEspada, arbitro.ganadorEntre(tresDeOro,sieteDeEspada) );
    }

    @Test ( expected = CartaInvalidaException.class)
    public void cartaInvalidaDevuelveExcepcion(){
        Carta cartaInvalida = new Carta(8, Palo.BASTO);
        Carta sieteDeEspada = new Carta(7, Palo.ESPADA);

        Arbitro arbitro = new Arbitro();
        arbitro.ganadorEntre(cartaInvalida,sieteDeEspada);
    }

    @Test
    public void siLasCartasSonIgualesDevuelveNull(){
        Carta sieteDeEspada = new Carta(7, Palo.ESPADA);

        Arbitro arbitro = new Arbitro();
        assertEquals( null, arbitro.ganadorEntre(sieteDeEspada,sieteDeEspada) );
    }
}

package fiuba.algo3.modelo.test;

import static org.junit.Assert.*;

import fiuba.algo3.modelo.Carta;
import fiuba.algo3.modelo.Mesa;
import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.excepciones.MesaLlenaException;
import org.junit.Test;

/**
 * Created by Facundo on 08-Nov-15.
 */
public class MesaTest {

    @Test
    public void ganadorDevuelveElGanadorDeLaMano(){
        Carta anchoDeBasto = new Carta(1, Palo.BASTO);
        Carta tresDeOro = new Carta(3, Palo.ORO);
        Carta dosDeCopa = new Carta(2, Palo.COPA);
        Carta seisDeOro = new Carta(6, Palo.ORO);

        Mesa mesa = new Mesa();

        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(dosDeCopa);

        mesa.jugarCarta(seisDeOro);
        mesa.jugarCarta(tresDeOro);

        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(dosDeCopa);

        assertEquals( Ganador.EquipoUno, mesa.determinarGanador() );
    }

    @Test
    public void siUnEquipoGanaDosRondasSeguidasGanaLaMano(){
        Carta anchoDeBasto = new Carta(1, Palo.BASTO);
        Carta tresDeOro = new Carta(3, Palo.ORO);
        Carta dosDeCopa = new Carta(2, Palo.COPA);
        Carta seisDeOro = new Carta(6, Palo.ORO);

        Mesa mesa = new Mesa();

        mesa.jugarCarta(dosDeCopa);
        mesa.jugarCarta(anchoDeBasto);

        mesa.jugarCarta(seisDeOro);
        mesa.jugarCarta(tresDeOro);

        assertEquals( Ganador.EquipoDos, mesa.determinarGanador() );
    }

    @Test ( expected = MesaLlenaException.class)
    public void mesaNoPuedeTenerMasDeSeisCartas(){
        Carta anchoDeBasto = new Carta(1, Palo.BASTO);

        Mesa mesa = new Mesa();
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
    }

    @Test
    public void limpiarMesaBorraLasCartas(){
        Carta anchoDeBasto = new Carta(1, Palo.BASTO);

        Mesa mesa = new Mesa();
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);
        mesa.jugarCarta(anchoDeBasto);

        mesa.limpiar();
        assertEquals(mesa.cartasEnMesa(), 0);
    }
}

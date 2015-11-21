package fiuba.algo3.modelo.test;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.Turno;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Facundo on 20-Nov-15.
 */
public class TurnoTest {

    private Turno turno;
    Equipo equipoUno;
    Equipo equipoDos;

    @Before
    public void setup(){

        equipoUno = new Equipo();
        equipoUno.agregarJugador(new Jugador("Facu"));
        equipoUno.agregarJugador(new Jugador("Agus"));

        equipoDos = new Equipo();
        equipoDos.agregarJugador(new Jugador("Homero"));
        equipoDos.agregarJugador(new Jugador("Lisa"));

         this.turno = new Turno(equipoUno,equipoDos);
    }

    @Test
    public void rotarCambiaElEquipoDeTurno(){
        Assert.assertEquals(this.equipoUno,this.turno.equipoDeTurno());
        this.turno.rotar();
        Assert.assertEquals(this.equipoDos,this.turno.equipoDeTurno());
    }
}

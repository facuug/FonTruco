package fiuba.algo3.modelo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Facundo on 20-Nov-15.
 */
public class Turno {

    private List<Equipo> equipos;
    private Equipo equipoDeTurno;
    private int posicion;

    public Turno(Equipo unEquipo, Equipo otroEquipo) {
        equipos = Arrays.asList(unEquipo,otroEquipo);
        this.equipoDeTurno = unEquipo;
        this.posicion = 1;
    }

    public void rotar() {
        try{
            this.equipoDeTurno = this.equipos.get(this.posicion++);
        } catch (Exception exception){
            this.posicion = 0;
            this.rotar();
        }
    }

    public Equipo equipoDeTurno() {
        return this.equipoDeTurno;
    }
}

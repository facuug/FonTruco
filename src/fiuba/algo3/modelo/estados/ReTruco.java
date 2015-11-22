package fiuba.algo3.modelo.estados;


import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.interfaces.EstadoJuego;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class ReTruco implements EstadoJuego {

    private int puntos = 0;

    @Override
    public void noQuiero() {
        this.puntos = 2;
    }

    @Override
    public void quiero() {
        this.puntos = 3;
    }

    @Override
    public int cuantosPuntos() {
        return this.puntos;
    }

    @Override
    public EstadoJuego truco() {
        throw new CantoInvalidoException();
    }

    @Override
    public EstadoJuego reTruco() {
        throw new CantoInvalidoException();
    }

    @Override
    public EstadoJuego valeCuatro() {
        return new ValeCuatro();
    }

    @Override
    public EstadoJuego envido() {
        throw new CantoInvalidoException();
    }

    @Override
    public EstadoJuego realEnvido() {
        throw new CantoInvalidoException();
    }

    @Override
    public EstadoJuego faltaEnvido() {
        throw new CantoInvalidoException();
    }
}

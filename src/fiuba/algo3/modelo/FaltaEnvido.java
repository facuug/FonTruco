package fiuba.algo3.modelo;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.interfaces.EstadoJuego;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class FaltaEnvido implements EstadoJuego {

    private int puntos = 0;

    public FaltaEnvido(int puntosAcumulados) {
        this.puntos = puntosAcumulados;
    }

    @Override
    public void noQuiero() {
        if(this.puntos == 0) this.puntos = 1;
    }

    public void quiero(){
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
        throw new CantoInvalidoException();
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

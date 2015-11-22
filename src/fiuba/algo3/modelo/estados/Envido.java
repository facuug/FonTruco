package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.interfaces.EstadoJuego;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class Envido implements EstadoJuego {

    private int puntos = 0;

    public Envido(int puntosAcumulados){
        this.puntos = puntosAcumulados;
    }

    public void noQuiero() {
        if(this.puntos == 0) this.puntos = 1;
    }

    public void quiero(){
        this.puntos += 2;
    }

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
        return new Envido(2);
    }

    @Override
    public EstadoJuego realEnvido() {
        return new RealEnvido(this.puntos + 2);
    }

    @Override
    public EstadoJuego faltaEnvido() {
        return new FaltaEnvido(this.puntos + 2);
    }
}

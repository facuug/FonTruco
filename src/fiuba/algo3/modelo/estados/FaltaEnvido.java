package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class FaltaEnvido implements EstadoJuego {

    private int puntos = 0;
    private Boolean fueRespondido;

    public FaltaEnvido(int puntosAcumulados) {
        this.puntos = puntosAcumulados;
        this.fueRespondido = false;
    }

    @Override
    public void noQuiero() {
        if(this.fueRespondido()) throw new CantoInvalidoException();

        if(this.puntos == 0) this.puntos = 1;
        this.fueRespondido = true;
    }

    public void quiero(){
        if(this.fueRespondido()) throw new CantoInvalidoException();

        this.fueRespondido = true;
        if(this.puntos < 15) this.puntos = 15 - this.puntos;
        else this.puntos = 30 - this.puntos;
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
    public EstadoJuego faltaEnvido(int puntosActuales) {
        throw new CantoInvalidoException();
    }

    @Override
    public Boolean fueRespondido() {
        return this.fueRespondido;
    }

    @Override
    public Boolean fueNoQuerido() {
        return false;
    }

    @Override
    public EstadoJuego flor() {
        throw new CantoInvalidoException();
    }

    @Override
    public EstadoJuego contraFlor() {
        throw new CantoInvalidoException();
    }

    @Override
    public EstadoJuego contraFlorAlResto(int puntosActuales) {
        throw new CantoInvalidoException();
    }

    @Override
    public boolean esTruco() {
        return false;
    }

    @Override
    public boolean esEnvido() {
        return true;
    }
}

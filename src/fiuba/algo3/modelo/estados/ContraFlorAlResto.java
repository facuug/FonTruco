package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 29-Nov-15.
 */
public class ContraFlorAlResto implements EstadoJuego {

    private int puntos;
    private boolean fueRespondido;

    public ContraFlorAlResto(int puntosAcumulados) {
        this.puntos = puntosAcumulados;
    }

    @Override
    public void noQuiero() {
        if(this.fueRespondido()) throw new CantoInvalidoException();

        this.puntos += 0;
        this.fueRespondido = true;
    }

    @Override
    public void quiero() {
        if(this.fueRespondido()) throw new CantoInvalidoException();

        this.puntos = 33 - this.puntos;
        this.fueRespondido = true;
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
        throw new CantoInvalidoException();
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
        return false;
    }
}

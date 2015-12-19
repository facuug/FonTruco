package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 29-Nov-15.
 */
public class ContraFlor implements EstadoJuego {

    private int puntos;
    private boolean fueRespondido;

    public ContraFlor(int puntosAcumulados){
        this.puntos = puntosAcumulados;
        this.fueRespondido = false;
    }

    @Override
    public void noQuiero() {
        if(this.fueRespondido()) throw new CantoInvalidoException();

        this.fueRespondido = true;
        this.puntos += 0;
    }

    @Override
    public void quiero() {
        if(this.fueRespondido()) throw new CantoInvalidoException();

        this.fueRespondido = true;
        this.puntos += 3;
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
        return null;
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

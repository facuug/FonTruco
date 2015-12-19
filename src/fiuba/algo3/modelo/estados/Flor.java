package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 25-Nov-15.
 */
public class Flor implements EstadoJuego {
    private int puntos;

    public Flor(int puntosAcumulados) {
        this.puntos = puntosAcumulados;
    }

    @Override
    public void noQuiero() {
        throw new CantoInvalidoException();
    }

    @Override
    public void quiero() {
    }

    @Override
    public int cuantosPuntos() {
        return this.puntos;
    }

    @Override
    public EstadoJuego truco() {
        return new Truco();
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
        return true;
    }

    @Override
    public Boolean fueNoQuerido() {
        return null;
    }

    @Override
    public EstadoJuego flor() {
        if(this.cuantosPuntos() == 6) throw new CantoInvalidoException();

        return new Flor(6);
    }

    @Override
    public EstadoJuego contraFlor() {
        return new ContraFlor(this.puntos);
    }

    @Override
    public EstadoJuego contraFlorAlResto(int puntosActuales) {
        return new ContraFlorAlResto(puntosActuales + this.puntos);
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

package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class EstadoSinCanto implements EstadoJuego {

    @Override
    public void noQuiero() {
    }

    @Override
    public void quiero() {
    }

    @Override
    public int cuantosPuntos() {
        return 0;
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
        return new Envido(0);
    }

    @Override
    public EstadoJuego realEnvido() {
        return new RealEnvido(0);
    }

    @Override
    public EstadoJuego faltaEnvido(int puntosActuales) {
        return new FaltaEnvido(puntosActuales);
    }

    @Override
    public Boolean fueRespondido() {
        return true;
    }

    @Override
    public Boolean fueNoQuerido() {
        return false;
    }

    @Override
    public EstadoJuego flor() {
        return new Flor(3);
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

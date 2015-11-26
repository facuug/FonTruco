package fiuba.algo3.modelo;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;
import fiuba.algo3.modelo.interfaces.EstadoJuego;

/**
 * Created by Facundo on 25-Nov-15.
 */
public class Flor implements EstadoJuego {
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
        return null;
    }

    @Override
    public Boolean fueNoQuerido() {
        return null;
    }

    @Override
    public EstadoJuego flor() {
        throw new CantoInvalidoException();
    }
}

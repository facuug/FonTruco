package fiuba.algo3.modelo.estados;


import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class Truco implements EstadoJuego {

    private int puntos = 0;
    private Boolean fueRespondido = false;
    private boolean fueNoQuerido;

    @Override
    public void noQuiero() {
        if(this.fueRespondido()) throw new CantoInvalidoException();

        this.puntos = 1;
        this.fueRespondido = true;
        this.fueNoQuerido = true;
    }

    @Override
    public void quiero() {
        if(this.fueRespondido()) throw new CantoInvalidoException();

        this.puntos = 2;
        this.fueRespondido = true;
        this.fueNoQuerido = false;
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
        return new ReTruco();
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
        return this.fueNoQuerido;
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
        return true;
    }

    @Override
    public boolean esEnvido() {
        return false;
    }
}

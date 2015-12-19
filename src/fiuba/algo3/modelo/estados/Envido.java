package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class Envido implements EstadoJuego {

    private int puntos = 0;
    private Boolean cantoRespondido;

    public Envido(int puntosAcumulados){
        this.puntos = puntosAcumulados;
        this.cantoRespondido = false;
    }

    public void noQuiero() {
        if(this.fueRespondido()) throw new CantoInvalidoException();

        if(this.puntos == 0) this.puntos = 1;
        this.cantoRespondido = true;
    }

    public void quiero(){
        if(this.fueRespondido()) throw new CantoInvalidoException();

        this.puntos += 2;
        this.cantoRespondido = true;
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
    public EstadoJuego faltaEnvido(int puntosActuales) {
        return new FaltaEnvido(this.puntos + 2);
    }

    @Override
    public Boolean fueRespondido() {
        return this.cantoRespondido;
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

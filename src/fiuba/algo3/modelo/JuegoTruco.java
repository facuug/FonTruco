package fiuba.algo3.modelo;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 11-Nov-15.
 */
public class JuegoTruco {

    private EstadoJuego estadoEnvido;
    private int puntosAcumulados;

    public JuegoTruco(){
        this.estadoEnvido = new EstadoSinCanto();
    }

    public void envido() {
        estadoEnvido.quiero(); // si hubo un envido previo, se debe aceptar
        estadoEnvido = new Envido(this.cuantosPuntos());
    }

    public void noQuiero() {
        estadoEnvido.noQuiero();
    }

    public int cuantosPuntos() {
        return this.estadoEnvido.cuantosPuntos();
    }

    public void quiero(){
        this.estadoEnvido.quiero();
    }

    public void realEnvido() {
        this.estadoEnvido.quiero();
        estadoEnvido = new RealEnvido(this.cuantosPuntos());
    }

    public void faltaEnvido() {
        this.estadoEnvido.quiero();
        estadoEnvido = new FaltaEnvido(this.cuantosPuntos());
    }
}

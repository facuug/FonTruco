package fiuba.algo3.modelo;

import fiuba.algo3.modelo.estados.Envido;
import fiuba.algo3.modelo.estados.EstadoJuego;
import fiuba.algo3.modelo.estados.EstadoSinCanto;
import fiuba.algo3.modelo.estados.FaltaEnvido;
import fiuba.algo3.modelo.estados.ReTruco;
import fiuba.algo3.modelo.estados.RealEnvido;
import fiuba.algo3.modelo.estados.Truco;
import fiuba.algo3.modelo.estados.ValeCuatro;

/**
 * Created by Facundo on 11-Nov-15.
 */
public class JuegoTruco {

    private EstadoJuego estadoDeJuego;

    public JuegoTruco(){
        this.estadoDeJuego = new EstadoSinCanto();
    }

    public void envido() {
        estadoDeJuego.quiero(); // si hubo un envido previo, se debe aceptar
        estadoDeJuego = new Envido(this.cuantosPuntos());
    }

    public void noQuiero() {
        estadoDeJuego.noQuiero();
    }

    public int cuantosPuntos() {
        return this.estadoDeJuego.cuantosPuntos();
    }

    public void quiero(){
        this.estadoDeJuego.quiero();
    }

    public void realEnvido() {
        this.estadoDeJuego.quiero();
        estadoDeJuego = new RealEnvido(this.cuantosPuntos());
    }

    public void faltaEnvido() {
        this.estadoDeJuego.quiero();
        estadoDeJuego = new FaltaEnvido(this.cuantosPuntos());
    }

    public void truco() {
        this.estadoDeJuego = new Truco();
    }

    public void reTruco(){
        this.estadoDeJuego = new ReTruco();
    }

    public void valeCuatro() {
        this.estadoDeJuego = new ValeCuatro();
    }
}

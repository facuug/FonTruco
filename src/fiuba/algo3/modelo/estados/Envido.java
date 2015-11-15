package fiuba.algo3.modelo.estados;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class Envido extends EstadoJuego {


    public Envido(int puntosAcumulados){
        this.puntos = puntosAcumulados;
    }

    public void noQuiero() {
        if(this.puntos == 0) this.puntos = 1;
    }

    public void quiero(){
        this.puntos += 2;
    }

    public int cuantosPuntos() {
        return this.puntos;
    }
}

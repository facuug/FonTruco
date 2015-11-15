package fiuba.algo3.modelo.estados;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class FaltaEnvido extends EstadoJuego {

    public FaltaEnvido(int puntosAcumulados) {
        this.puntos = puntosAcumulados;
    }

    @Override
    public void noQuiero() {
        if(this.puntos == 0) this.puntos = 1;
    }

    public void quiero(){
    }

    @Override
    public int cuantosPuntos() {
        return this.puntos;
    }
}

package fiuba.algo3.modelo.estados;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class Truco extends EstadoJuego {

    @Override
    public void noQuiero() {
        this.puntos = 1;
    }

    @Override
    public void quiero() {
        this.puntos = 2;
    }

    @Override
    public int cuantosPuntos() {
        return this.puntos;
    }
}

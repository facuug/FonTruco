package fiuba.algo3.modelo;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class ReTruco extends EstadoJuego {
    @Override
    public void noQuiero() {
        this.puntos = 2;
    }

    @Override
    public void quiero() {
        this.puntos = 3;
    }

    @Override
    public int cuantosPuntos() {
        return this.puntos;
    }
}

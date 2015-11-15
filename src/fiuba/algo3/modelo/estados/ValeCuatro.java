package fiuba.algo3.modelo.estados;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class ValeCuatro extends EstadoJuego {
    @Override
    public void noQuiero() {
        this.puntos = 3;
    }

    @Override
    public void quiero() {
        this.puntos = 4;
    }

    @Override
    public int cuantosPuntos() {
        return this.puntos;
    }
}

package fiuba.algo3.modelo;

/**
 * Created by Facundo on 11-Nov-15.
 */
public  abstract class EstadoJuego {
    protected int puntos;

    public abstract void noQuiero();

    public abstract void quiero();

    public abstract int cuantosPuntos();

    public abstract void envido();
}

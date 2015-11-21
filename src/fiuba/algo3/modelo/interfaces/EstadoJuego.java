package fiuba.algo3.modelo.interfaces;

/**
 * Created by Facundo on 11-Nov-15.
 */
public interface  EstadoJuego {

    public void noQuiero();

    public  void quiero();

    public int cuantosPuntos();

    public EstadoJuego truco();

    public EstadoJuego reTruco();

    public EstadoJuego valeCuatro();

    public EstadoJuego envido();

    public EstadoJuego realEnvido();

    public EstadoJuego faltaEnvido();
}

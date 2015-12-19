package fiuba.algo3.modelo.estados;

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

    public EstadoJuego faltaEnvido(int puntosActuales);

    public Boolean fueRespondido();

    public Boolean fueNoQuerido();

    public EstadoJuego flor();

    public EstadoJuego contraFlor();

    EstadoJuego contraFlorAlResto(int puntosActuales);

    public boolean esTruco();

    public boolean esEnvido();
}

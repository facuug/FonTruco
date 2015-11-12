package fiuba.algo3.modelo;

/**
 * Created by Facundo on 11-Nov-15.
 */
public class JuegoTruco {

    private EstadoJuego estadoEnvido;

    public void envido() {
        if ( estadoEnvido != null ) estadoEnvido.envido(); //si se canto envido
        else estadoEnvido = new Envido();
    }

    public void noQuiero() {
        estadoEnvido.noQuiero();
    }

    public int cuantosPuntos() {
        if (estadoEnvido == null) return 0;
        return estadoEnvido.cuantosPuntos();
    }

    public void quiero() {
        estadoEnvido.quiero();
    }

    public void realEnvido() {
        Boolean hayCantoPrevio = (estadoEnvido != null);
        estadoEnvido = new RealEnvido(this.cuantosPuntos(),hayCantoPrevio);
    }

    public void faltaEnvido() {
        Boolean hayCantoPrevio = (estadoEnvido != null);
        estadoEnvido = new FaltaEnvido(this.cuantosPuntos(),hayCantoPrevio);
    }
}

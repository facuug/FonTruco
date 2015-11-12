package fiuba.algo3.modelo;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class FaltaEnvido extends EstadoJuego {

    private int puntosAcumulados;
    private Boolean hayCantoPrevio;

    public FaltaEnvido(int puntos, Boolean huboCantoPrevio) {
        super();
        this.puntosAcumulados = puntos;
        this.hayCantoPrevio = huboCantoPrevio;
    }

    @Override
    public void noQuiero() {
        if(!this.hayCantoPrevio) this.puntosAcumulados += 1;
        else this.puntosAcumulados += 2;
    }

    @Override
    public void quiero() {
    }

    @Override
    public int cuantosPuntos() {
        return this.puntosAcumulados;
    }

    @Override
    public void envido() {}
}

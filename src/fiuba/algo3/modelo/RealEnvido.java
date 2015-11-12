package fiuba.algo3.modelo;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class RealEnvido extends EstadoJuego {

    int puntosAcumulados;
    Boolean hayCantoPrevio;

    public RealEnvido(int puntos, Boolean huboCantoPrevio) {
        super();
        this.puntosAcumulados = puntos;
        this.hayCantoPrevio = huboCantoPrevio;
    }

    @Override
    public void noQuiero() {
        if(!hayCantoPrevio) this.puntosAcumulados += 1;
        else this.puntosAcumulados += 2;
    }

    @Override
    public void quiero() {
        if(!this.hayCantoPrevio) this.puntosAcumulados += 3;
        else this.puntosAcumulados += 5;
    }

    @Override
    public int cuantosPuntos() {
        return this.puntosAcumulados;
    }

    @Override
    public void envido() {}
}

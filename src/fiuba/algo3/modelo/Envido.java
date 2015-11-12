package fiuba.algo3.modelo;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 12-Nov-15.
 */
public class Envido extends EstadoJuego {

    private boolean fueCantado = false;

    public void noQuiero() {
        if(!this.fueCantado) this.puntos += 1;
    }

    public void quiero() {
        this.puntos += 2;
    }

    public int cuantosPuntos() {
        return this.puntos;
    }

    @Override
    public void envido() {
        if(this.fueCantado) throw new CantoInvalidoException();

        this.fueCantado = true;
        this.puntos += 2;
    }
}

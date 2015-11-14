package fiuba.algo3.modelo;

import fiuba.algo3.modelo.excepciones.CantoInvalidoException;

/**
 * Created by Facundo on 14-Nov-15.
 */
public class EstadoSinCanto extends EstadoJuego {

    @Override
    public void noQuiero() {
        //mas adelante es metodo puede lanzar excepcion
    }

    @Override
    public void quiero() {

    }

    @Override
    public int cuantosPuntos() {
        return 0;
    }
}

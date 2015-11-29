package fiuba.algo3.modelo;

import fiuba.algo3.modelo.interfaces.Resultado;
import fiuba.algo3.modelo.interfaces.Ronda;

/**
 * Created by Facundo on 28-Nov-15.
 */
public class PrimeraEmparda implements Resultado {

    private Equipo equipoMano;

    public PrimeraEmparda(Equipo equipo) {
        this.equipoMano = equipo;
    }

    @Override
    public Equipo verEquipo() {
        return this.equipoMano;
    }

    @Override
    public Ronda calcularRondaSiguiente(Ronda unaRonda) {
        return new RondaFinal(unaRonda);
    }

    @Override
    public String situacion() {
        return "empardada";
    }

    @Override
    public void sumarPuntos(int i) {

    }
}

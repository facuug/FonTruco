package fiuba.algo3.modelo.resultado;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.rondas.Ronda;
import fiuba.algo3.modelo.rondas.RondaFinal;

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

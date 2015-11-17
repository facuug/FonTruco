package fiuba.algo3.modelo;

import fiuba.algo3.modelo.enums.Ganador;
import fiuba.algo3.modelo.excepciones.MesaLlenaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Facundo on 08-Nov-15.
 */
public class Mesa {
	
	private Mazo mazo;
    private List<Carta> cartasJugadas;
    private Arbitro arbitro;

    private int rondasEquipoUno;
    private int rondasEquipoDos;
    private int jugadoresEnMesa;

    public Mesa(int cantidadDeJugadores){
    	
    	this.mazo = new Mazo();
        this.cartasJugadas = new ArrayList<Carta>();
        this.arbitro = new Arbitro();
        this.jugadoresEnMesa = cantidadDeJugadores;
    }

    public void jugarCarta(Carta unaCarta) {
        if (this.mesaLlena()) throw new MesaLlenaException();

        this.cartasJugadas.add(unaCarta);
    }

    public boolean mesaLlena() {
        return ( this.cartasEnMesa() == 3 * this.jugadoresEnMesa );
    }

    public Ganador determinarGanador() {

        Carta cartaJugadorUno;
        Carta cartaJugadorDos;
        Carta cartaGanadora;

        for(int posicion = 0; posicion < this.cartasJugadas.size(); posicion += 2){
            cartaJugadorUno = this.cartasJugadas.get(posicion);
            cartaJugadorDos = this.cartasJugadas.get(posicion + 1);

            cartaGanadora = arbitro.ganadorEntre(cartaJugadorUno, cartaJugadorDos);

            if ( cartaGanadora.equals(cartaJugadorUno) ) this.rondasEquipoUno += 1;
            else this.rondasEquipoDos += 1;
        }

        if ( this.rondasEquipoUno > this.rondasEquipoDos) return Ganador.EquipoUno;
        if ( this.rondasEquipoUno < this.rondasEquipoDos) return Ganador.EquipoDos;
        else return null;
    }

    public void limpiar() {
        this.cartasJugadas.clear();
    }

    public int cartasEnMesa() {
        return this.cartasJugadas.size();
    }
}
package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fiuba.algo3.modelo.enums.Palo;
import fiuba.algo3.modelo.enums.TipoCarta;
import fiuba.algo3.modelo.interfaces.Ronda;

public class SegundaRonda implements Ronda{
	
	private HashMap<Equipo,Carta> cartas;
	private Equipo equipoGanadorDePrimeraRonda,equipoDos;

	public SegundaRonda(Equipo equipoGanador, Equipo otroEquipo) {
		this.equipoGanadorDePrimeraRonda = equipoGanador;
		this.equipoDos = otroEquipo;
		
		this.cartas = new HashMap<Equipo,Carta>();
		this.cartas.put(equipoGanador, new CartaInvalida(TipoCarta.INVALIDO,Palo.BASTO));
		this.cartas.put(otroEquipo, new CartaInvalida(TipoCarta.INVALIDO,Palo.BASTO));
	}

	@Override
	public void jugarCarta(Equipo equipo, Carta carta) {
		this.cartas.replace(equipo, carta);
	}

	@Override
	public Resultado ganadorDeRonda() {
		List<Carta> cartas = new ArrayList<Carta>(this.cartas.values());
		
		int resultado = 0;
		resultado = cartas.get(0).comparar(cartas.get(1));

		if (resultado == 1) return new EquipoGanador(this.equipoGanadorDePrimeraRonda);
		else if (resultado == -1) return new EquipoGanador(this.equipoDos);
		else return new Emparda();
	}

	@Override
	public Resultado determinarGanadorDeMano() {
		if(this.ganadorDeRonda().equals(this.equipoGanadorDePrimeraRonda)) return new EquipoGanador(this.equipoGanadorDePrimeraRonda);
		else return new Emparda();
	}

	@Override
	public Ronda calcularRondaSiguiente() {
		if(this.cartasJugadas() <= 2) return this;
		
		return this.ganadorDeRonda().calcularRondaSiguiente(this);
	}

	private int cartasJugadas() {
		int total = 0;

		for(Carta carta: this.cartas.values()){
			if(!carta.getTipoCarta().equals(TipoCarta.INVALIDO)) total++;
		}
		return total;
	}

	public Ronda siguiente() {
		return new RondaFinal(this.ganadorDeRonda().verEquipo(),this.equipoDos);
	}

}

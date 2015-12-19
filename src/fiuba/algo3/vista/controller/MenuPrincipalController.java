package fiuba.algo3.vista.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Equipo;
import fiuba.algo3.modelo.IA;
import fiuba.algo3.modelo.Jugador;
import fiuba.algo3.modelo.tipoJuego.TrucoConFlor;
import fiuba.algo3.modelo.tipoJuego.TrucoSinFlor;
import fiuba.algo3.vista.controller.handler.BtnSalirHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPrincipalController extends Controller {

	@FXML
	private Button btnJugar;
	@FXML
	private Button btnAyuda;
	@FXML
	private Button btnSalir;

	@FXML
	private Button btnDosJugadores;
	@FXML
	private Button btnCuatroJugadores;
	@FXML
	private Button btnPicaPica;
	@FXML
	private Button btnVolver;
	@FXML
	private Button btnVolverDeFlor;
	@FXML
	private Button btnContraIA;
	@FXML
	private Button btnSinFlor;
	@FXML
	private Button btnConFlor;

	private boolean isMesaConIA;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		isMesaConIA = false;
		btnJugarHandler();
		btnVolverHandler();
		btnSalirHandler();
		btnAyudaHandler();
		btnDosJugadoresHandler();
		btnCuatroJugadoresHandler();
		btnPicaPicaHandler();
		btnSinFlorHandler();
		btnConFlorHandler();
		btnVolverDeFlor();
		btnContraIAHandler();
	}

	private EventHandler<ActionEvent> esconderOtrosBotonesHandler = new EventHandler<ActionEvent>() {
		@Override
		public void handle(final ActionEvent event) {
			cambiarVisibilidadDeBotones();
		}
	};

	private void btnJugarHandler() {
		btnJugar.setOnAction(esconderOtrosBotonesHandler);
	}

	private void btnVolverHandler() {
		btnVolver.setOnAction(esconderOtrosBotonesHandler);
	}

	private void cambiarVisibilidadDeBotones() {
		cambiarVisibilidadBoton(btnDosJugadores);
		cambiarVisibilidadBoton(btnCuatroJugadores);
		cambiarVisibilidadBoton(btnPicaPica);
		cambiarVisibilidadBoton(btnAyuda);
		cambiarVisibilidadBoton(btnJugar);
		cambiarVisibilidadBoton(btnSalir);
		cambiarVisibilidadBoton(btnVolver);
		cambiarVisibilidadBoton(btnContraIA);
	}

	private void cambiarVisibilidadBoton(Button unBoton) {
		unBoton.setVisible(!unBoton.isVisible());
	}

	private void btnSalirHandler() {
		btnSalir.setOnAction(new BtnSalirHandler());
	}

	private void btnAyudaHandler() {
		btnAyuda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				redirect("Ayuda");
			}
		});
	}

	private void btnVolverDeFlor() {
		btnVolverDeFlor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cambiarVisibilidadBoton(btnDosJugadores);
				cambiarVisibilidadBoton(btnCuatroJugadores);
				cambiarVisibilidadBoton(btnContraIA);
				cambiarVisibilidadBoton(btnPicaPica);
				cambiarVisibilidadBoton(btnConFlor);
				cambiarVisibilidadBoton(btnSinFlor);
				cambiarVisibilidadBoton(btnVolverDeFlor);
			}
		});
	}

	private void btnDosJugadoresHandler() {
		btnDosJugadores.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				isMesaConIA = false;
				MesaController.setCantidadJugadores(2);
				cambiarVisibilidadBoton(btnDosJugadores);
				cambiarVisibilidadBoton(btnCuatroJugadores);
				cambiarVisibilidadBoton(btnContraIA);
				cambiarVisibilidadBoton(btnPicaPica);
				cambiarVisibilidadBoton(btnConFlor);
				cambiarVisibilidadBoton(btnSinFlor);
				cambiarVisibilidadBoton(btnVolverDeFlor);
			}
		});
	}

	private void btnContraIAHandler() {
		btnContraIA.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				isMesaConIA = true;
				MesaController.setCantidadJugadores(2);
				cambiarVisibilidadBoton(btnDosJugadores);
				cambiarVisibilidadBoton(btnCuatroJugadores);
				cambiarVisibilidadBoton(btnContraIA);
				cambiarVisibilidadBoton(btnPicaPica);
				cambiarVisibilidadBoton(btnConFlor);
				cambiarVisibilidadBoton(btnSinFlor);
				cambiarVisibilidadBoton(btnVolverDeFlor);
			}
		});
	}

	private void btnesFlor(int cantidadDeJugadores) {
		if (cantidadDeJugadores < 5)
			if (isMesaConIA) {
				redirect("MesaConIA");
			} else {
				redirect("Mesa");
			}
		else
			redirect("MesaDeSeis");
	}

	private void btnSinFlorHandler() {
		btnSinFlor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int cantidadDeJugadores = MesaController.getCantidadJugadores();
				juegoTruco = new TrucoSinFlor(armarEquipo(cantidadDeJugadores / 2, false),
						armarEquipo(cantidadDeJugadores / 2, isMesaConIA));
				MesaController.mesa = juegoTruco.obtenerMesa();
				btnesFlor(cantidadDeJugadores);
			}
		});
	}

	private void btnConFlorHandler() {
		btnConFlor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int cantidadDeJugadores = MesaController.getCantidadJugadores();
				juegoTruco = new TrucoConFlor(armarEquipo(cantidadDeJugadores / 2, false),
						armarEquipo(cantidadDeJugadores / 2, isMesaConIA));
				MesaController.mesa = juegoTruco.obtenerMesa();
				btnesFlor(cantidadDeJugadores);
			}
		});
	}

	private void btnPicaPicaHandler() {
		btnPicaPica.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				isMesaConIA = false;
				MesaController.setCantidadJugadores(6);
				cambiarVisibilidadBoton(btnDosJugadores);
				cambiarVisibilidadBoton(btnCuatroJugadores);
				cambiarVisibilidadBoton(btnContraIA);
				cambiarVisibilidadBoton(btnPicaPica);
				cambiarVisibilidadBoton(btnConFlor);
				cambiarVisibilidadBoton(btnSinFlor);
				cambiarVisibilidadBoton(btnVolverDeFlor);
			}
		});
	}

	private void btnCuatroJugadoresHandler() {
		btnCuatroJugadores.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				isMesaConIA = false;
				MesaController.setCantidadJugadores(4);
				cambiarVisibilidadBoton(btnDosJugadores);
				cambiarVisibilidadBoton(btnCuatroJugadores);
				cambiarVisibilidadBoton(btnContraIA);
				cambiarVisibilidadBoton(btnPicaPica);
				cambiarVisibilidadBoton(btnConFlor);
				cambiarVisibilidadBoton(btnSinFlor);
				cambiarVisibilidadBoton(btnVolverDeFlor);
			}
		});
	}

	private Equipo armarEquipo(int cantidadDeJugadores, boolean isIA) {
		Equipo equipo = new Equipo();

		for (int i = 0; i < cantidadDeJugadores; i++) {
			Jugador jugador;
			if (isIA) {
				jugador = new IA();
			} else {
				jugador = new Jugador("Jugador" + (i + 1));
			}
			jugador.asignarEquipo(equipo);
			equipo.agregarJugador(jugador);
		}
		return equipo;
	}
}

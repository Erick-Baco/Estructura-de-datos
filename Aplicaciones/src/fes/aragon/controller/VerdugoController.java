package fes.aragon.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class VerdugoController {
	private int desafortunados;
	private int salto;
	private int salvarEn;

	@FXML
	private ImageView VerEspera;

	@FXML
	private ImageView VerMatando;

	@FXML
	private AnchorPane panelPrincipal;

	@FXML
	private Button desaparecer;

	@FXML
	private TextField numerodeDesafortunados;

	@FXML
	private TextField campoEntradaValorM;

	@FXML
	private TextField campoPosicionCompanero;

	@FXML
	void desaparecerPeriodistas(ActionEvent evento) throws Exception {

		VerEspera.setOpacity(0);

		salvarEn = Integer.parseInt(campoPosicionCompanero.getText());
		desafortunados = Integer.parseInt(numerodeDesafortunados.getText());

		salto = determinarPaso(desafortunados, salvarEn);
		panelPrincipal.getChildren().removeIf(nodo -> nodo instanceof Circle);
		pintar(desafortunados, salvarEn);
		verdugo(desafortunados);
	}

	@FXML
	void inicializar() {

	}

	private void pintar(int numSujetos, int indiceCompanero) {
		double radio = 200;
		double X = 275;
		double Y = 300;

		VerMatando.setOpacity(1);

		for (int i = 0; i < numSujetos; i++) {
			double angulo = i * (360.0 / numSujetos);
			double posX = X + radio * Math.cos(Math.toRadians(angulo));
			double posY = Y + radio * Math.sin(Math.toRadians(angulo));

			Circle individuo;
			if (i == indiceCompanero) {
				individuo = new Circle(posX, posY, 10, Color.GREEN);
			} else {
				individuo = new Circle(posX, posY, 10, Color.BLUE);
			}

			panelPrincipal.getChildren().add(individuo);
		}
	}

	private void verdugo(int n) throws Exception {

		List<Circle> individuos = new ArrayList<>();
		Node nodo;
		int indiceEjecutor;
		for (int i = 0; i < panelPrincipal.getChildren().size(); i++) {
			nodo = panelPrincipal.getChildren().get(i);
			if (nodo instanceof Circle) {
				individuos.add((Circle) nodo);

			}
		}

		indiceEjecutor = individuos
				.indexOf(individuos.stream().filter(c -> c.getFill() == Color.RED).findFirst().orElse(null));
		List<Integer> ordenEjecucion = turno(desafortunados, salto, salvarEn);
		animacion(individuos, ordenEjecucion, indiceEjecutor, n);
	}

	private void animacion(List<Circle> individuos, List<Integer> ordenEjecucion, int indiceEjecutor, int m) {
		VerMatando.setRotate(0);
		new Thread(() -> {
			try {
				for (int j = 0; j < ordenEjecucion.size(); j++) {
					int i = ordenEjecucion.get(j);
					if (i != salvarEn) {

						individuos.get(i).setFill(Color.RED);
						VerMatando.setRotate((360 / m) * i);
						

						Thread.sleep(1000);

					}
				}

				individuos.get(salvarEn).setFill(Color.GREEN);

				Platform.runLater(() -> desaparecer.setDisable(false));

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static List<Integer> turno(int totalIndividuos, int m, int indiceCompanero) {
		List<Integer> turno = new ArrayList<>();
		List<Integer> noAmigos = new ArrayList<>();

		for (int i = 0; i < totalIndividuos; i++) {
			noAmigos.add(i);
		}

		int index = 0;

		while (noAmigos.size() > 1) {
			index = (index + m) % noAmigos.size();
			turno.add(noAmigos.remove(index));
		}

		turno.add(noAmigos.get(0));

		return turno;
	}

	private int determinarPaso(int totalIndividuos, int indiceCompanero) {
		indiceCompanero = (indiceCompanero + 1) % totalIndividuos;
		return (totalIndividuos - 1) % totalIndividuos;
	}
}

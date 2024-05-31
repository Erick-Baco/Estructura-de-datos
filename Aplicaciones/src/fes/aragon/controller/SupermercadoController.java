package fes.aragon.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import fes.aragon.utilerias.dinamicas.cola.Cola;
import fes.aragon.utilerias.dinamicas.cola.ColaInterfaz;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SupermercadoController implements Initializable {

	@FXML
	private ImageView caja;

	@FXML
	private ImageView imgGreen;

	@FXML
	private ImageView imgRed;

	@FXML
	private Button btnIniciar;

	@FXML
	private Pane panel;

	@FXML
	private Label lblTiempo;

	@FXML
	private Label lblminutos;

	@FXML
	private Label lblActual;

	@FXML
	private Label lblTurno;

	@FXML
	private Label lblCola;

	@FXML
	private Label lblNumeroFormados;

	@FXML
	private Canvas lienzo;

	private int segundos = 0;

	private Timeline timeline;

	private GraphicsContext graficos;

	private int nuevo;
	private int atender;
	private int i;
	private int turno = 1;
	private int xx = 0;
	private int yy = 150;

	ColaInterfaz<Cliente> cola = new Cola<>();

	int numero = 0;

	@FXML
	void Iniciar(ActionEvent event) {

		graficos = lienzo.getGraphicsContext2D();
		nuevo = nuevo();
		atender = nuevo();

		imgGreen.setVisible(false);
		imgRed.setVisible(false);
		imgGreen.setOpacity(1);
		imgRed.setOpacity(1);

		String nombreArchivo = "C:/Users/hp/eclipse-workspace/SegundoParcial/src/fes/aragon/recursos/ticket.txt";
		
		
		if (timeline == null) {
			timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					try {
						
						FileWriter archivoEscritura = new FileWriter(nombreArchivo, true);

			            // Crear un objeto BufferedWriter para escribir en el archivo de manera eficiente
			            BufferedWriter writer = new BufferedWriter(archivoEscritura);

			            
						System.out.println("..");
						// lleva el cronometro
						segundos++;
						lblTiempo.setText(segundos + "");

						// lleva las impresiones
						/*for (int h = segundos*7 - 7; h >= 0; h--) {
							writer.newLine();
						}*/

						writer.newLine();
						writer.write("===============================================");
						writer.newLine();
						writer.write("tiempo: " + segundos);
						writer.newLine();
						writer.write("elementos:" + numero);
						writer.newLine();

						imgGreen.setVisible(false);
						imgRed.setVisible(false);

						// ingresa clientes a la fila
						if (segundos == nuevo) {

							writer.write("cliente nuevo turno: " + turno);
							writer.newLine();

							cola.insertar(new Cliente(turno++));
							imgGreen.setVisible(true);
							numero++;
							nuevo += nuevo();

						}

						// salen clientes
						if (!cola.esVacia()) {
							if (atender == 0) {
								try {
									writer.write("sale cliente turno " + cola.primerElemento().getTurno());
									writer.newLine();
									lblActual.setText("" + cola.primerElemento().getTurno());
									imgRed.setVisible(true);

									cola.extraer();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								numero--;
								atender = nuevo();

							}
							atender--;
						}

						lblNumeroFormados.setText("" + numero);

						graficos.clearRect(0, 0, 700, 700);
						i = 0;
						xx = 0;
						yy = 150;
						if (!cola.esVacia()) {
							while (i < numero) {

								if (i == 0) {
									graficos.drawImage(new Image("/fes/aragon/recursos/GO.png"), 0, 0);
									try {
										lblActual.setText("" + cola.primerElemento().getTurno());
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {
									if (i % 5 == 0) {
										yy += 150;
									}
									graficos.drawImage(new Image("/fes/aragon/recursos/GO.png"), xx, yy);
									xx += 75;
								}
								i++;
							}
						} else {
							lblActual.setText(null);
						}

						writer.close();

					} catch (IOException e) {
						System.err.println("Error al escribir en el archivo: " + e.getMessage());
					}

				}
			}));

			timeline.setCycleCount(720);
			timeline.play();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		panel.setStyle("-fx-background-color: #" + "ffffff");
		imgGreen.setOpacity(0.2);
		imgRed.setOpacity(0.2);
	}

	public static int nuevo() {
		int res = ThreadLocalRandom.current().nextInt(1, 100);
		if (0 < res && res < 26) {
			return 1;
		} else if (25 < res && res < 51) {
			return 2;
		} else if (50 < res && res < 76) {
			return 3;
		} else if (75 < res && res < 101) {
			return 4;
		} else {
			return 0;
		}
	}
}

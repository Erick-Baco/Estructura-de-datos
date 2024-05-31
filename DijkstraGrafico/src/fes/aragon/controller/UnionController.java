package fes.aragon.controller;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UnionController {

	@FXML
	private Button btnAceptar;

	@FXML
	private Label lblTitulo;

	@FXML
	private ChoiceBox<Integer> opcionDos;

	@FXML
	private ChoiceBox<Integer> opcionUno;

	@FXML
	private Pane panel;

	private Stage stage;

	private Integer uno;

	private Integer dos;

	public void setStage(Stage stage) {
		this.stage = stage;

	}

	// carga la lista con los indices de los nodos
	
	@FXML
	void cargarIndices(MouseEvent event) {
		opcionUno.setItems(CreadorController.getIndicesObservable());
		opcionDos.setItems(CreadorController.getIndicesObservable());

	}

	//obtiene los valores seleccionados en los campos 
	// si no son nulos guarda los valores en variables de CreadorController además cambia la banderaUnir a true cierra la ventana
	// si son nulos lanza ventana de error y pone banderaunir en false
	// devuelve el control a CreadorController línea 102
	@FXML
	void metodoAceptar(MouseEvent event) {

		uno = opcionUno.getValue();
		dos = opcionDos.getValue();
		if(uno != null & dos != null) {
			CreadorController.setUnirUno(uno);
			CreadorController.setUnirDos(dos);
			CreadorController.setBanderaUnir(true);
			stage.close();
		} else {
			CreadorController.setBanderaUnir(false);
			SwingUtilities.invokeLater(() -> {
				JOptionPane.showMessageDialog(null, "Seleccione una opción", null, JOptionPane.ERROR_MESSAGE);

			});
		}

	}

}

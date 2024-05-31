package fes.aragon.controller;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CDatosController {

	@FXML
	private Button btnAceptar;

	@FXML
	private Label lblNombre;

	@FXML
	private Pane pane;

	@FXML
	private TextField tfValor;

	private Stage stage;
	
	

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// metodo que entra cuando se crea la ventan CDatos
	// registra el valor para el nodo y lo guarda en la variable de "CreadorController"
	// antes de cerrar cambia BanderaUnir a verdadero
	// continua código en CreadorController línea 139
	@FXML
	void metodoAceptar(ActionEvent event) {
		try {
			CreadorController.setValor(Integer.parseInt(tfValor.getText(), 10));
			CreadorController.setBanderaUnir(true);
			stage.close();
		}catch(NumberFormatException nfe) {
			CreadorController.setBanderaUnir(false);
			SwingUtilities.invokeLater(() -> {
	            JOptionPane.showMessageDialog(null, "Introduzca un valor numérico",null, JOptionPane.ERROR_MESSAGE);
	            
	        });
		}
		
	}

}

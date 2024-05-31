package fes.aragon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CatalanController {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button botonaccion;

    @FXML
    private TextField dato;

    @FXML
    private Label etiquetaresultado;

    @FXML
    private Label instrucciones;

    @FXML
    private TextField resultado;

    @FXML
    private Label titulo;

    @FXML
    void calcularCatalan() {
        String inputText = dato.getText();
        try {
            int n = Integer.parseInt(inputText);
            Integer catalanN = NumeroCatalan.calcularCatalan(n);
            resultado.setText(catalanN.toString());
        } catch (NumberFormatException e) {
            resultado.setText("Por favor, ingresa un número válido");
        }
    }

}


package fes.aragon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AckermanController {

    @FXML
    private TextField campoAckermann;

    @FXML
    private TextField campoM;

    @FXML
    private TextField campoN;

    @FXML
    private TextField campoProfundidad;

    @FXML
    private TextField datos;

    @FXML
    private AnchorPane fondo;

    @FXML
    private Label indicaciones;

    @FXML
    private Label textoAckermann;

    @FXML
    private Label textoM;

    @FXML
    private Label textoN;

    @FXML
    private Label textoProfundidad;

    @FXML
    private Label titulo;

    @FXML
    void calcularAckermann(ActionEvent event) {
        String[] valores = datos.getText().split(" ");
        int m = Integer.parseInt(valores[0]);
        int n = Integer.parseInt(valores[1]);

        // Calcular Ackermann y mostrar resultados en los TextField
        int resultado = Ackermann.ackermann(m, n, 0);

        campoM.setText(String.valueOf(m));
        campoN.setText(String.valueOf(n));
        campoAckermann.setText(String.valueOf(resultado));
        campoProfundidad.setText(String.valueOf(Ackermann.getProfundidad()));
    }
}


package fes.aragon.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class OperacionesController {

    @FXML
    private Button btnResolver;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblOperacion;

    @FXML
    private Label lblPosfija;

    @FXML
    private Label lblResultado;

    @FXML
    private Pane panel;

    @FXML
    private TextField tfOperacion;

    @FXML
    private TextField tfPosfija;

    @FXML
    private TextField tfResultado;
    
    

    public OperacionesController() {
		super();
	}
    
	public Button getBtnResolver() {
		return btnResolver;
	}





	public void setBtnResolver(Button btnResolver) {
		this.btnResolver = btnResolver;
	}





	public Label getLblNombre() {
		return lblNombre;
	}





	public void setLblNombre(Label lblNombre) {
		this.lblNombre = lblNombre;
	}





	public Label getLblOperacion() {
		return lblOperacion;
	}





	public void setLblOperacion(Label lblOperacion) {
		this.lblOperacion = lblOperacion;
	}





	public Label getLblPosfija() {
		return lblPosfija;
	}





	public void setLblPosfija(Label lblPosfija) {
		this.lblPosfija = lblPosfija;
	}





	public Label getLblResultado() {
		return lblResultado;
	}





	public void setLblResultado(Label lblResultado) {
		this.lblResultado = lblResultado;
	}





	public Pane getPanel() {
		return panel;
	}





	public void setPanel(Pane panel) {
		this.panel = panel;
	}





	public TextField getTfOperacion() {
		return tfOperacion;
	}





	public void setTfOperacion(TextField tfOperacion) {
		this.tfOperacion = tfOperacion;
	}





	public TextField getTfPosfija() {
		return tfPosfija;
	}





	public void setTfPosfija(TextField tfPosfija) {
		this.tfPosfija = tfPosfija;
	}





	public TextField getTfResultado() {
		return tfResultado;
	}





	public void setTfResultado(TextField tfResultado) {
		this.tfResultado = tfResultado;
	}





	@FXML
    void resolverOperacion(MouseEvent event) throws Exception {
    	String opeCadena = tfOperacion.getText();
    	InterPos operacion = new InterPos();
    	String opTrans = InterPos.transformarInPos(opeCadena);
		Float res = InterPos.evaluador(opTrans);
		tfPosfija.setText(opTrans);
		tfResultado.setText(Float.toString(res));;
    }

}

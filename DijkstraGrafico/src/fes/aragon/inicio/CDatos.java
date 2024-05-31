package fes.aragon.inicio;

import fes.aragon.controller.CDatosController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CDatos extends Stage{
	
	public CDatos() {
		
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fes/aragon/fxml/CDatosInicio.fxml"));
            Pane root = loader.load();
            Scene scene = new Scene(root);
            setScene(scene);
            CDatosController controller = loader.getController();
            controller.setStage(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
}

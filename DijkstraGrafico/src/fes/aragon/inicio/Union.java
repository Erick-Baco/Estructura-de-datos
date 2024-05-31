package fes.aragon.inicio;

import fes.aragon.controller.UnionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Union extends Stage{

	public Union() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fes/aragon/fxml/UnionInicio.fxml"));
            Pane root = loader.load();
            Scene scene = new Scene(root);
            setScene(scene);
            UnionController controller = loader.getController();
            controller.setStage(this);
            
            
            
           

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

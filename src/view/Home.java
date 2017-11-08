package view;

import controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Home extends Application {

	public static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader fxml= new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
		ClientController controller= new ClientController();
		fxml.setController(controller);
		Scene scene= new Scene(fxml.load());
		stage.setTitle("Client Chat- Sistemas Operacionais");
		stage.setScene(scene);
		//stage.setOnCloseRequest(e->Client.sendMessage("desconectar do chat"));
		Home.stage=stage;
		stage.show();
	}

}

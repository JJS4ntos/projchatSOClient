package controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.Client;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import view.Home;

public class ClientController implements Initializable {

	@FXML private TextField tf_mensagem;
	@FXML private ListView<String> list_mensagens;
	private Client client;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		criarConexao();
		initActions();
	}
	
	private void initActions() {
		tf_mensagem.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER) {
				client.sendMessage(tf_mensagem.getText());
				tf_mensagem.clear();
			}
		});
		Platform.runLater(()->Home.stage.setOnCloseRequest(e->client.sendMessage("REMOVER$chat")));
	}
	
	private void criarConexao() {
		try {
			client= new Client("127.0.0.1", 666);
			DataInputStream in= new DataInputStream(client.getSocket().getInputStream());
			Thread thread= new Thread(escutarServidor(in));
			thread.setDaemon(true);
			thread.start();
		}
		catch(IOException e) {e.printStackTrace();}
	}
	
	private Task<Void> escutarServidor(DataInputStream in) {
		Task<Void> task= new Task<Void>() {
			protected Void call() throws IOException {
				while(true) {
					String[] mensagem=in.readUTF().split("ии");
					Platform.runLater(()->list_mensagens.getItems().add(mensagem[0].concat(": ").concat(mensagem[1])));
				}
			}
		};
		return task;
	}
	
}

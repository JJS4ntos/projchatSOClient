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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import view.Home;

public class ClientController implements Initializable {

    @FXML private Label lbl_sorriso, lbl_sorriso2, lbl_gargalhada, lbl_pensativo, lbl_surpreso, lbl_surpreso2, lbl_raiva, lbl_olholado;
	@FXML private TextField tf_mensagem;
	@FXML private ListView<String> list_mensagens;
	@FXML private ImageView img_sender;
	private Client client;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		criarConexao();
		initActions();
	}
	
	/***
	 * Inicia as ações nos elementos da tela
	 */
	private void initActions() {
		tf_mensagem.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER) {
				client.sendMessage(tf_mensagem.getText());
				tf_mensagem.clear();
			}
		});
		lbl_sorriso.setOnMouseClicked(e->tf_mensagem.setText(tf_mensagem.getText()+lbl_sorriso.getText()));
		lbl_sorriso2.setOnMouseClicked(e->tf_mensagem.setText(tf_mensagem.getText()+lbl_sorriso2.getText()));
		lbl_gargalhada.setOnMouseClicked(e->tf_mensagem.setText(tf_mensagem.getText()+lbl_gargalhada.getText()));
		lbl_pensativo.setOnMouseClicked(e->tf_mensagem.setText(tf_mensagem.getText()+lbl_pensativo.getText()));
		lbl_surpreso.setOnMouseClicked(e->tf_mensagem.setText(tf_mensagem.getText()+lbl_surpreso.getText()));
		lbl_surpreso2.setOnMouseClicked(e->tf_mensagem.setText(tf_mensagem.getText()+lbl_surpreso2.getText()));
		lbl_raiva.setOnMouseClicked(e->tf_mensagem.setText(tf_mensagem.getText()+lbl_raiva.getText()));
		lbl_olholado.setOnMouseClicked(e->tf_mensagem.setText(tf_mensagem.getText()+lbl_olholado.getText()));
		Platform.runLater(()->Home.stage.setOnCloseRequest(e->{
			client.sendMessage("Hey pessoal, estou me desconectando.");
			client.sendMessage("REMOVER$chat");
		}));
	}
	
	/***
	 * Cria conexão com o servidor
	 */
	private void criarConexao() {
		try {
			client= new Client("127.0.0.1", 512);
			DataInputStream in= new DataInputStream(client.getSocket().getInputStream());
			Thread thread= new Thread(escutarServidor(in));
			thread.setDaemon(true);
			thread.start();
		}
		catch(IOException e) {e.printStackTrace();}
	}
	
	/***
	 * Cria uma tarefa de loop infinito para escutar tudo que vier do servidor
	 * @param in
	 * @return
	 */
	private Task<Void> escutarServidor(DataInputStream in) {
		Task<Void> task= new Task<Void>() {
			protected Void call() throws IOException {
				while(true) {
					String[] mensagem=in.readUTF().split("¨¨");
					Platform.runLater(()->list_mensagens.getItems().add(mensagem[0].concat(": ").concat(mensagem[1])));
				}
			}
		};
		return task;
	}
	
}

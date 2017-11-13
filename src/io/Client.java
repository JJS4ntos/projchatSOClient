package io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import javafx.scene.image.Image;

public class Client {
	
	private final Socket socket;
	private ObjectOutputStream out;
	
	public Client(String ip, int porta) throws UnknownHostException, IOException {
		socket= new Socket(ip, porta);
		out= new ObjectOutputStream(socket.getOutputStream());
	}

	public Socket getSocket() {
		return socket;
	}
	
	public void sendMessage(String mensagem) {
		try {
			out.writeUTF("[".concat(LocalDateTime.now().toString()).concat("]").concat(socket.getInetAddress().getHostAddress().concat("ии").concat(mensagem)));
			out.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendImage(Image image) {
		try {
			ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(image);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sair() {
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

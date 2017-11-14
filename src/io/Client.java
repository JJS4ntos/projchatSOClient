package io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Client {
	
	private final Socket socket;
	private DataOutputStream out;
	private DateTimeFormatter format= DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	
	/***
	 * Construtor do m�todo client
	 * @param ip
	 * @param porta
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public Client(String ip, int porta) throws UnknownHostException, IOException {
		socket= new Socket(ip, porta);
		out= new DataOutputStream(socket.getOutputStream());
	}
	
	/***
	 * Retorna o socket de conex�o
	 * @return
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/***
	 * Envia mensagem para o servidor
	 * @param mensagem
	 */
	public void sendMessage(String mensagem) {
		try {
			out.writeUTF("[".concat(LocalDateTime.now().format(format)).concat("]").concat(socket.getInetAddress().getHostAddress().concat("��").concat(mensagem)));
			out.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Fecha o OutputStream
	 */
	public void sair() {
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

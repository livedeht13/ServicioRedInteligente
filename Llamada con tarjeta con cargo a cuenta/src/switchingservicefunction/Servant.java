package switchingservicefunction;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ListIterator;

import TheChunk.DataChunk;
import TheUser.Usuario;

public class Servant extends Thread {

	private String conversationTitle;
	private DataChunk chunk;
	private Usuario usuario;
	//private static final DBConnectionManager connectionManager = new DBConnectionManager();

	public Servant(DataChunk chunk) {
		this.usuario = chunk.getSender();
		this.chunk = chunk;
	}

	class NotificaUsuario extends Thread {

		DataChunk chunk;

		public NotificaUsuario(DataChunk chunk) {
			this.chunk = chunk;
		}

		@Override
		public void run() {
			Cifrado cifrado = new Cifrado("MyPriceOfHistory");
			int counter = 0;
			boolean wasSent = false;
			while (!wasSent && counter < 2) {
				Socket socket = new Socket();
				Usuario currentUser = null;
				try {
					ListIterator<Usuario> userIterator = Boss.getUsuarios()
							.listIterator();
					while (userIterator.hasNext()) {
						currentUser = userIterator.next();
						if (currentUser.getNombre().equals(
								chunk.getDestination().getNombre())) {
							chunk.getDestination().setHostAddress(
									currentUser.getHost());
							break;
						}
					}
					InetSocketAddress addr = new InetSocketAddress(chunk
							.getDestination().getHost(), 5002);
					socket.connect(addr, 5000);
					ObjectOutputStream salida = new ObjectOutputStream(
							socket.getOutputStream());
					salida.writeObject(chunk);
					salida.flush();
					salida.close();
					socket.close();
					wasSent = true;
					System.out.println("Éxito al enviar a: "
							+ chunk.getDestination().getNombre());
				} catch (IOException e) {
					if (!e.toString().contains("timed out")) {
						wasSent = true;
					} else {
						wasSent = false;
						counter++;
					}
				}
			}
		}
	}

	@Override
	public void run() {
		if (!chunk.getDestination().getNombre().equals("All")) {
			NotificaUsuario notificador = new NotificaUsuario(chunk);
			notificador.start();
		} else {
			System.out.println(chunk.getSender().getNombre()
					+ " dice: "
					+ (new Cifrado("MyPriceOfHistory")).decipher(chunk
							.getCipheredString()));
			synchronized (Boss.getUsuarios()) {
				ListIterator<Usuario> iterUsuario = Boss.getUsuarios()
						.listIterator();
				while (iterUsuario.hasNext()) {
					Usuario currentUser = iterUsuario.next();
					if (!currentUser.getNombre().equals(usuario.getNombre())) {
						DataChunk replyChunk = new DataChunk();
						replyChunk.setSender(usuario);
						replyChunk.setDestination(currentUser);
						replyChunk.setCipheredString(chunk.getCipheredString());
						NotificaUsuario notificador = new NotificaUsuario(
								replyChunk);
						notificador.start();
					}
				}
			}
		}
	}

	public void setConversationTitle(String conversationTitle) {
		this.conversationTitle = conversationTitle;
	}
}
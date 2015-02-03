package switchingservicefunction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import TheChunk.DataChunk;
import TheUser.Usuario;

public class Attendant extends Thread {

	private Socket client;
	private TimerTask clientCloser;
	private Cifrado cifrado;
	private static Usuario masterUser;

	public Attendant(Socket client) {
		this.client = client;
		try {
			masterUser = new Usuario("Master", InetAddress.getLocalHost()
					.getHostAddress(), "");
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		clientCloser = new MyTask();
	}

	private class MyTask extends TimerTask {
		@Override
		public void run() {
			try {
				client.close();
			} catch (IOException e) {
			}
		}
	}

	/** This class shall be in charge of the login process **/
	@Override
	public void run() {
		try {
			ObjectInputStream entrada = new ObjectInputStream(
					client.getInputStream());
			Timer timer = new Timer();
			timer.schedule(clientCloser, 10000);
			DataChunk incommingUser = (DataChunk) entrada.readObject();
			timer.cancel();
			incommingUser.getSender().setHostAddress(
					client.getInetAddress().getHostAddress());
			ObjectOutputStream salida = new ObjectOutputStream(
					client.getOutputStream());
			DataChunk chunk = new DataChunk();
			boolean isUserIn = false;
			ListIterator<Usuario> currentUser = Boss.getUsuarios()
					.listIterator();
			synchronized (Boss.getUsuarios()) {
				while (currentUser.hasNext()) {
					Usuario aNewUser = currentUser.next();
					System.out.println("----> " + aNewUser.getNombre() + ", "
							+ aNewUser.getHost());
					if (aNewUser.getNombre().equals(
							incommingUser.getSender().getNombre())) {
						isUserIn = true;
						break;
					}
				}
			}
			if (isUserIn) {
				switch (incommingUser.getOption()) {
				case 0:
					chunk.setSender(masterUser);
					chunk.setCipheredString(cifrado.cipher("Ok"));
					salida.writeObject(chunk);
					salida.flush();
					Newcomer newcomer = new Newcomer(incommingUser.getSender(),
							salida);
					newcomer.start();
					break;
				case 1:
					System.out
							.println("A send message request of a current user");
					Servant servant = new Servant(incommingUser);
					servant.setConversationTitle("Laura en America");
					servant.start();
					break;
				}
			} else {
				synchronized (Boss.getUsuarios()) {
					Boss.addUsuario(incommingUser.getSender());
				}
				switch (incommingUser.getOption()) {
				case 0:
					chunk.setSender(masterUser);
					chunk.setCipheredString(cifrado.cipher("Ok"));
					salida.writeObject(chunk);
					salida.flush();
					Newcomer newcomer = new Newcomer(incommingUser.getSender(),
							salida);
					newcomer.start();
					break;
				case 1:
					System.out.println("A send message request");
					Servant servant = new Servant(incommingUser);
					servant.setConversationTitle("Laura en America");
					servant.start();
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {

		}
		Boss.removeAttendant(this);
	}

	public void setCifrado(Cifrado cifrado) {
		this.cifrado = cifrado;
	}
}
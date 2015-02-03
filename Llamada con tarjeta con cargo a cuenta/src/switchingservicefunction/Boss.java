package switchingservicefunction;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import TheUser.Usuario;

public class Boss extends Thread {

	private Cifrado cifrado;
	private ServerSocket server;
	private static LinkedList<Usuario> usuarios;
	private static LinkedList<Attendant> theAttendants;
	private int counterLock;

	public static LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}

	synchronized public static void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	synchronized public static void removeAttendant(Attendant attendant) {
		theAttendants.remove(attendant);
	}

	public static int counter = 0;

	public Boss() {
		usuarios = new LinkedList<Usuario>();
		theAttendants = new LinkedList<Attendant>();
		cifrado = new Cifrado("MyPriceOfHistory");
		counterLock = 0;
		try {
			server = new ServerSocket();
		} catch (IOException e) {
		}
	}

	public Boss(String key) {
		usuarios = new LinkedList<Usuario>();
		theAttendants = new LinkedList<Attendant>();
		cifrado = new Cifrado(key);
		counterLock = 0;
		try {
			server = new ServerSocket();
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (!server.isBound()) {
					server = new ServerSocket(5001);
				}
				System.out.println("Atendiendo a " + counterLock
						+ " peticiones.");
				if (counterLock >= 1000) {
					System.out.println("Accepting no more petitions");
					break;
				}
				System.out.println("Checking users in database");
				while (true) {
					if (theAttendants.size() >= 20) {
						sleep(2000);
					} else {
						Socket socket = server.accept();
						Attendant attendant = new Attendant(socket);
						attendant.setCifrado(cifrado);
						synchronized (theAttendants) {
							theAttendants.add(attendant);
						}
						attendant.start();
					}
					if (counterLock > 0)
						counterLock--;
				}
			} catch (IOException e) {
				counterLock++;
			} catch (InterruptedException ex) {
				counterLock++;
			}
		}
		try {
			server.close();
		} catch (IOException e) {
			System.out.println("No reason to be here.");
		}
	}
}
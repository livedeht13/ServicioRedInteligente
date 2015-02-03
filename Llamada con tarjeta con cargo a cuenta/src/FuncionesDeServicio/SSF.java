package FuncionesDeServicio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import switchingservicefunction.Attendant;
import switchingservicefunction.Cifrado;
import TheUser.Usuario;

public class SSF extends Thread {


	private Cifrado cifrado;
	private ServerSocket server;
	private static LinkedList<Usuario> usuarios;
	private static LinkedList<Attendant> theAttendants;
	private int counterLock;
	
	@Override
	public void run() {
		try {
			if (!server.isBound()) {
				server = new ServerSocket(5001);
			}
			while (true) {
				Socket socket = server.accept();
				Attendant attendant = new Attendant(socket);
				attendant.setCifrado(cifrado);
				synchronized (theAttendants) {
					theAttendants.add(attendant);
				}
				attendant.start();
			}
		} catch (IOException e) {
			counterLock++;
		}
	}
}

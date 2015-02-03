package switchingservicefunction;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.ListIterator;

import TheChunk.DataChunk;
import TheUser.Usuario;


public class Newcomer extends Thread{
	
	private LinkedList<DataChunk> messages;
	private Usuario usuario;
	private ObjectOutputStream salida;
	
	public Newcomer(Usuario usuario, ObjectOutputStream salida){
		this.usuario = usuario;
		this.salida = salida;
	}
	
	@Override
	public void run() {
		try{
			salida.writeObject(Boss.getUsuarios());
			salida.flush();
			salida.close();
		}catch(IOException e){
			System.out.println("Error al mandar la lista de usuarios.");
		}
	}
}

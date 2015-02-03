package switchingservicefunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import TheChunk.DataChunk;
import TheUser.Usuario;

public class DBConnectionManager {

	private Connection con;
	private Cifrado cifrado;

	public DBConnectionManager() {
		try {
			con = DriverManager
					.getConnection("jdbc:mysql://localhost/MesaDialogoMensajes?"
							+ "user=root&password=pokemones");
		} catch (SQLException e) {
		}
	}
	
	public void setCifrado(String key){
		cifrado = new Cifrado(key);
	}

	public boolean isUserIn(String usrName, String psswd) {
		if (con == null) {
			System.out.println("No se pudo llegar hasta aquí");
			return false;
		} else {
			try {
				ResultSet rs = con.createStatement().executeQuery(
						"CALL consultaUsuario('" + usrName + "','" + psswd
								+ "');");
				String aux = new String();
				while (rs.next()) {
					aux = rs.getString(1);
				}
				if (!aux.equals("1")) {
					System.out.println("NO");
					return false;
				} else {
					return true;
				}
			} catch (SQLException e) {
				System.out.println(e.toString());
				return false;
			}
		}
	}

	public void keepMessage(String conversationTitle, String sender,
			String destination, String message, boolean deliveryState) {
		if (con != null)
			try {
				PreparedStatement stmnt = con
						.prepareStatement("CALL keepMessage(?,?,?,?,?);");
				stmnt.setString(1,conversationTitle);
				stmnt.setString(2,sender);
				stmnt.setString(3,destination);
				stmnt.setString(4, message);
				stmnt.setBoolean(5, deliveryState);
				stmnt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Y DEME LOS PINCHIS ENVASES EH OKEY!? "
						+ e.toString());
			}
	}

	public LinkedList<String> getUser(String name) {
		LinkedList<String> datos = new LinkedList<String>();
		if (con != null) {
			try {
				System.out.println("Executing: " + "CALL retrieveUser('" + name
						+ "');");
				ResultSet rs = con.createStatement().executeQuery(
						"CALL retrieveUser('" + name + "');");
				rs.next();
				datos.add(rs.getString("Nombre").toString());
				datos.add(rs.getString("Password").toString());
				datos.add(rs.getString("HostAddress").toString());
			} catch (SQLException e) {
				System.out.println("From DBManager: " + e.toString());
			}
		}
		return datos;
	}

	public LinkedList<DataChunk> getPendingMessages(Usuario destination) {
		LinkedList<DataChunk> pendingMessages = new LinkedList<DataChunk>();
		if (con == null)
			return null;
		else {
			Cifrado cifrado = new Cifrado("MyPriceOfHistory");
			try {
				ResultSet rs = con.createStatement().executeQuery(
						"CALL retrievePendingMessages('"
								+ destination.getNombre() + "');");
				while (rs.next()) {
					DataChunk chunk = new DataChunk();
					chunk.setOption(rs.getInt(1));
					LinkedList<String> senderData = getUser(rs.getString(2));
					Usuario sender = new Usuario(senderData.get(0),
							senderData.get(2), senderData.get(1));
					chunk.setSender(sender);
					chunk.setDestination(destination);
					System.out.println("the message: " + rs.getString(3));
					chunk.setCipheredString(cifrado.cipher(rs.getString(3)));
					pendingMessages.add(chunk);					
					//System.out.println("Lo que descubrimos fue: " + cifrado.decipher(rs.getString(3).getBytes()));
				}
			} catch (SQLException e) {
				System.out.println("From DBManager: " + e.toString());
			}
		}
		return pendingMessages;
	}

	public void updatePendingMessage(int id) {
		if (con == null)
			return;
		else {
			try {
				con.createStatement().executeQuery(
						"CALL updatePendingMessage(" + id + ");");
			} catch (SQLException e) {
				System.out.println("From DBManager update: " + e.toString());
			}
		}
	}

	public void manageHostAddress(String usrName, String hostAddress) {
		if (con != null) {
			try {
				con.createStatement().executeQuery(
						"CALL manageHostAddress('" + usrName + "','"
								+ hostAddress + "')");
			} catch (SQLException e) {
				System.out.println("Maybe the link already Exists\n"
						+ e.toString());
			}
		}
	}
}
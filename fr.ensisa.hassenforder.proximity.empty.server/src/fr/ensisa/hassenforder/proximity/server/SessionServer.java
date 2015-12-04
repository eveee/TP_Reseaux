package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.User;

public class SessionServer {

	private Socket connection;
	private Document document;
	
	public SessionServer (Document document, Socket connection) {
		this.document = document;
		this.connection = connection;
	}
	
	public void login(Writer writer, String name) {
		User user;
		if((user = document.doConnect(name)) == null) {
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.LOGIN);
			writer.writeUser(user);
		}
	}
	
	public void move(Writer writer, String name, int dx, int dy){
		User user;
		if((user = document.doMove(name, dx, dy)) == null){
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.MOVE);
			writer.writeUser(user);
		}
	}

	public boolean operate() {
		try {
			Writer writer = new Writer(connection.getOutputStream());
			Reader reader = new Reader(connection.getInputStream());
			reader.receive();
			switch(reader.getType()) {
				case 0 : return false; // socket closed
				case Protocol.LOGIN :{
					this.login(writer, reader.readName());
					writer.send();
					return true;
				}
				case Protocol.MOVE :{
					this.move(writer, reader.readName(), reader.readInt(), reader.readInt());
					writer.send();
					return true;
					
				}
				default: return false; // connection jammed
			}
		} catch (IOException e) {
			return false;
		}
	}

}

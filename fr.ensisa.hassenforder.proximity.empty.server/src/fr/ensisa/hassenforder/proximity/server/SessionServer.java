package fr.ensisa.hassenforder.proximity.server;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
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
	
	public void move(Writer writer, String name, int x, int y){
		if(!this.document.doMove(name, x, y)){
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.MOVE);
			writer.writeUser(this.document.getUserByName(name));
		}
	}
	
	public void chgmode(Writer writer, String name, Mode mode){
		if(!this.document.doChangeMode(name, mode)){
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.MOVE);
			writer.writeUser(this.document.getUserByName(name));
		}
	}
	
	public void chgradius(Writer writer, String name, int radius){
		if(!this.document.doChangeRadius(name, radius)){
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.CHGRADIUS);
			writer.writeUser(this.document.getUserByName(name));
		}
	}
	
	public void chgpreflevel(Writer writer, String name, String preference_name, int level){
		if(!this.document.doChangePreferenceLevel(name, preference_name, level)){
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.CHGPREFLEVEL);
			writer.writeUser(this.document.getUserByName(name));
		}
	}
	
	public void chgprefvisibility(Writer writer, String name, String preference_name, boolean visibility){
		if(!this.document.doChangePreferenceVisibility(name, preference_name, visibility)){
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.CHGPREFVISIBILITY);
			writer.writeUser(this.document.getUserByName(name));
		}
	}
	
	public void findnear(Writer writer, String name){
		List<User> users;
		if((users = document.doFind(name)) == null){
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.FINDNEAR);
			writer.writeInt(users.size());
			for(int i = 0; i < users.size(); i++){
				writer.writeUser(users.get(i));
			}
		}
	}
	
	public void getstate(Writer writer, String name){
		User user;
		if((user = document.doGetState(name)) == null){
			writer.writeKO();
		}
		else {
			writer.writeType(Protocol.GETSTATE);
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
				case Protocol.LOGIN :
					String n1 = reader.readName();
					this.login(writer, n1);
					writer.send();
					return true;

				case Protocol.MOVE :
					String n2 = reader.readName();
					int x = reader.readInt();
					int y = reader.readInt();
					this.move(writer, n2, x, y);
					writer.send();
					return true;
					
				case Protocol.CHGMODE :
					String n3 = reader.readName();
					Mode m = reader.readMode();
					this.chgmode(writer, n3, m);
					writer.send();
					return true;
				
				case Protocol.CHGRADIUS :
					String n4 = reader.readName();
					int rad = reader.readInt();
					this.chgradius(writer, n4, rad);
					writer.send();
					return true;
					
				case Protocol.CHGPREFLEVEL :
					String n5 = reader.readName();
					String pn1 = reader.readName();
					int value = reader.readInt();
					this.chgpreflevel(writer, n5, pn1, value);
					writer.send();
					return true;
					
				case Protocol.CHGPREFVISIBILITY :
					String n6 = reader.readName();
					String pn2 = reader.readName();
					boolean visibility = reader.readVisibility();
					this.chgprefvisibility(writer, n6, pn2, visibility);
					writer.send();
					return true;
				
				case Protocol.FINDNEAR :
					String n7 = reader.readName();
					this.findnear(writer, n7);
					writer.send();
					return true;
					
				case Protocol.GETSTATE :
					String n8 = reader.readName();
					this.getstate(writer, n8);
					writer.send();
					return true;
					
				default: return false; // connection jammed
			}
		} catch (IOException e) {
			return false;
		}
	}

}

package fr.ensisa.hassenforder.proximity.server;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void writeKO(){
		super.writeByte(Protocol.KO);
	}
	
	public void writeUser(User user){
		super.writeString(user.getName());
		super.writeInt(user.getX());
		super.writeInt(user.getY());
		super.writeInt(user.getRadius());
		super.writeMode(user.getMode());
		this.writePreferences(user);
		
	}
	
	public void writePreferences(User user){
		super.writeInt(user.getPreferences().size());
		for (int i=0; i<user.getPreferences().size(); i++){
			super.writeString(user.getPreferencesTab()[i].getName());
			super.writeInt(user.getPreferencesTab()[i].getLevel());
			super.writeBoolean(user.getPreferencesTab()[i].isVisibility());
		}
		
	}
	
	public void writeType(byte b){
		super.writeByte(b);
	}
	
	public void writeInt(int v){
		super.writeInt(v);
	}
	
}

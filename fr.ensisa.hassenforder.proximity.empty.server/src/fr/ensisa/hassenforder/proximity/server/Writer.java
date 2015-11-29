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
		super.writeBoolean(fr.ensisa.hassenforder.network.Protocol.KO);
	}
	
	public void writeUser(User user){
		super.writeString(user.getName());
		super.writeInt(user.getX());
		super.writeInt(user.getY());
		super.writeInt(user.getRadius());
		super.writeMode(user.getMode());
	}
}

package fr.ensisa.hassenforder.proximity.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Writer extends BasicAbstractWriter {

	public Writer(OutputStream outputStream) {
		super (outputStream);
	}

	public void writeLogin(String name) {
		super.writeByte(Protocol.LOGIN);
		super.writeString(name);
	}

	public void writeMove(String name, int x, int y) {
		super.writeByte(Protocol.MOVE);
		super.writeString(name);
		super.writeInt(x);
		super.writeInt(y);
	}

	public void writeChgMode(String name, Mode mode) {
		super.writeByte(Protocol.CHGMODE);
		super.writeString(name);
		super.writeMode(mode);
	}
	
}

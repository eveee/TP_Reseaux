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

	public void writeChgRad(String name, int radius) {
		super.writeByte(Protocol.CHGRADIUS);
		super.writeString(name);
		super.writeInt(radius);
	}

	public void writeChgPrefLevel(String name, String preference, int value) {
		super.writeByte(Protocol.CHGPREFLEVEL);
		super.writeString(name);
		super.writeString(preference);
		super.writeInt(value);
	}

	public void writeChgPrefVisibility(String name, String preference, boolean value) {
		super.writeByte(Protocol.CHGPREFVISIBILITY);
		super.writeString(name);
		super.writeString(preference);
		super.writeBoolean(value);
	}

	public void writeFind(String name) {
		super.writeByte(Protocol.FINDNEAR);
		super.writeString(name);
	}
	
}

package fr.ensisa.hassenforder.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import fr.ensisa.hassenforder.proximity.model.Mode;

public class BasicAbstractWriter {

	protected OutputStream outputStream;
	private ByteArrayOutputStream baos = new ByteArrayOutputStream ();
	private DataOutputStream output = new DataOutputStream (baos);

	public BasicAbstractWriter(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	protected void writeBoolean(boolean v) {
		try {
			if (v) output.writeInt(1);
			else output.writeInt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeInt(int v) {
		try {
			output.writeInt(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeLong(long v) {
		try {
			output.writeLong(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeString(String v) {
		try {
			output.writeUTF(v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void writeByte(byte b) {
		try {
			output.writeByte(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void writeMode(Mode mode){
		if (mode == Mode.HIDDEN) {
			writeByte(Protocol.HIDDEN);
		} else if (mode == Mode.VISIBLE) {
			writeByte(Protocol.VISIBLE);
		} else if (mode == Mode.OCCUPIED) {
			writeByte(Protocol.OCCUPIED);
		}
	}

	public void send() {
		byte [] message = baos.toByteArray();
		try {
			outputStream.write(message);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
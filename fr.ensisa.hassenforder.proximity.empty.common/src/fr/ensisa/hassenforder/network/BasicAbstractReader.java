package fr.ensisa.hassenforder.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.Preference;

public class BasicAbstractReader {

	protected DataInputStream inputStream;
	protected Byte type;

	public BasicAbstractReader(InputStream inputStream2) {
		this.inputStream = new DataInputStream (inputStream2);
	}

	protected boolean readBoolean() {
		try {
			int x = inputStream.readInt();
			if (x != 0) return true;
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	protected short readShort () {
		try {
			return inputStream.readShort();
		} catch (IOException e) {
			return 0;
		}
	}

	protected int readInt() {
		try {
			return inputStream.readInt();
		} catch (IOException e) {
			return 0;
		}
	}

	protected long readLong() {
		try {
			return inputStream.readLong();
		} catch (IOException e) {
			return 0;
		}
	}

	protected String readString() {
		try {
			return inputStream.readUTF();
		} catch (IOException e) {
			return "";
		}
	}
	
	protected Byte readByte() {
		try {
			return inputStream.readByte();
		} catch (IOException e) {
			return 0;
		}
	}

	public Byte getType() {
		return type;
	}

	public Mode readMode() {
		byte mode = this.readByte();
		if (mode == Protocol.HIDDEN) {
			return (Mode.HIDDEN);
		} else if (mode == Protocol.VISIBLE) {
			return (Mode.VISIBLE);
		} else {
			return (Mode.OCCUPIED);
		}
	}
	
	public Preference[] readPreferences() {
		int size = this.readInt();
		Preference preferences[] = new Preference[size];
		for(int i = 0; i < size; i++) {
			preferences[i] = new Preference(this.readString(), this.readInt(), this.readBoolean());
		}
		return preferences;
	}

}
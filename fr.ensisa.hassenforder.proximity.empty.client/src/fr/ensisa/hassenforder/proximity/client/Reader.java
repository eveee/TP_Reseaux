package fr.ensisa.hassenforder.proximity.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Preference;
import fr.ensisa.hassenforder.proximity.model.User;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Reader extends BasicAbstractReader {

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readByte();
		switch (type) {
			case 'l': type = Protocol.LOGIN;
		}
	}

	public User readUser() {
		return (new User(super.readString(), super.readInt(), super.readInt(), super.readInt(), super.readMode()));
	}

}

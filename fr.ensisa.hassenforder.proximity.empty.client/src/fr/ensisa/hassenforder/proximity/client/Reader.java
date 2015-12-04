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
	
	private User user;

	public Reader(InputStream inputStream) {
		super (inputStream);
		this.user = null;
	}

	public void receive() {
		type = readByte();
		this.user = null;
		switch (type) {
			case Protocol.KO: break;
			case Protocol.LOGIN: {
				this.user = readUser();
				this.user.addPreferences(this.readPreferences());;
			}
			
			default: break;
			}
	}
	

	public User readUser() {
		return (new User(super.readString(), super.readInt(), super.readInt(), super.readInt(), super.readMode()));
	}
	
	public User getUser(){
		return this.user;
	}

}

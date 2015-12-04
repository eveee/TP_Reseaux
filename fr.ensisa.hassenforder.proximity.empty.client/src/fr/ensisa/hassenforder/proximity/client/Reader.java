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
		switch (type) {
			case Protocol.KO:
				break;
			case Protocol.LOGIN: 	
				this.user = this.readUser();
				this.user.addPreferences(this.readPreferences());
				break;
			case Protocol.MOVE:
				this.user = this.readUser();
				break;
			case Protocol.CHGMODE:
				this.user = this.readUser();
				break;
			default: 
				break;
			}
	}
	

	public User readUser() {
		String name = super.readString();
		int x = super.readInt();
		int y = super.readInt();
		int radius = super.readInt();
		Mode mode = super.readMode();
		return (new User(name, x, y, radius, mode));
	}
	
	public User getUser(){
		return this.user;
	}

}

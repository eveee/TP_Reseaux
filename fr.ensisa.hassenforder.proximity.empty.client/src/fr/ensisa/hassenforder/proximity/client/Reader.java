package fr.ensisa.hassenforder.proximity.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.User;

public class Reader extends BasicAbstractReader {
	
	private User user;
	private List<User> users;

	public Reader(InputStream inputStream) {
		super (inputStream);
		this.user = null;
		this.users = new ArrayList<User>();
	}
	
	public User readUser() {
		String name = super.readString();
		int x = super.readInt();
		int y = super.readInt();
		int radius = super.readInt();
		Mode mode = super.readMode();
		User user = new User(name, x, y, radius, mode);
		user.addPreferences(this.readPreferences());
		return (user);
	}
	
	public User getUser() {
		return this.user;
	}
	
	public List<User> getUsers() {
		return this.users;
	}

	public void receive() {
		type = readByte();
		switch (type) {
			case Protocol.LOGIN: 	
				this.user = this.readUser();
				break;
			case Protocol.GETSTATE:
				this.user = this.readUser();
				break;
			case Protocol.FINDNEAR:
				this.users.clear();
				int size = super.readInt();
				for(int i = 0; i < size; i++) {
					this.users.add(this.readUser());
				}
				break;
			case Protocol.CHGMODE:
				this.user = this.readUser();
				break;
			case Protocol.MOVE:
				this.user = this.readUser();
				break;
			case Protocol.CHGRADIUS:
				this.user = this.readUser();
				break;
			case Protocol.CHGPREFLEVEL:
				this.user = this.readUser();
				break;
			case Protocol.CHGPREFVISIBILITY:
				this.user = this.readUser();
				break;
			case Protocol.KO:
				break;
			default: 
				break;
			}
	}

}

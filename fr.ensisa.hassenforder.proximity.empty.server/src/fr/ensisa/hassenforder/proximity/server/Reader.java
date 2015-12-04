package fr.ensisa.hassenforder.proximity.server;


import java.io.InputStream;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.proximity.model.Mode;

public class Reader extends BasicAbstractReader {

	public Reader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readByte();
		switch (type) {
		case 'l' : type = Protocol.LOGIN;
		}
	}
	
	public String readName(){
		return this.readString();
	}
	
	public int readInt(){
		return super.readInt();
	}
}

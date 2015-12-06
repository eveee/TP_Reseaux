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
		case Protocol.LOGIN : 	
			type = Protocol.LOGIN;
			break;
		case Protocol.MOVE : 
			type = Protocol.MOVE;
			break;
		case Protocol.CHGMODE :
			type = Protocol.CHGMODE;
			break;
		case Protocol.CHGRADIUS :
			type = Protocol.CHGRADIUS;
			break;
		}
	}
	
	public String readName(){
		return this.readString();
	}
	
	public int readInt(){
		return super.readInt();
	}
}

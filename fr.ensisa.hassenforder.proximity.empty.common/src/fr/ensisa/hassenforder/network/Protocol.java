package fr.ensisa.hassenforder.network;

public interface Protocol {

	public static final Object EXIT_TEXT = "exit";

	public static final int PROXIMITY_PORT_ID = 31000;
	
	public static final byte KO = 0;
	
	public static final byte VISIBLE = 1;
	
	public static final byte HIDDEN = 2;
	
	public static final byte OCCUPIED = 3;
	
	public static final byte LOGIN = 'l';
	
	public static final byte MOVE = 'm';
	
	public static final byte CHGMODE = 'c';
	
	public static final byte CHGRADIUS = 'r';
	
	public static final byte CHGPREFLEVEL = 'p';

}

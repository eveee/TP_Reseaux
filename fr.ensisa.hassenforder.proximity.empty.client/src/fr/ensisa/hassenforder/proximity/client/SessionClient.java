package fr.ensisa.hassenforder.proximity.client;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.proximity.model.Mode;
import fr.ensisa.hassenforder.proximity.model.User;

public class SessionClient {

	private Socket connection;
	
	public SessionClient(Socket connection) {
		this.connection = connection;
	}

	public User connect(String name) throws IOException {
		Writer writer = new Writer(this.connection.getOutputStream());
		Reader reader = new Reader(this.connection.getInputStream());
		writer.writeLogin(name);
		writer.send();
		reader.receive();
		return reader.getUser();
	}

	public void disconnect () {
		this.connection = null;
	}
	
	public User getState(String name) throws IOException {
		Writer writer = new Writer(this.connection.getOutputStream());
		Reader reader = new Reader(this.connection.getInputStream());
		writer.writeGetState(name);
		writer.send();
		reader.receive();
		return reader.getUser();
	}

	public List<User> findNear(String name) throws IOException {
		Writer writer = new Writer(this.connection.getOutputStream());
		Reader reader = new Reader(this.connection.getInputStream());
		writer.writeFind(name);
		writer.send();
		reader.receive();
		return reader.getUsers();
	}

	public boolean changeMode (String name, Mode mode) throws IOException {
		Writer writer = new Writer(this.connection.getOutputStream());
		Reader reader = new Reader(this.connection.getInputStream());
		writer.writeChgMode(name, mode);
		writer.send();
		reader.receive();
		return true;
	}

	public boolean move(String name, int x, int y) throws IOException {
		Writer writer = new Writer(this.connection.getOutputStream());
		Reader reader = new Reader(this.connection.getInputStream());
		writer.writeMove(name, x, y);
		writer.send();
		reader.receive();
		return true;
	}

	public boolean changeRadius(String name, int radius) throws IOException {
		Writer writer = new Writer(this.connection.getOutputStream());
		Reader reader = new Reader(this.connection.getInputStream());
		writer.writeChgRad(name, radius);
		writer.send();
		reader.receive();
		return true;
	}

	public boolean changePreferenceLevel(String name, String preference, int value) throws IOException {
		Writer writer = new Writer(this.connection.getOutputStream());
		Reader reader = new Reader(this.connection.getInputStream());
		writer.writeChgPrefLevel(name, preference, value);
		writer.send();
		reader.receive();
		return true;
	}

	public boolean changePreferenceVisibility(String name, String preference, boolean value) throws IOException {
		Writer writer = new Writer(this.connection.getOutputStream());
		Reader reader = new Reader(this.connection.getInputStream());
		writer.writeChgPrefVisibility(name, preference, value);
		writer.send();
		reader.receive();
		return true;
	}

}

package dm;

import java.net.Socket;

import server.Threads;

public class Client {
	private Socket socket;
	private Threads threads;
	private String player;

	public Client() {
	}

	public Client(Socket socket) {
		super();
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Threads getThreads() {
		return threads;
	}

	public void setThreads(Threads threads) {
		this.threads = threads;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Client [socket=" + socket + ", threads=" + threads + ", player=" + player + "]";
	}

}

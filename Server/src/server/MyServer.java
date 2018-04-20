package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dm.Client;
import dm.Player;

public class MyServer {

	static final int PORT = 8081;
	static final int TimeOut = 20000;

	private List<Client> listClients;

	ServerSocket serverSocket;

	public static void main(String[] args) {
		MyServer myServer = new MyServer();
	}

	public MyServer() {
		System.out.print(ServerSocketHelper.getIpAddress());
		listClients = new ArrayList<>();
		ServerThread serverThread = new ServerThread();
		serverThread.start();
	}

	public void addClient(Client client) {
		this.listClients.add(client);
	}

	private class ServerThread extends Thread {

		private Timer timer;

		public ServerThread() {
			timer = new Timer();
			timer.schedule(runnable, 0, TimeOut);
		}

		@Override
		public void run() {

			Socket socket = null;
			try {
				serverSocket = new ServerSocket(PORT);
				System.out.println("Connect: " + serverSocket.getLocalPort());

				Client client;
				Boolean flag = false;

				while (true) {
					System.out.println("AAAA");
					socket = serverSocket.accept();
					// for (int i = 0; i < listClients.size(); i++) {
					// if
					// (listClients.get(i).getSocket().getInetAddress().equals(socket.getInetAddress()))
					// {
					// flag = true;
					// }
					// }
					// if (!flag) {
					// System.out.println(socket.getInetAddress());
					// client = new Client(socket);
					// listClients.add(client);
					// }
					System.out.println(socket.getInetAddress());
					client = new Client(socket);
					listClients.add(client);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		TimerTask runnable = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Excute random" + listClients.size());
				if (listClients.size() > 1) {
					listClients = ServerSocketHelper.randomPlayer(listClients);
					System.out.println(listClients.size());
					int numberMatches = listClients.size() / 2;

					for (int i = 0; i < numberMatches; i++) {
						Client playerA = listClients.get(i);
						playerA.setPlayer(Player.PLAYERA);

						Client playerB = listClients.get(i + numberMatches);
						playerB.setPlayer(Player.PLAYERB);
						Threads threads = new Threads(playerA, playerB);
						System.out.println("i = " + i);
						threads.start();
					}

					for (int i = 0; i < numberMatches; ++i) {
						listClients.remove(i);
					}

				}
			}
		};
	}

}

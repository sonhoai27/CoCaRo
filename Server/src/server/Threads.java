package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import dm.Client;
import dm.Move;
import dm.Player;
import dm.STATUS;
import dm.StatusEmit;
import game.ChessBoard;

public class Threads extends Thread {

	private Client playerA;
	private Client playerB;
	private Client currentPlayer;
	private DataInputStream dataInputStream = null;
	private DataOutputStream dataOutputStream = null;
	private ChessBoard chessBoard;
	DB db;

	public Threads(Client playerA, Client playerB) {
		this.playerA = playerA;
		this.playerB = playerB;
		this.currentPlayer = playerA;
		this.chessBoard = new ChessBoard();
		db = DB.getInstance();
	}

	@Override
	public void run() {
		try {
			this.sendMsgClient(playerA, STATUS.TAO, new Move(10, 10));
			this.sendMsgClient(playerB, STATUS.TAO, new Move(10, 10));
			Move move;
			while (true) {
				dataInputStream = new DataInputStream(currentPlayer.getSocket().getInputStream());
				if (dataInputStream.available() > 0) {
					move = new Move(dataInputStream.readUTF());
					System.out.println("move = " + move.getRow() + move.getCol());
					System.out.println(currentPlayer.getPlayer());
					chessBoard.makeMove(move, currentPlayer.getPlayer());// di nuoc di cua
					// nguoichoi, va doi nguoc lai

					if (chessBoard.isOver()) {
						db.insert(playerA, playerB, chessBoard, chessBoard.getWinner());
						if (chessBoard.getWinner().equals(Player.PLAYERA)) {
							this.sendMsgClient(playerA, STATUS.THANG, new Move(10, 10));
							this.sendMsgClient(playerB, STATUS.THUA, move);
						} else {
							this.sendMsgClient(playerB, STATUS.THANG, new Move(10, 10));
							this.sendMsgClient(playerA, STATUS.THUA, move);
						}
					} else {
						if (currentPlayer.getPlayer().equals(Player.PLAYERA)) {
							System.out.println("PlayerA move");
							this.sendMsgClient(playerB, STATUS.CHOI, move);
						} else {
							System.out.println("PlayerB move");
							this.sendMsgClient(playerA, STATUS.CHOI, move);
						}
					}
				}

				daoNguoc();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendMsgClient(Client player, String status, Move move) throws IOException {
		dataOutputStream = new DataOutputStream(player.getSocket().getOutputStream());
		StatusEmit message = new StatusEmit(player, status, move);
		dataOutputStream.writeUTF(message.makeMessage());
		dataOutputStream.flush();
	}

	private void daoNguoc() {
		if (currentPlayer.equals(playerA)) {
			currentPlayer = playerB;
		} else {
			currentPlayer = playerA;
		}
	}

}

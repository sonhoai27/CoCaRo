package server;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import dm.Client;
import dm.Player;
import game.ChessBoard;

public class DB {
	private static DB instance = null;
	private final String url = "jdbc:mysql://localhost:3306/board";
	private final String user = "root";
	private final String password = "";
	private Connection connection = null;
	private Statement statement = null;

	private DB() {
		connection();
	}

	public static DB getInstance() {
		if (instance == null) {
			instance = new DB();
			return instance;
		} else {
			return instance;
		}
	}

	private void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(url, user, password);
			statement = (Statement) connection.createStatement();
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}

	public void query() throws SQLException {
		String query = "select * from board";
		ResultSet rs = (ResultSet) statement.executeQuery(query);
		while (rs.next()) {
			int numColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= numColumns; i++) {
				System.out.println("COLUMN " + i + " = " + rs.getObject(i));
			}
		}
	}

	public void insert(Client a, Client b, ChessBoard board, String winner) throws SQLException {
		String clientAIP = a.getSocket().getInetAddress().toString();
		String clientBIP = b.getSocket().getInetAddress().toString();
		String currentBoard = makeStringBoard(board);
		String query = "INSERT INTO board " + "(player_a, player_b, winner, board) VALUES " + "('" + clientAIP + "', '"
				+ clientBIP + "', '" + winner + "', '" + currentBoard + "')";
		statement.executeUpdate(query);
		System.out.println(query);

	}

	private String makeStringBoard(ChessBoard board) {
		String[][] bc = board.getBoard();
		String result = "[";
		for (int i = 0; i < board.getRowQty(); i++) {
			for (int y = 0; y < board.getColQty(); y++) {
				if (bc[i][y].equals(Player.PLAYERA)) {
					result += "a";
				} else if (bc[i][y].equals(Player.PLAYERB)) {
					result += "b";
				} else {
					result += "null";
				}
				if (y != board.getColQty() - 1) {
					result += ", ";
				}
			}
			result += "] ";
		}
		return result;
	}
}

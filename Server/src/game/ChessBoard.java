package game;

import dm.Move;
import dm.Player;

public class ChessBoard {
	public int colQty = 8;
	public int rowQty = 8;
	private String[][] board;
	private String winner = Player.NULL;
	private Boolean isOver = false;
	private String currentPlayer;
	private int need;
	private Move saveMove;

	public ChessBoard() {
		init();
	}

	public void init() {
		need = 4;
		isOver = false;
		saveMove = null;
		board = new String[rowQty][colQty];

		for (int i = 0; i < rowQty; i++) {
			for (int j = 0; j < colQty; j++) {
				board[i][j] = Player.NULL;
			}
		}
	}

	public Boolean makeMove(Move move, String player) {
		if (!moveOk(move)) {
			return false;
		}
		saveMove = move;
		currentPlayer = player;// lưu lại player hiện tại
		board[move.getRow()][move.getCol()] = player;
		if (isOver()) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean moveOk(Move move) {
		if (board[move.getRow()][move.getCol()] == Player.NULL) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean checkWin() {
		if (saveMove == null)
			return false;
		if (checkWinHorizontal(saveMove.getRow()) || checkWinVerical(saveMove.getCol())
				|| checkWindiagonalLeftBottom(saveMove.getRow(), saveMove.getCol())
				|| checkWindiagonalRightBottom(saveMove.getRow(), saveMove.getCol())) {
			return true;
		}

		return false;
	}

	public boolean isOver() {
		if (checkWin()) {
			isOver = true;
			return true;
		}

		int count = 0;
		for (int i = 0; i < rowQty; i++) {
			for (int j = 0; j < colQty; j++) {
				if (board[i][j] != Player.NULL)
					count++;
			}
		}
		if (count == 0) {
			isOver = true;
			return true;// trò chơi kết thúc
		}

		return false;
	}

	public String getWinner() {
		return winner;
	}

	public Boolean getIsOver() {
		return isOver;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean checkWinHorizontal(int row) {
		int dem = 0;
		for (int i = 1; i < rowQty; i++) {
			if (board[row][i] != board[row][i - 1]) {
				dem = 0;
			} else if (board[row][i] != Player.NULL) {
				dem++;
			}
			if (dem == need) {
				winner = board[row][i];
				return true;
			}
		}

		return false;
	}

	private boolean checkWinVerical(int col) {
		int dem = 0;
		for (int i = 1; i < rowQty; i++) {
			if (board[i][col] != board[i - 1][col]) {
				dem = 0;
			} else if (board[i][col] != Player.NULL) {
				dem++;
			}
			if (dem == need) {
				winner = board[i][col];
				return true;
			}
		}

		return false;
	}

	private boolean checkWindiagonalLeftBottom(int row, int col) {
		int rowStart, colStart;
		int i = 0;
		int count = 0;

		if (row > col) {
			rowStart = row - col;
			colStart = 0;
		} else {
			rowStart = 0;
			colStart = col - row;
		}

		while (rowStart + i + 1 < colQty && colStart + i + 1 < rowQty) {
			if (board[rowStart + i][colStart + i] == board[rowStart + i + 1][colStart + i + 1]
					&& board[rowStart + i][colStart + i] != Player.NULL) {
				count++;

				if (count == need) {
					winner = board[rowStart + i][colStart + i];
					return true;
				}
			} else {
				count = 0;
			}
			i++;
		}

		return false;
	}

	private boolean checkWindiagonalRightBottom(int row, int col) {
		int rowStart, colStart;
		int i = 0;
		int count = 0;

		if (row + col < colQty - 1) {
			colStart = row + col;
			rowStart = 0;
		} else {
			colStart = colQty - 1;
			rowStart = col + row - (colQty - 1);
		}

		while (colStart - i - 1 >= 0 && rowStart + i + 1 < colQty) {
			if (board[rowStart + i][colStart - i] == board[rowStart + i + 1][colStart - i - 1]
					&& board[rowStart + i][colStart - i] != Player.NULL) {
				count++;

				if (count == need) {
					winner = board[rowStart + i][colStart - i];
					return true;
				}
			} else {
				count = 0;
			}

			i++;
		}
		return false;
	}

	public int getColQty() {
		return colQty;
	}

	public void setColQty(int colQty) {
		this.colQty = colQty;
	}

	public int getRowQty() {
		return rowQty;
	}

	public void setRowQty(int rowQty) {
		this.rowQty = rowQty;
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

}

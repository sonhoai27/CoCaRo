package dm;

public class Move {

	private int row;
	private int col;

	public Move(int col, int row) {
		this.setCol(col);
		this.setRow(row);
	}

	public Move(String value) {
		String[] parts = value.split("-");
		this.setRow(Integer.parseInt(parts[0]));
		this.setCol(Integer.parseInt(parts[1]));
	}

	public Move() {
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}

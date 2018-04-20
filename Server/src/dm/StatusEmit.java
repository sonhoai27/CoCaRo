package dm;

public class StatusEmit {
	private Client client;
	private String status;
	private Move move;

	public StatusEmit() {
		// TODO Auto-generated constructor stub
	}

	public StatusEmit(Client client, String status, Move move) {
		this.move = move;
		this.client = client;
		this.status = status;
	}

	public String makeMessage() {
		String result = "";

		result += status + "";
		result += "-" + client.getPlayer();
		result += "-" + move.getRow();
		result += "-" + move.getCol();

		return result;
	}
}

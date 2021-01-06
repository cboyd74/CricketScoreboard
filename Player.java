package application;

public class Player {
	private String name;
	int board[];
	int score = 0;
	int totalThrows = 0;
	
	public Player(String name) {
		this.name = name;
		this.board = new int[7];
		for (int i = 0; i < board.length; i++) {
			board[i] = 0;
		}
	}
	
	public String getName() {
		return this.name;
	}
}

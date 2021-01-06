package application;

public class State {
	private int p1Score;
	private int p2Score;
	private int [][] board;
	private Player whosTurn;
	private int multiplier;
	private int currentThrows;
	
	public State(int p1Score, int p2Score, int[][]board, int multiplier, int currentThrows, Player whosTurn) {
		this.p1Score = p1Score;
		this.p2Score = p2Score;
		this.board = board;
		this.multiplier = multiplier;
		this.currentThrows = currentThrows;
		this.whosTurn = whosTurn;
	}
	
	public int p1Score() {
		return this.p1Score;
	}
	
	public int p2Score() {
		return this.p2Score;
	}
	
	public int[] p1Board() {
		return this.board[0];
	}
	
	public int[] p2Board() {
		return this.board[1];
	}
	
	public Player whosTurn() {
		return this.whosTurn;
	}
	
	public int multi() {
		return this.multiplier;
	}
	
	public int currThrows() {
		return this.currentThrows;
	}
}

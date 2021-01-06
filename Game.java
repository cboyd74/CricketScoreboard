package application;

import java.util.Arrays;
import java.util.Stack;

public class Game {
	static final int X1 = 1;
	static final int X2 = 2;
	static final int X3 = 3;
	Player playerOne;
	Player playerTwo;
	Player whosTurn;
	int currentThrows;
	int multiplier;
	Stack<State> prevStates;

	public Game(String p1, String p2) {
		// by the time this cstor is called, strings will not be null
		this.playerOne = new Player(p1);
		this.playerTwo = new Player(p2);
		whosTurn = playerOne;
		currentThrows = 0;
		multiplier = Game.X1; // start game in single multiplier
		this.prevStates = new Stack<State>();
	}

	public Player otherPlayer() {
		if (whosTurn.equals(playerOne)) {
			return this.playerTwo;
		} else {
			return this.playerOne;
		}
	}

	public void changeTurn() {
		currentThrows = 0;
		if (this.whosTurn.equals(playerOne)) {
			this.whosTurn = playerTwo;
		} else {
			this.whosTurn = playerOne;
		}
	}

	public int hit(int num, int multiplier) {
		this.saveState();
		int boardIndex = getBoardIndex(num);
		// System.out.println("boardIndex: " + boardIndex + " num : " + num);
		currentThrows++;
		if (boardIndex == -1) {
			return 0;
		}
		for (int i = 0; i < multiplier; i++) {
			if (whosTurn.board[boardIndex] >= 3 && otherPlayer().board[boardIndex] < 3) {
				score(num);
			}
			this.whosTurn.board[boardIndex]++;
			if (checkForWin()) {
				return 1;
			}
		}
		if (currentThrows == 3) {
			changeTurn();
		}
		return 0;
	}

	public void miss() {
		saveState();
		currentThrows++;
		if (currentThrows == 3) {
			changeTurn();
		}
	}

	public boolean checkForWin() {
		boolean won = true;
		for (int i = 0; i < whosTurn.board.length; i++) {
			if (whosTurn.board[i] < 3) {
				won = false;
				break;
			}
		}
		if (won == true) {
			if (whosTurn.score <= otherPlayer().score) {
				won = false;
			}
		}
		return won;
	}

	public int getBoardIndex(int num) {
		int index = -1;
		switch (num) {
		case 20:
			index = 0;
			break;
		case 19:
			index = 1;
			break;
		case 18:
			index = 2;
			break;
		case 17:
			index = 3;
			break;
		case 16:
			index = 4;
			break;
		case 15:
			index = 5;
			break;
		case 25:
			index = 6;
			break;
		}
		return index;
	}

	public void score(int num) {
		this.whosTurn.score += num;
	}

	private void saveState() {
		int[][] board = new int[2][7];
		for (int i = 0; i < 7; i++) {
			board[0][i] = playerOne.board[i];
			board[1][i] = playerTwo.board[i];
		}
		this.prevStates
				.push(new State(playerOne.score, playerTwo.score, board, multiplier, currentThrows, this.whosTurn));
	}

	public void restoreState() {
		if (!prevStates.isEmpty()) {
			State prev = prevStates.pop();
			this.playerOne.score = prev.p1Score();
			this.playerTwo.score = prev.p2Score();
			this.playerOne.board = prev.p1Board();
			this.playerTwo.board = prev.p2Board();
			this.whosTurn = prev.whosTurn();
			this.multiplier = prev.multi();
			this.currentThrows = prev.currThrows();
		}
	}

	//TODO: beable to save a game to load up in the future
	public int saveGame() {
		return 0;
	}

	//TODO: be able to load a game that was saved
	//      will need a data structure for saving more than one game?
	public int loadGame() {
		return 0;
	}
}

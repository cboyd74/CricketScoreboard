package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreboardTests {

//	@Test
//	void testBasicScore() {
//		Game game = new Game("PlayerOne", "PlayerTwo");
//		Player player1 = game.playerOne;
//		Player player2 = game.playerTwo;
//
//		game.hit(20, 3);
//		game.hit(20, 1);
//		game.hit(20, 1);
//		
//		if (game.whosTurn.score != 40) {
//			fail("testBasicScore: incorrect score");
//		}
//	}

	@Test
	void testOtherPlayer() {
		Game game = new Game("plyr1", "plyr2");
		// game.changeTurn();
		if (game.otherPlayer() != game.playerTwo) {
			fail();
		}
	}

	//@Test
	void testComplexScore() {
		Game game = new Game("PlayerOne", "PlayerTwo");
		Player player1 = game.playerOne;
		Player player2 = game.playerTwo;

		game.hit(20, 3);
		game.hit(19, 2);
		game.hit(18, 1);
		System.out.println(game.whosTurn.score);
		game.changeTurn();
		game.hit(17, 3);
		game.hit(16, 2);
		game.hit(25, 1);
		System.out.println(game.whosTurn.score);
		game.changeTurn();
		game.hit(20, 2);
		game.hit(19, 2);
		game.hit(18, 1);
		System.out.println(game.whosTurn.score);
		game.changeTurn();
		game.hit(17, 2);
		game.hit(16, 2);
		game.hit(25, 1);
		System.out.println(game.whosTurn.score);
		game.changeTurn();
		game.miss();
		game.hit(18, 1);
		game.hit(18, 1);
		System.out.println(game.whosTurn.score);
		game.changeTurn();
		game.miss();
		game.hit(25, 1);
		game.hit(25, 1);
		System.out.println(game.whosTurn.score);

		System.out.println(player1.score + "   " + player2.score);

		if (player1.score != 77 || player2.score != 75) {
			fail("testComplexScore: incorrect score");
		}

	}

	@Test
	void testDoublesIssue() {
		Game game = new Game("one", "two");
		game.hit(20, 2);
		game.hit(20, 2);
		
		System.out.println(game.playerOne.board[0]);
		if (game.playerOne.score!=20) {
			fail();
		}
	}

}

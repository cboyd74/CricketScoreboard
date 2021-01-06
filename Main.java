package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {
	private static final int GAME_HEIGHT = 490;
	private static final int GAME_WIDTH = 450;
	private static final int SECOND_HEIGHT = 400;
	private static final int SECOND_WIDTH = 500;

	private String playerOne;
	private String playerTwo;

	Game game;
	Label errorMessage;

	Stage window;

	// Opening scene objects
	Scene firstScene;
	Label enterPlayers;
	Label versusLabel;
	TextField usr1Name;
	TextField usr2Name;
	VBox usernames;
	Button playGame;
	VBox firstCenter;
	VBox getNames;
	Text invalidName;
	Button load;

	// Game scene objects
	Scene gameScene;
	VBox allTop;
	HBox top;
	HBox midTop;
	HBox bottomTop;
	HBox missUndo;
	Button singleShot;
	Button doubleShot;
	Button tripleShot;
	ImageView dart1;
	ImageView dart2;
	ImageView dart3;
	HBox darts;
	VBox board;
	Button miss;
	Button undo;
	Button twenty;
	Button nineteen;
	Button eighteen;
	Button seventeen;
	Button sixteen;
	Button fifteen;
	Button bull;
	Image exitSign;
	Image saveSign;
	Button exit;
	Button save;
	HBox veryBottom;

	Label player1;
	HBox p1HBox;
	VBox p1Hits;
	Label p1Score;
	ImageView p120;
	ImageView p119;
	ImageView p118;
	ImageView p117;
	ImageView p116;
	ImageView p115;
	ImageView p1Bull;
	Label player2;
	HBox p2HBox;
	VBox p2Hits;
	Label p2Score;
	ImageView p220;
	ImageView p219;
	ImageView p218;
	ImageView p217;
	ImageView p216;
	ImageView p215;
	ImageView p2Bull;
	VBox gameBoard;

	// Winning scene objects
	Scene winningScene;
	BorderPane winnerPane;
	VBox winnerVBox;
	Label winner;
	Button playAgain;
	Button newGame;

	// All images used
	Image oneHit;
	Image twoHit;
	Image closed;
	Image empty;
	Image dart;
	Image cricket;
	Image saveImg;

	// Save Scene objects
	VBox saveExit;
	Scene saveScene;
	TextField name;
	TextField direct;
	Button saveButton;
	Button saveExitButton;

	public static void main(String args[]) {
		launch(args);
	}

	public void start(Stage PrimaryStage) throws FileNotFoundException {
		window = PrimaryStage;
		window.setTitle("Cricket");

		// Load in all images
		oneHit = new Image("application/one_hit.png");
		twoHit = new Image("application/two_hit.png");
		closed = new Image("application/three_hit.png");
		empty = new Image("application/empty.png");
		dart = new Image("application/dart.png");
		exitSign = new Image("application/exit_sign.png");
		saveSign = new Image("application/save_sign.png");
		cricket = new Image("application/cricket_3.png");
		saveImg = new Image("application/save.png");

		initSaveScene();
		initUsernameScene();
		window.setScene(this.firstScene);
		window.setHeight(Main.SECOND_HEIGHT);
		window.setWidth(Main.SECOND_WIDTH);
		window.show();
	}

	private void initUsernameScene() {
		ImageView logo = new ImageView(cricket);
		BorderPane.setAlignment(logo, Pos.CENTER);

		usr1Name = new TextField("Player One");
		usr1Name.setMaxWidth(200);
		usr1Name.setStyle("-fx-background-color: rgb(61,61,61)");
		usr1Name.setAlignment(Pos.CENTER);
		usr1Name.setFont(new Font("Courier", 20));

		versusLabel = new Label("   vs  ");
		versusLabel.setFont(new Font("Courier", 20));
		versusLabel.setStyle("-fx-text-fill: rgb(175,255,163)");

		usr2Name = new TextField("Player Two");
		usr2Name.setMaxWidth(200);
		usr2Name.setStyle("-fx-background-color: rgb(61,61,61)");
		usr2Name.setAlignment(Pos.CENTER);
		usr2Name.setFont(new Font("Courier", 20));

		usernames = new VBox();
		usernames.getChildren().addAll(usr1Name, versusLabel, usr2Name);

		invalidName = new Text("Invalid Username");
		invalidName.setFill(Color.RED);
		invalidName.setVisible(false);

		playGame = new Button("Start Game");
		playGame.setStyle("-fx-background-color: rgb(51,51,51)");
		playGame.setTextFill(Color.web("rgb(175,255,163)"));
		playGame.setPrefWidth(250);
		playGame.setMinWidth(250);
		playGame.setFont(new Font("Courier", 20));
		playGame.setBorder(new Border(new BorderStroke(Color.web("rgb(175,255,163)"), BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, BorderStroke.THIN, Insets.EMPTY)));

		load = new Button("Load");
		load.setStyle("-fx-background-color: rgb(51,51,51)");
		load.setTextFill(Color.web("rgb(175,255,163)"));
		load.setPrefWidth(250);
		load.setMinWidth(250);
		load.setFont(new Font("Courier", 20));
		load.setBorder(new Border(new BorderStroke(Color.web("rgb(175,255,163)"), BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, BorderStroke.THIN, Insets.EMPTY)));
		firstCenter = new VBox(15);
		firstCenter.getChildren().addAll(usernames, playGame, load, invalidName);

		getNames = new VBox(15, logo, firstCenter);
		getNames.setAlignment(Pos.CENTER);
		getNames.setStyle("-fx-background-color: rgb(80,80,80)");

		usernames.setAlignment(Pos.CENTER);
		firstCenter.setAlignment(Pos.CENTER);

		firstScene = new Scene(getNames);
		playGame.setOnAction(this);
	}

	private void initGameScene() {
		dart1 = new ImageView(dart);
		dart2 = new ImageView(dart);
		dart3 = new ImageView(dart);
		darts = new HBox(10);
		darts.getChildren().addAll(dart1, dart2, dart3);
		darts.setAlignment(Pos.CENTER);

		player1 = new Label();
		player1.setFont(new Font("Courier", 20));
		player1.setStyle("-fx-text-fill: rgb(81,81,81)");

		player2 = new Label();
		player2.setFont(new Font("Courier", 20));
		player2.setStyle("-fx-text-fill: rgb(81,81,81)");

		p1HBox = new HBox(5, player1);
		p1HBox.setAlignment(Pos.CENTER);
		p2HBox = new HBox(5, player2);
		p2HBox.setAlignment(Pos.CENTER);
		top = new HBox(40);
		top.setAlignment(Pos.CENTER);
		top.getChildren().addAll(p1HBox, darts, p2HBox);

		singleShot = new Button("x1");
		singleShot.setFont(new Font("Courier", 12));
		singleShot.setTextFill(Color.web("rgb(175,255,163)"));
		singleShot.setStyle("-fx-background-color: rgb(71,71,71)");
		doubleShot = new Button("x2");
		doubleShot.setFont(new Font("Courier", 12));
		doubleShot.setTextFill(Color.web("rgb(175,255,163)"));
		doubleShot.setStyle("-fx-background-color: rgb(71,71,71)");
		tripleShot = new Button("x3");
		tripleShot.setFont(new Font("Courier", 12));
		tripleShot.setTextFill(Color.web("rgb(175,255,163)"));
		tripleShot.setStyle("-fx-background-color: rgb(41,41,41)");
		midTop = new HBox(15);
		midTop.setAlignment(Pos.CENTER);
		midTop.getChildren().addAll(singleShot, doubleShot, tripleShot);

		miss = new Button("Miss");
		miss.setFont(new Font("Courier", 13));
		miss.setTextFill(Color.web("rgb(175,255,163)"));
		miss.setStyle("-fx-background-color: rgb(61,61,61)");
		undo = new Button("Undo");
		undo.setFont(new Font("Courier", 13));
		undo.setTextFill(Color.web("rgb(175,255,163)"));
		undo.setStyle("-fx-background-color: rgb(61,61,61)");
		missUndo = new HBox(5, miss, undo);
		missUndo.setAlignment(Pos.CENTER);
		p1Score = new Label();
		p1Score.setFont(new Font("Courier", 40));
		p1Score.setStyle("-fx-text-fill: rgb(100,100,100)");
		p2Score = new Label();
		p2Score.setFont(new Font("Courier", 40));
		p2Score.setStyle("-fx-text-fill: rgb(100,100,100)");

		VBox topButtons = new VBox(5, midTop, missUndo);
		bottomTop = new HBox(50);
		bottomTop.setAlignment(Pos.CENTER);
		bottomTop.getChildren().addAll(p1Score, topButtons, p2Score);

		VBox box = new VBox(5);
		box.minHeight(15);
		box.setPrefHeight(15);
		allTop = new VBox(5, top, bottomTop, box);

		DropShadow shad = new DropShadow();
		shad.setColor(Color.BLACK);
		shad.setBlurType(BlurType.ONE_PASS_BOX);
		twenty = new Button("20");
		twenty.setFont(new Font("Courier", 18));
		twenty.setStyle("-fx-background-color: rgb(100,100,100)");
		twenty.setTextFill(Color.web("rgb(175,255,163)"));
		twenty.setEffect(shad);
		nineteen = new Button("19");
		nineteen.setFont(new Font("Courier", 18));
		nineteen.setStyle("-fx-background-color: rgb(100,100,100)");
		nineteen.setTextFill(Color.web("rgb(175,255,163)"));
		nineteen.setEffect(shad);
		eighteen = new Button("18");
		eighteen.setFont(new Font("Courier", 18));
		eighteen.setStyle("-fx-background-color: rgb(100,100,100)");
		eighteen.setTextFill(Color.web("rgb(175,255,163)"));
		eighteen.setEffect(shad);
		seventeen = new Button("17");
		seventeen.setFont(new Font("Courier", 18));
		seventeen.setStyle("-fx-background-color: rgb(100,100,100)");
		seventeen.setTextFill(Color.web("rgb(175,255,163)"));
		seventeen.setEffect(shad);
		sixteen = new Button("16");
		sixteen.setFont(new Font("Courier", 18));
		sixteen.setStyle("-fx-background-color: rgb(100,100,100)");
		sixteen.setTextFill(Color.web("rgb(175,255,163)"));
		sixteen.setEffect(shad);
		fifteen = new Button("15");
		fifteen.setFont(new Font("Courier", 18));
		fifteen.setStyle("-fx-background-color: rgb(100,100,100)");
		fifteen.setTextFill(Color.web("rgb(175,255,163)"));
		fifteen.setEffect(shad);
		bull = new Button("25");
		bull.setFont(new Font("Courier", 18));
		bull.setStyle("-fx-background-color: rgb(100,100,100)");
		bull.setTextFill(Color.web("rgb(175,255,163)"));
		bull.setEffect(shad);

		p1Hits = new VBox(10);
		p120 = new ImageView(empty);
		p119 = new ImageView(empty);
		p118 = new ImageView(empty);
		p117 = new ImageView(empty);
		p116 = new ImageView(empty);
		p115 = new ImageView(empty);
		p1Bull = new ImageView(empty);
		p2Hits = new VBox(10);
		p220 = new ImageView(empty);
		p219 = new ImageView(empty);
		p218 = new ImageView(empty);
		p217 = new ImageView(empty);
		p216 = new ImageView(empty);
		p215 = new ImageView(empty);
		p2Bull = new ImageView(empty);
		int boardSpacing = 75;
		HBox twenties = new HBox(boardSpacing, p120, twenty, p220);
		twenties.setAlignment(Pos.CENTER);
		HBox nineteens = new HBox(boardSpacing, p119, nineteen, p219);
		nineteens.setAlignment(Pos.CENTER);
		HBox eighteens = new HBox(boardSpacing, p118, eighteen, p218);
		eighteens.setAlignment(Pos.CENTER);
		HBox seventeens = new HBox(boardSpacing, p117, seventeen, p217);
		seventeens.setAlignment(Pos.CENTER);
		HBox sixteens = new HBox(boardSpacing, p116, sixteen, p216);
		sixteens.setAlignment(Pos.CENTER);
		HBox fifteens = new HBox(boardSpacing, p115, fifteen, p215);
		fifteens.setAlignment(Pos.CENTER);
		HBox bulls = new HBox(boardSpacing, p1Bull, bull, p2Bull);
		bulls.setAlignment(Pos.CENTER);
		board = new VBox(5);
		board.setAlignment(Pos.CENTER);
		HBox hbox = new HBox();
		hbox.prefHeight(10);
		hbox.setMinHeight(10);
		board.getChildren().addAll(twenties, nineteens, eighteens, seventeens, sixteens, fifteens, bulls, hbox);

		allTop.setStyle("-fx-background-color: rgb(41,41,41)");
		allTop.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
				BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
				CornerRadii.EMPTY, BorderStroke.THIN, Insets.EMPTY)));

		exit = new Button();
		exit.setPrefHeight(exitSign.getHeight());
		exit.setMinHeight(exitSign.getHeight());
		exit.setPrefWidth(exitSign.getWidth());
		exit.setMinWidth(exitSign.getWidth());
		exit.setBackground(new Background(new BackgroundImage(exitSign, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		save = new Button();
		save.setPrefHeight(saveSign.getHeight());
		save.setMinHeight(saveSign.getHeight());
		save.setPrefWidth(saveSign.getWidth());
		save.setMinWidth(saveSign.getWidth());
		save.setBackground(new Background(new BackgroundImage(saveSign, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		saveExit = new VBox(5, save, exit);
		saveExit.setAlignment(Pos.CENTER);

		veryBottom = new HBox(5, saveExit);
		veryBottom.setAlignment(Pos.CENTER_RIGHT);
		veryBottom.setStyle("-fx-background-color: rgb(41,41,41)");
		veryBottom.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
				BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
				CornerRadii.EMPTY, BorderStroke.THIN, Insets.EMPTY)));

		gameBoard = new VBox(15);
		gameBoard.getChildren().addAll(allTop, board, veryBottom);

		errorMessage = new Label();
		errorMessage.setVisible(false);
		BorderPane.setAlignment(errorMessage, Pos.CENTER);
		errorMessage.setTextFill(Color.RED);
		errorMessage.setFont(new Font("Courier", 16));
		// ameBoard.setBottom(errorMessage);

		gameBoard.setStyle("-fx-background-color: rgb(56,56,56)");
		gameScene = new Scene(gameBoard);

		singleShot.setOnAction(this);
		doubleShot.setOnAction(this);
		tripleShot.setOnAction(this);
		twenty.setOnAction(this);
		nineteen.setOnAction(this);
		eighteen.setOnAction(this);
		seventeen.setOnAction(this);
		sixteen.setOnAction(this);
		fifteen.setOnAction(this);
		bull.setOnAction(this);
		miss.setOnAction(this);
		undo.setOnAction(this);
		exit.setOnAction(this);
		save.setOnAction(this);
	}

	private void initWinnerScene() {
		winnerPane = new BorderPane();
//		winnerPane.setStyle("-fx-text-fill: rgb(255,251,185)");
		winner = new Label();
		playAgain = new Button("Play Again");
		playAgain.setAlignment(Pos.CENTER);
		newGame = new Button("New Game");
		winnerVBox = new VBox(5);
		winnerVBox.setStyle("-fx-text-fill: rgb(255,251,185)");
		winnerVBox.getChildren().addAll(winner, playAgain, newGame);
		winnerVBox.setAlignment(Pos.CENTER);
		winnerPane.setCenter(winnerVBox);
		winningScene = new Scene(winnerPane);

		playAgain.setOnAction(this);
		newGame.setOnAction(this);
	}

	private void initSaveScene() {
		ImageView img = new ImageView(saveImg);

		name = new TextField("Name");
		name.setMaxWidth(200);
		name.setStyle("-fx-background-color: rgb(61,61,61)");
		name.setAlignment(Pos.CENTER);
		name.setFont(new Font("Courier", 20));
		direct = new TextField("./desired/path/");
		direct.setMaxWidth(200);
		direct.setStyle("-fx-background-color: rgb(61,61,61)");
		direct.setAlignment(Pos.CENTER);
		direct.setFont(new Font("Courier", 20));

		saveButton = new Button("Save");
		saveButton.setStyle("-fx-background-color: rgb(51,51,51)");
		saveButton.setTextFill(Color.web("rgb(175,255,163)"));
		saveButton.setPrefWidth(250);
		saveButton.setMinWidth(250);
		saveButton.setFont(new Font("Courier", 20));
		saveButton.setBorder(new Border(new BorderStroke(Color.web("rgb(175,255,163)"), BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, BorderStroke.THIN, Insets.EMPTY)));

		saveExitButton = new Button("Save & Exit");
		saveExitButton.setStyle("-fx-background-color: rgb(51,51,51)");
		saveExitButton.setTextFill(Color.web("rgb(175,255,163)"));
		saveExitButton.setPrefWidth(250);
		saveExitButton.setMinWidth(250);
		saveExitButton.setFont(new Font("Courier", 20));
		saveExitButton.setBorder(new Border(new BorderStroke(Color.web("rgb(175,255,163)"), BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, BorderStroke.THIN, Insets.EMPTY)));

		VBox saveContain = new VBox(25, img, name, direct, saveButton, saveExitButton);
		saveContain.setAlignment(Pos.CENTER);
		saveContain.setStyle("-fx-background-color: rgb(80,80,80)");

		saveScene = new Scene(saveContain);

		saveButton.setOnAction(this);
		saveExitButton.setOnAction(this);
	}

	public void handle(ActionEvent arg0) {
		if (arg0.getSource() == playGame) {
			handleGetNames();
		}
		if (arg0.getSource() == twenty) {
			handleHit(20);
		}
		if (arg0.getSource() == nineteen) {
			handleHit(19);
		}
		if (arg0.getSource() == eighteen) {
			handleHit(18);
		}
		if (arg0.getSource() == seventeen) {
			handleHit(17);
		}
		if (arg0.getSource() == sixteen) {
			handleHit(16);
		}
		if (arg0.getSource() == fifteen) {
			handleHit(15);
		}
		if (arg0.getSource() == bull) {
			handleHit(25);
		}
		if (arg0.getSource() == miss) {
			handleMiss();
			updateBoard();
		}
		if (arg0.getSource() == playAgain) {
			handlePlayAgain();
		}
		if (arg0.getSource() == newGame) {

		}
		if (arg0.getSource() == undo) {
			game.restoreState();
			updateBoard();
		}
		if (arg0.getSource() == singleShot) {
			this.changeMultiplier(1);
			updateBoard();
		}
		if (arg0.getSource() == doubleShot) {
			this.changeMultiplier(2);
			updateBoard();
		}
		if (arg0.getSource() == tripleShot) {
			this.changeMultiplier(3);
			updateBoard();
		}
		if (arg0.getSource() == exit) {
			System.out.println("Exit");
			System.exit(0);
		}
		if (arg0.getSource() == save) {
			System.out.println("Save");
			window.setScene(saveScene);
		}
		if (arg0.getSource() == saveButton) {
			// handle save game
			// return to intro scene
		}
		if (arg0.getSource() == saveExitButton) {
			// handle save game
			// system.exit
		}
	}

	public void handleGetNames() {
		this.playerOne = usr1Name.getText().trim();
		this.playerTwo = usr2Name.getText().trim();

		if (playerOne == null || playerTwo.equals("") || playerTwo == null || playerTwo.equals("")
				|| playerOne.length() > 10 || playerTwo.length() > 10) {
			invalidName.setVisible(true);
			return;
		} else {
			invalidName.setVisible(false);
		}
		int len = playerOne.length();
		for (int i = 0; i < 10 - len; i++) {
			if (i % 2 == 0) { // even
				this.playerOne = " " + this.playerOne;
			} else {
				this.playerOne += " ";
			}
		}

		len = playerTwo.length();
		for (int i = 0; i < 10 - len; i++) {
			if (i % 2 == 0) {
				this.playerTwo = " " + playerTwo;
			} else {
				this.playerTwo += " ";
			}
		}
		initGameScene();
		initGame();
	}

	public void handleMiss() {
		game.miss();
		this.updateBoard();
		errorMessage.setVisible(false);
	}

	public void handleHit(int hitNum) {
		if (game.hit(hitNum, game.multiplier) == 1) {
			winner();
		}
		this.updateBoard();
		errorMessage.setVisible(false);
	}

	public void handlePlayAgain() {
		initGame();
	}

	private void changeMultiplier(int mult) {
		if (mult < 1 || mult > 3)
			return;
		game.multiplier = mult;
	}

	public void initGame() {
		this.game = new Game(playerOne, playerTwo);

		p1Hits.setMinWidth(180);
		p1Hits.setMaxWidth(180);
		p1Hits.setPrefWidth(180);

		p2Hits.setMinWidth(180);
		p2Hits.setMaxWidth(180);
		p2Hits.setPrefWidth(180);

		player1.setText(" " + game.playerOne.getName() + "");
		p1Score.setText("" + game.playerOne.score);
		player2.setText("" + game.playerTwo.getName() + " ");
		p2Score.setText("" + game.playerTwo.score);

		window.setScene(gameScene);
		window.setHeight(Main.GAME_HEIGHT);
		window.setWidth(Main.GAME_HEIGHT);
		updateBoard();
	}

	private void updateBoard() {
		// Updating the scores
		p1Score.setText(this.scoreToString(this.game.playerOne.score));
		p2Score.setText(this.scoreToString(this.game.playerTwo.score));

		// Updating the amount of darts left (circles)
		if (game.currentThrows == 0) {
			dart1.setVisible(true);
			dart2.setVisible(true);
			dart3.setVisible(true);
		} else if (game.currentThrows == 1) {
			dart1.setVisible(false);
			dart2.setVisible(true);
			dart3.setVisible(true);
		} else if (game.currentThrows == 2) {
			dart1.setVisible(false);
			dart2.setVisible(false);
			dart3.setVisible(true);
		}

		// Update marks
		p120.setImage(this.getMark(game.playerOne.board[0]));
		p119.setImage(this.getMark(game.playerOne.board[1]));
		p118.setImage(this.getMark(game.playerOne.board[2]));
		p117.setImage(this.getMark(game.playerOne.board[3]));
		p116.setImage(this.getMark(game.playerOne.board[4]));
		p115.setImage(this.getMark(game.playerOne.board[5]));
		p1Bull.setImage(this.getMark(game.playerOne.board[6]));
		p220.setImage(this.getMark(game.playerTwo.board[0]));
		p219.setImage(this.getMark(game.playerTwo.board[1]));
		p218.setImage(this.getMark(game.playerTwo.board[2]));
		p217.setImage(this.getMark(game.playerTwo.board[3]));
		p216.setImage(this.getMark(game.playerTwo.board[4]));
		p215.setImage(this.getMark(game.playerTwo.board[5]));
		p2Bull.setImage(this.getMark(game.playerTwo.board[6]));

		// Update whose turn
		Player whosTurn = game.whosTurn;
		if (whosTurn.equals(game.playerOne)) {
			player1.setStyle("-fx-text-fill: rgb(175,255,163)");
			player2.setStyle("-fx-text-fill: rgb(81,81,81)");
		} else {
			player1.setStyle("-fx-text-fill: rgb(81,81,81)");
			player2.setStyle("-fx-text-fill: rgb(175,255,163)");
		}

		// Multiplier buttons
		if (game.multiplier == Game.X1) {
			singleShot.setTextFill(Color.web("rgb(71,71,71)"));
			singleShot.setStyle("-fx-background-color: rgb(175,255,163)");
			doubleShot.setFont(new Font("Courier", 12));
			doubleShot.setTextFill(Color.web("rgb(175,255,163)"));
			doubleShot.setStyle("-fx-background-color: rgb(71,71,71)");
			tripleShot.setTextFill(Color.web("rgb(175,255,163)"));
			tripleShot.setStyle("-fx-background-color: rgb(71,71,71)");
		} else if (game.multiplier == Game.X2) {
			doubleShot.setTextFill(Color.web("rgb(71,71,71)"));
			doubleShot.setStyle("-fx-background-color: rgb(175,255,163)");
			singleShot.setTextFill(Color.web("rgb(175,255,163)"));
			singleShot.setStyle("-fx-background-color: rgb(71,71,71)");
			tripleShot.setTextFill(Color.web("rgb(175,255,163)"));
			tripleShot.setStyle("-fx-background-color: rgb(71,71,71)");
		} else if (game.multiplier == Game.X3) {
			tripleShot.setTextFill(Color.web("rgb(71,71,71)"));
			tripleShot.setStyle("-fx-background-color: rgb(175,255,163)");
			doubleShot.setTextFill(Color.web("rgb(175,255,163)"));
			doubleShot.setStyle("-fx-background-color: rgb(71,71,71)");
			singleShot.setTextFill(Color.web("rgb(175,255,163)"));
			singleShot.setStyle("-fx-background-color: rgb(71,71,71)");
		}

	}

	private String scoreToString(int score) {
		String returnStr = "" + score;
		if (returnStr.length() > 4)
			return null;
		int len = 3 - returnStr.length();
		for (int i = 0; i < len; i++) {
			returnStr = "0" + returnStr;
		}
		return returnStr;
	}

	private Image getMark(int mark) {
		if (mark == 1) {
			return oneHit;
		} else if (mark == 2) {
			return twoHit;
		} else if (mark >= 3) {
			return closed;
		}
		return empty;
	}

	public void winner() {
		initWinnerScene();
		winner.setText(game.whosTurn.getName() + " WINS!!!");
		winner.setFont(new Font("Courier", 30));
		window.setHeight(200);
		window.setWidth(400);
		window.setScene(winningScene);

	}
}

package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.TicTacToeGame;

public class DrawView extends BorderPane implements Observer {

	private TicTacToeGame theGame;

	private Canvas canvas;
	private GraphicsContext gc;
	private Pane root;
	private String top;
	private Integer whatRow;
	private Integer whatCol;
	private int scrx;
	private int scry;

	public DrawView(TicTacToeGame TicTacToeGame) {

		theGame = TicTacToeGame;

		// Create the Canvas
		canvas = new Canvas(510, 340);
		// Set the width of the Canvas
		canvas.setWidth(510);
		// Set the height of the Canvas
		canvas.setHeight(340);

		// Get the graphics context of the canvas
		gc = canvas.getGraphicsContext2D();

		// Draw a Text
		gc.setStroke(Color.GRAY);
		gc.setFill(Color.GRAY);
		gc.setFont(new Font ("Abadi MT Condensed Extra Bold", 24));
		top = "Make Move";
		gc.fillText(top, 30, 35);

		// Create the Pane
		root = new Pane();

		//most Important
		setCenter(canvas);

		initializePanel();
	}

	private void initializePanel() {
		// TODO Auto-generated method stub
		// Add the Canvas to the Pane
		root.getChildren().add(canvas);

		//drawing the lines
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		drawBoard();

		//most Important
		setCenter(canvas);
		EventHandler<MouseEvent> handler = new ButtonListener();
		canvas.setOnMousePressed(handler);

	}

	private void drawBoard() {
		// TODO Auto-generated method stub

		gc.setStroke(Color.GRAY);
		gc.setFill(Color.GRAY);
		gc.setFont(new Font ("Abadi MT Condensed Extra Bold", 24));
		gc.fillText(top, 30, 35);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(22, 70, 70, 70);
		gc.strokeRect(92, 70, 70, 70);
		gc.strokeRect(162, 70, 70, 70);

		gc.strokeRect(22, 140, 70, 70);
		gc.strokeRect(92, 140, 70, 70);
		gc.strokeRect(162, 140, 70, 70);

		gc.strokeRect(22, 210, 70, 70);
		gc.strokeRect(92, 210, 70, 70);
		gc.strokeRect(162, 210, 70, 70);
	}

	private class ButtonListener implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			scrx = (int) event.getX();
			scry = (int) event.getY();

			//check to see if clicked on none of the boxes  
			whatRow = new Integer(9);
			whatCol = new Integer(9);

			//locate the corresponding rows and column
			if(scrx > 22 && scrx < 92) {
				if(scry > 70 && scry < 140) {
					//row 0 col 0
					whatRow = 0;
					whatCol = 0;
				}
				else if(scry > 140 && scry < 210) {
					//row 1 col 0
					whatRow = 1;
					whatCol = 0;
				}
				else if(scry > 210 && scry < 280) {
					//row 2 col 0
					whatRow = 2;
					whatCol = 0;
				}
			}
			else if(scrx > 92 && scrx < 162) {
				if(scry > 70 && scry < 140) {
					//row 0 col 1
					whatRow = 0;
					whatCol = 1;
				}
				else if(scry > 140 && scry < 210) {
					//row 1 col 1
					whatRow = 1;
					whatCol = 1;
				}
				else if(scry > 210 && scry < 280) {
					//row 2 col 0
					whatRow = 2;
					whatCol = 1;
				}
			}
			else if(scrx > 162 && scrx < 232) {
				if(scry > 70 && scry < 140) {
					//row 0 col 2
					whatRow = 0;
					whatCol = 2;
				}
				else if(scry > 140 && scry < 210) {
					//row 1 col 2
					whatRow = 1;
					whatCol = 2;
				}
				else if(scry > 210 && scry < 280) {
					//row 2 col 2
					whatRow = 2;
					whatCol = 2;
				}
			}
			else {
				return;
			}

			if((whatRow == 9) || (whatCol == 9)) {
				return;
			}

			//if boxes weren't clicked skip the code
			if(whatRow <= 2 && whatCol <= 2 && whatRow >= 0 && whatCol >= 0) {
				if (theGame.stillRunning() && theGame.getTicTacToeBoard()[whatRow][whatCol] == '_') {
					if(theGame.available(whatRow, whatCol)) {
						theGame.humanMove(whatRow, whatCol, false);
						updateButtons();
					}
				}
			}
			else {
				return;
			}

		}
	}

	@Override
	public void update(Observable observable, Object message) {
		// TODO Auto-generated method stub

		theGame = (TicTacToeGame) observable;
		updateButtons();

		//updating the text to represent who won
		if (theGame.didWin('X'))
			top = ("X wins");
		else if (theGame.didWin('O'))
			top = ("O wins");
		else if (theGame.tied())
			top = ("Tie");
		else
			top = "Make Move";

		updateButtons();
	}

	private void updateButtons() {
		// TODO Auto-generated method stub
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		drawBoard();
		char[][] temp = theGame.getTicTacToeBoard();
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if(temp[r][c] == 'X') {
					//drawing the x images
					Image hunter = new Image("file:images/x.png", false);
					gc.drawImage(hunter, 5+((c*70)+35), 55+((r*70)+35));
				}
				else if(temp[r][c] == 'O') {
					//drawing the y images
					Image wumpus = new Image("file:images/o.png", false);
					gc.drawImage(wumpus, 5+((c*70)+35), 55+((r*70)+35));
				}
			}
		}
	}

}

package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.TicTacToeGame;

public class TextAreaView extends BorderPane implements Observer {

	private TicTacToeGame theGame;

	private Button makeMove;
	private TextField row;
	private Label lab1;
	private TextField col;
	private Label lab2;

	private GridPane fields;
	private TextArea textBox;
	private String textString;

	public TextAreaView(TicTacToeGame TicTacToeGame) {
		theGame = TicTacToeGame;
		fields = new GridPane();
		fields.setHgap(10);
		fields.setVgap(10);
		fields.setPadding(new Insets(10, 0, 0, 0));

		row = new TextField();
		row.setMaxWidth(70.0);
		GridPane.setMargin(row, new Insets(0, 0, 0, 65));	

		lab1 = new Label("Row");

		col = new TextField();
		col.setMaxWidth(70.0);
		GridPane.setMargin(col, new Insets(0, 0, 0, 65));

		lab2 = new Label("Column");

		makeMove = new Button();
		makeMove.setText("Make Move");
		makeMove.setMaxWidth(100.0);
		GridPane.setMargin(makeMove, new Insets(0, 0, 0, 65));

		this.setCenter(fields);
		textBox = new TextArea();
		textBox.setEditable(false);
		
		//changed the font to recommended Courier font
		textBox.setFont(new Font("Courier", 24));
		textBox.setMaxWidth(254);
		textBox.setPadding(new Insets(0, 0, 0, 0));
		this.setBottom(textBox);


		initializePanel();
	}

	private void initializePanel() {
		fields.add(row, 0, 0);
		fields.add(lab1, 1, 0);
		fields.add(col, 0, 1);
		fields.add(lab2, 1, 1);

		//event handler and printing the original present empty string
		ButtonListener handler = new ButtonListener();
		makeMove.setOnAction(handler);
		fields.add(makeMove, 0, 2);
		fields.add(textBox, 0, 3, 3, 1);
		textBox.setText("  _    _    _\n\n"+"  _    _    _\n\n"+"  _    _    _\n\n");

	}

	private class ButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			int whatRow = Integer.parseInt(row.getText());
			int whatCol = Integer.parseInt(col.getText());

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

	public void update(Observable observable, Object arg) {
		// TODO Auto-generated method stub
		theGame = (TicTacToeGame) observable;
		updateButtons();
		if (theGame.didWin('X'))
			makeMove.setText("X wins");
		else if (theGame.didWin('O'))
			makeMove.setText("O wins");
		else if (theGame.tied())
			makeMove.setText("Tie");
		else
			makeMove.setText("Make Move");

		System.out.println("\nIn TextAreaView.update() \n" + observable);
	}

	//Mark each selected square with an X or an O and prevent
	// selection of the selected squares with seDisabled(true)
	public void updateButtons() {
		textString = new String();
		char[][] temp = theGame.getTicTacToeBoard();
		textString = "";
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				textString += "  " + temp[r][c] + "  ";
			}
			if (r == 0 || r == 1)
				textString += "\n\n";
		}
		textBox.setText(textString);
	}
}
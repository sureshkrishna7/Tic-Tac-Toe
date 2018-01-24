package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.TicTacToeGame;

public class ButtonView extends BorderPane implements Observer {

  private TicTacToeGame theGame;
  private Label stateButton;
  private Button[][] buttons = null;
  private GridPane buttonPanel;
  final public static String clickMessage = "Click to make a move";
 
  public ButtonView(TicTacToeGame TicTacToeGame) {
    theGame = TicTacToeGame; // Need this to send the first move to the game
    buttonPanel = new GridPane();
    buttonPanel.setHgap(5);
    buttonPanel.setVgap(5);

    BorderPane.setAlignment(buttonPanel, Pos.TOP_LEFT);
    BorderPane.setMargin(buttonPanel, new Insets(30, 30, 0, 30));
    this.setCenter(buttonPanel);

    stateButton = new Label(clickMessage);
    stateButton.setFont(new Font("Arial", 17));
    stateButton.setTextFill(Color.web("#0076a3"));
    BorderPane.setAlignment(stateButton, Pos.TOP_CENTER);
    BorderPane.setMargin(stateButton, new Insets(0, 0, 50, 0));
    this.setBottom(stateButton);
    initializeButtonPanel();
  }

  // This method is called by OurObservable's notifyObservers()
  public void update(Observable observable, Object message) {
    theGame = (TicTacToeGame) observable;
    updateButtons();
    if (theGame.didWin('X'))
      stateButton.setText("X wins");
    else if (theGame.didWin('O'))
      stateButton.setText("O wins");
    else if (theGame.tied())
      stateButton.setText("Tie");
    else
      stateButton.setText(clickMessage);
  }

  private void initializeButtonPanel() {
    Font myFont = new Font("Courier New", 32);
    buttons = new Button[3][3];
    ButtonListener handler = new ButtonListener();
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        buttons[row][col] = new Button();
        buttons[row][col].setFont(myFont);
        buttons[row][col].setOnAction(handler);
        buttons[row][col].setTextFill(Color.web("#0076a3"));
        buttons[row][col].setText("_");
        buttonPanel.add(buttons[row][col], col, row);
      }
    }
  }

  // Mark each selected square with an X or an O and prevent
  // selection of the selected squares with seDisabled(true)
  public void updateButtons() {
    char[][] temp = theGame.getTicTacToeBoard();
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        String text = "" + temp[r][c];
        buttons[r][c].setText(text);
      }
    }
  }

  private class ButtonListener implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent arg0) {
      Button buttonClicked = (Button) arg0.getSource();
      for (int i = 0; i < buttons.length; i++) {
        for (int j = 0; j < buttons[i].length; j++) {
          if (buttons[i][j] == buttonClicked) {
            if (theGame.stillRunning() && theGame.getTicTacToeBoard()[i][j] == '_') {
              // Human user makes a move and the ComputerPlayer makes a move
              theGame.humanMove(i, j, false);
              updateButtons();
            } else {
              return; // The clicked button was already chosen
            }
          }
        }
      }
    } // end handle
    
  }
}
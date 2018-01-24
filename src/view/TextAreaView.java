package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import model.TicTacToeGame;

public class TextAreaView extends BorderPane implements Observer {
   
  private TicTacToeGame theGame;

  public TextAreaView(TicTacToeGame TicTacToeGame) {
    theGame = TicTacToeGame;
    initializePane();
  }

  private void initializePane() {
    Label label = new Label("TextAreaView");
    label.setFont(new Font("serif", 24));
    setCenter(label);
  }

  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub
    System.out.println("\nIn TextAreaView.update() \n" + o);
  }
}
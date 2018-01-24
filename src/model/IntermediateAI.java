package model;

import java.awt.Point;

/**
 * This TTT strategy tries to prevent the opponent from winning by checking
 * for a space where the opponent is about to win. If none found, it randomly
 * picks a place to win, which an sometimes be a win even if not really trying.
 * 
 * @author mercer
 */
public class IntermediateAI implements TicTacToeStrategy {

  @Override
  // Precondition: During testing the AI is associated with the 'O', the odd number move.
  public Point desiredMove(TicTacToeGame theGame) {
    // TODO: Return a Point that would win, if not possible to block, 
    // if not possible to block return an available Point of your own choosing 
    return new Point(0, 0);
  }
}
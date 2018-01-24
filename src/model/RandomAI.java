package model;

import java.awt.Point;

/**
 * This strategy selects the first available move at random.  It is easy to beat
 * 
 * @throws IGotNowhereToGoException whenever asked for a move that is impossible to deliver
 * 
 * @author mercer
 */
public class RandomAI implements TicTacToeStrategy {

  // Find an open spot while ignoring possible wins and stops (block a guaranteed win)
  @Override
  public Point desiredMove(TicTacToeGame theGame) {
    // TODO: Return a random available Point
    return new Point(0, 1);
  }
}
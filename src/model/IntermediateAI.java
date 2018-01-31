package model;

import java.awt.Point;
import java.util.Random;

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

		//Initial grand moves	
		if(theGame.maxMovesRemaining() == 8) {
			if(theGame.available(1, 1)) {
				return new Point(1, 1);
			}
			else if(theGame.available(0, 0)) {
				return new Point(0, 0);
			}
			else if(theGame.available(2, 2)) {
				return new Point(2, 2);
			}
			else if(theGame.available(0, 2)) {
				return new Point(0, 2);
			}
			else if(theGame.available(2, 0)) {
				return new Point(2, 0);
			}
		}
		else {
			char[][] look = theGame.getTicTacToeBoard();

			int size = 3;
			//check for two O in diagonal to win
			// Check Diagonal from upper left to lower right
			int sum = 0;
			for (int r = 0; r < size; r++) {
				if (look[r][r] == 'O')
					sum++;
			}
			if(sum == 2) {
				if(theGame.available(0, 0))
					return new Point(0, 0);
				else if(theGame.available(1, 1))
					return new Point(1, 1);
				else if(theGame.available(2, 2))
					return new Point(2, 2);
			}
			// Check Diagonal from upper right to lower left
			sum = 0;
			for (int r = size - 1; r >= 0; r--) {
				if (look[size - r - 1][r] == 'O')
					sum++;
			}
			if(sum == 2) {
				if(theGame.available(0, 2))
					return new Point(0, 2);
				else if(theGame.available(1, 1))
					return new Point(1, 1);
				else if(theGame.available(2, 0))
					return new Point(2, 0);
			}

			//check for two O in rows to win
			for (int r = 0; r < size; r++) {
				int rowSum = 0;
				for (int c = 0; c < size; c++) {
					if (look[r][c] == 'O') {
						rowSum++;
					}
				}
				if(rowSum == 2) {
					if(theGame.available(r, 0)) 
						return new Point(r, 0);
					else if(theGame.available(r, 1))
						return new Point(r, 1);
					else if(theGame.available(r, 2))
						return new Point(r, 2);
				}
				rowSum = 0;
			}

			//check for two O in columns to win
			for (int c = 0; c < size; c++) {
				int colSum = 0;
				for (int r = 0; r < size; r++) {
					if (look[r][c] == 'O') {
						colSum++;
					}
				}
				if(colSum == 2) {
					if(theGame.available(0, c)) 
						return new Point(0, c);
					else if(theGame.available(1, c))
						return new Point(1, c);
					else if(theGame.available(2, c))
						return new Point(2, c);
				}
				colSum = 0;
			}

			//check for two X in diagonal to win
			// Check Diagonal from upper left to lower right
			sum = 0;
			for (int r = 0; r < size; r++) {
				if (look[r][r] == 'X')
					sum++;
			}
			if(sum == 2) {
				if(theGame.available(0, 0))
					return new Point(0, 0);
				else if(theGame.available(1, 1))
					return new Point(1, 1);
				else if(theGame.available(2, 2))
					return new Point(2, 2);
			}
			// Check Diagonal from upper right to lower left
			sum = 0;
			for (int r = size - 1; r >= 0; r--) {
				if (look[size - r - 1][r] == 'X')
					sum++;
			}
			if(sum == 2) {
				if(theGame.available(0, 2))
					return new Point(0, 2);
				else if(theGame.available(1, 1))
					return new Point(1, 1);
				else if(theGame.available(2, 0))
					return new Point(2, 0);
			}

			//check for two X in rows to prevent X win
			for (int r = 0; r < size; r++) {
				int rowSum = 0;
				for (int c = 0; c < size; c++) {
					if (look[r][c] == 'X') {
						rowSum++;
					}
				}
				if(rowSum == 2) {
					if(theGame.available(r, 0)) 
						return new Point(r, 0);
					else if(theGame.available(r, 1))
						return new Point(r, 1);
					else if(theGame.available(r, 2))
						return new Point(r, 2);
				}
				rowSum = 0;
			}

			//check for two X in columns to prevent X win
			for (int c = 0; c < size; c++) {
				int colSum = 0;
				for (int r = 0; r < size; r++) {
					if (look[r][c] == 'X') {
						colSum++;
					}
				}
				if(colSum == 2) {
					if(theGame.available(0, c)) 
						return new Point(0, c);
					else if(theGame.available(1, c))
						return new Point(1, c);
					else if(theGame.available(2, c))
						return new Point(2, c);
				}
				colSum = 0;
			}

		//to avoid trapping of set peices
			if(theGame.maxMovesRemaining() == 6) {
				if((look[1][1] == 'X' && look[2][2] == 'X') || (look[1][1] == 'X' && look[0][0] == 'X')) {
					if(theGame.available(0, 2)) {
						return new Point(0,2);
					}
					else if(theGame.available(2, 0)) {
						return new Point(2,0);
					}
				}			
				else if((look[1][1] == 'X' && look[0][2] == 'X') || (look[1][1] == 'X' && look[2][0] == 'X')) {
					if(theGame.available(0, 0)) {
						return new Point(0,0);
					}
					else if(theGame.available(2, 2)) {
						return new Point(2,2);
					}
				}			
				else if((look[0][0] == 'X') && (look[2][2] == 'X')){
					if(theGame.available(0, 1)) {
						return new Point(0, 1);
					}
					else if(theGame.available(2, 1)) {
						return new Point(2,1);
					}
					else if(theGame.available(1, 0)) {
						return new Point (1,0);
					}
					else if(theGame.available(1, 2)) {
						return new Point (1,2);
					}
				}
				else if(((look[2][0] == 'X')) && ((look[0][2] == 'X'))){
					if(theGame.available(0, 1)) {
						return new Point(0, 1);
					}
					else if(theGame.available(2, 1)) {
						return new Point(2,1);
					}
					else if(theGame.available(1, 0)) {
						return new Point (1,0);
					}
					else if(theGame.available(1, 2)) {
						return new Point (1,2);
					}
				}
			}

			//if nobody is winning then lets focus on the set peices for winning
			if(theGame.available(1, 1)) {
				return new Point(1, 1);
			}
			else if(theGame.available(0, 0)) {
				return new Point(0, 0);
			}
			else if(theGame.available(2, 2)) {
				return new Point(2, 2);
			}
			else if(theGame.available(0, 2)) {
				return new Point(0, 2);
			}
			else if(theGame.available(2, 0)) {
				return new Point(2, 0);
			}
		}


		//if we analyze no pattern then just simply return a random point
		Random rand = new Random();
		int x = rand.nextInt(3);
		int y = rand.nextInt(3);

		while(!(theGame.available(x, y))) {
			x = rand.nextInt(3);
			y = rand.nextInt(3);
		}
		return new Point(x, y);
	}
}
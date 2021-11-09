package com.lebmorda.gdx.piezas;

import com.lebmorda.gdx.utils.Constants;

public class TetrisI extends Pieza {
	
	public TetrisI() {
		rotate();
	}

	@Override
	public void init() {
		ROW_TETRIS = 4;
		COLUMN_TETRIS = 4;
		COLOR = Constants.COLOR_RED;

	}

	public void rotate() {

		
		resetBlock();

		ROTATE++;

		if (ROTATE > 3) {
			ROTATE = 0;
		}

		if (ROTATE == 0) {			
			piezaBlockArray[0][3].setPiece(true);
			piezaBlockArray[1][3].setPiece(true);
			piezaBlockArray[2][3].setPiece(true);
			piezaBlockArray[3][3].setPiece(true);

			piezaBlockRotateArray[2][0].setRotate(true);
			piezaBlockRotateArray[2][1].setRotate(true);
			piezaBlockRotateArray[2][2].setRotate(true);
			piezaBlockRotateArray[2][3].setRotate(true);

			WIDTH = 4;
		}

		if (ROTATE == 1) {
			piezaBlockArray[2][0].setPiece(true);
			piezaBlockArray[2][1].setPiece(true);
			piezaBlockArray[2][2].setPiece(true);
			piezaBlockArray[2][3].setPiece(true);

			piezaBlockRotateArray[0][3].setRotate(true);
			piezaBlockRotateArray[1][3].setRotate(true);
			piezaBlockRotateArray[2][3].setRotate(true);
			piezaBlockRotateArray[3][3].setRotate(true);
			WIDTH = 1;

		}

		if (ROTATE == 2) {		
			piezaBlockArray[0][3].setPiece(true);
			piezaBlockArray[1][3].setPiece(true);
			piezaBlockArray[2][3].setPiece(true);
			piezaBlockArray[3][3].setPiece(true);

			piezaBlockRotateArray[1][0].setRotate(true);
			piezaBlockRotateArray[1][1].setRotate(true);
			piezaBlockRotateArray[1][2].setRotate(true);
			piezaBlockRotateArray[1][3].setRotate(true);
			
			WIDTH = 4;
		}

		if (ROTATE == 3) {
			piezaBlockArray[1][0].setPiece(true);
			piezaBlockArray[1][1].setPiece(true);
			piezaBlockArray[1][2].setPiece(true);
			piezaBlockArray[1][3].setPiece(true);

			piezaBlockRotateArray[0][3].setRotate(true);
			piezaBlockRotateArray[1][3].setRotate(true);
			piezaBlockRotateArray[2][3].setRotate(true);
			piezaBlockRotateArray[3][3].setRotate(true);

			WIDTH = 1;
		}
	}
}

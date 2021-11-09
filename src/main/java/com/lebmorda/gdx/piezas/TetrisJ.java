package com.lebmorda.gdx.piezas;

import com.lebmorda.gdx.utils.Constants;

public class TetrisJ extends Pieza {
	public TetrisJ() {
		
		rotate();
	}

	@Override
	public void init() {
		ROW_TETRIS = 4;
		COLUMN_TETRIS = 4;
		COLOR = Constants.COLOR_GREEN;
	}
	public void rotate() {
		
		resetBlock();
		
		ROTATE++;
		
		if (ROTATE > 3) {
			ROTATE = 0;
		}

		if (ROTATE == 0) {
			piezaBlockArray[0][1].setPiece(true);
			piezaBlockArray[1][1].setPiece(true);
			piezaBlockArray[2][1].setPiece(true);
			piezaBlockArray[2][0].setPiece(true);

			piezaBlockRotateArray[1][0].setRotate(true);
			piezaBlockRotateArray[0][0].setRotate(true);
			piezaBlockRotateArray[1][1].setRotate(true);
			piezaBlockRotateArray[1][2].setRotate(true);

		}

		if (ROTATE == 1) {
			piezaBlockArray[1][0].setPiece(true);
			piezaBlockArray[0][0].setPiece(true);
			piezaBlockArray[1][1].setPiece(true);
			piezaBlockArray[1][2].setPiece(true);

			piezaBlockRotateArray[0][1].setRotate(true);
			piezaBlockRotateArray[0][0].setRotate(true);
			piezaBlockRotateArray[1][0].setRotate(true);
			piezaBlockRotateArray[2][0].setRotate(true);
		}

		if (ROTATE == 2) {
			piezaBlockArray[0][1].setPiece(true);
			piezaBlockArray[0][0].setPiece(true);
			piezaBlockArray[1][0].setPiece(true);
			piezaBlockArray[2][0].setPiece(true);

			piezaBlockRotateArray[1][0].setRotate(true);
			piezaBlockRotateArray[1][1].setRotate(true);
			piezaBlockRotateArray[2][2].setRotate(true);
			piezaBlockRotateArray[1][2].setRotate(true);
		}

		if (ROTATE == 3) {
			piezaBlockArray[1][0].setPiece(true);
			piezaBlockArray[1][1].setPiece(true);
			piezaBlockArray[2][2].setPiece(true);
			piezaBlockArray[1][2].setPiece(true);

			piezaBlockRotateArray[0][1].setRotate(true);
			piezaBlockRotateArray[1][1].setRotate(true);
			piezaBlockRotateArray[2][1].setRotate(true);
			piezaBlockRotateArray[2][0].setRotate(true);
		}

	}


}

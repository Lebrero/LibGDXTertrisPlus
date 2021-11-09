package com.lebmorda.gdx.piezas;

import com.lebmorda.gdx.utils.Constants;

public class TetrisT extends Pieza {
	public TetrisT() {

		piezaBlockArray[1][0].setPiece(true);
		piezaBlockArray[1][1].setPiece(true);
		piezaBlockArray[2][1].setPiece(true);
		piezaBlockArray[1][2].setPiece(true);
		
	}

	@Override
	public void init() {
		ROW_TETRIS = 3;
		COLUMN_TETRIS = 3;
		COLOR = Constants.COLOR_BLUE;
	}

	public void rotate() {
		Block[][] listaBlock = rotateClockWise(piezaBlockArray);
		for (int i = 0; i < ROW_TETRIS; i++) {
			for (int j = 0; j < COLUMN_TETRIS; j++) {
				piezaBlockArray[j][i].setPiece(listaBlock[j][i].isPiece());

			}
		}
	}

	private Block[][] rotateClockWise(Block[][] matrix) {

		int size = matrix.length;
		for (int i = 0; i < size; ++i)
			for (int j = 0; j < size; ++j)
				piezaArrayTemp[i][j].setPiece(matrix[size - j - 1][i].isPiece());
		return piezaArrayTemp;
	}

	

}

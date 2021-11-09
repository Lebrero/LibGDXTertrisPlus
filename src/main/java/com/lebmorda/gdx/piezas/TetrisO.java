package com.lebmorda.gdx.piezas;

import com.lebmorda.gdx.utils.Constants;

public class TetrisO extends Pieza {

	public TetrisO() {
		
		rotate();
	}

	@Override
	public void init() {
		ROW_TETRIS = 2;
		COLUMN_TETRIS = 2;
		COLOR = Constants.COLOR_BLUE;
	}
	@Override

	public void rotate() {
		
		piezaBlockArray[0][0].setPiece(true);
		piezaBlockArray[0][1].setPiece(true);
		piezaBlockArray[1][0].setPiece(true);
		piezaBlockArray[1][1].setPiece(true);
	}


}

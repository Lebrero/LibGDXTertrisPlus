package com.lebmorda.gdx.piezas;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Pieza extends Actor {
	protected int ROTATE = -1;
	
	public Block[][] piezaBlockArray;
	public Block[][] piezaBlockRotateArray;
	public Block[][] piezaBlockGhostArray;

	public Block[][] piezaArrayTemp;

	public int COLOR;
	public int ROW_TETRIS = 4;
	public int COLUMN_TETRIS = 4;
	
	public int WIDTH = 0;

	private int offset;
	
	public boolean canRotate=true;

	public Pieza() {
		createTetris();
	}

	public abstract void init();
	
	public abstract void rotate();

	public void createTetris() {

		// Inicializar el tamaño del array de la Pieza
		init();

		piezaBlockArray = new Block[COLUMN_TETRIS][ROW_TETRIS];
		piezaBlockRotateArray = new Block[COLUMN_TETRIS][ROW_TETRIS];
		piezaBlockGhostArray = new Block[COLUMN_TETRIS][ROW_TETRIS];

		piezaArrayTemp = new Block[COLUMN_TETRIS][ROW_TETRIS];


		for (int i = 0; i < ROW_TETRIS; i++) {
			for (int j = 0; j < COLUMN_TETRIS; j++) {
				piezaBlockArray[j][i] = new Block (j, i, COLOR);
				piezaBlockRotateArray[j][i] = new Block (j, i, COLOR);
				piezaBlockGhostArray[j][i] = new Block (j, i, COLOR);
				piezaArrayTemp[j][i] = new Block (j, i, COLOR);


			}
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (int i = 0; i < ROW_TETRIS; i++) {
			for (int j = 0; j < COLUMN_TETRIS; j++) {
				if (piezaBlockArray[j][i].isPiece()) {
					piezaBlockArray[j][i].drawBlocks(parentAlpha, batch, Math.round(getX()), Math.round(getY()));
				}
				if (piezaBlockRotateArray[j][i].isRotate()) {
					piezaBlockRotateArray[j][i].drawBlocksShape(parentAlpha, batch, Math.round(getX()), Math.round(getY()),0);
				}

				if (piezaBlockArray[j][i].isPiece()) {
					piezaBlockArray[j][i].drawBlocksShape(parentAlpha, batch, Math.round(getX()), Math.round(getY()),356);
				}

			}
		}
	}

	/**
	 * Método encargado de resetear la pieza
	 */
	public void resetBlock() {
		for (int i = 0; i < ROW_TETRIS; i++) {
			for (int j = 0; j < ROW_TETRIS; j++) {
				piezaBlockArray[j][i].setPiece(false);
				piezaBlockRotateArray[j][i].setRotate(false);
			}
		}
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}

package com.lebmorda.gdx.objetos;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lebmorda.gdx.MainGame;
import com.lebmorda.gdx.piezas.Block;
import com.lebmorda.gdx.piezas.Pieza;
import com.lebmorda.gdx.piezas.TetrisI;
import com.lebmorda.gdx.piezas.TetrisJ;
import com.lebmorda.gdx.piezas.TetrisL;
import com.lebmorda.gdx.piezas.TetrisO;
import com.lebmorda.gdx.piezas.TetrisS;
import com.lebmorda.gdx.piezas.TetrisT;
import com.lebmorda.gdx.piezas.TetrisZ;
import com.lebmorda.gdx.utils.Constants;

public class Tablero extends Actor {

	private int prevNumber = 0;

	public Pieza pieza;

	private MainGame game;

	public Block[][] tableroArray;
	
	public ArrayList<Vector2> blockTablero;
	
	public int LIMIT_RIGHT = 352;
	public int LIMIT_LEFT = 0;
	public int SIZE = 0;

	public Tablero(MainGame game) {
		this.game=game;
		init();
	}

	/**
	 * Método encargado de inicializar el tablero
	 */
	private void init() {
		this.tableroArray = new Block[Constants.TABLERO_COLUMN][Constants.TABLERO_ROW];
		this.blockTablero= new ArrayList<Vector2>();
		
		for (int i = Constants.TABLERO_ROW - 1; i >= 0; i--) {
			for (int j = 0; j < Constants.TABLERO_COLUMN; j++) {				
				this.tableroArray[j][i] = new Block(j, i, Constants.COLOR_BLUE);			
			}
		}
		genRandomPiece();

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (int i = 0; i < Constants.TABLERO_ROW; i++) {
			for (int j = 0; j < Constants.TABLERO_COLUMN; j++) {
				if (this.tableroArray[j][i].isTable()) {
					this.tableroArray[j][i].drawBlocks(parentAlpha, batch, Math.round(getX()),Math.round(getY()));
				}
			}
		}
	}
	
	
	/**
	 * Metodo encargado de detectar si la "Pieza" ha colisionado con otras piezas ya
	 * posicionadas en el tablero
	 */

	public boolean isCollide() {				
		for (int k = 0; k < pieza.ROW_TETRIS; k++) {
			for (int l = 0; l < pieza.COLUMN_TETRIS; l++) {
				if (this.pieza.piezaBlockArray[l][k].isPiece()) {
					if (this.blockTablero.contains(new Vector2(
							this.pieza.piezaBlockArray[l][k].getTableroPositionX(),
							this.pieza.piezaBlockArray[l][k].getTableroPositionY() -1))) {
								newPiece();
								return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Método encargado de comprobar si la pieza se puede mover libremente a la
	 * derecha o izquierda y no se encuentra con ningún otro elemento ya posicionado en el
	 * tablero
	 * 
	 * @return TRUE si existen obstáculos (no deja mover la pieza); FALSE la pieza
	 *         se puede mover libremente
	 */
	public boolean canMove(int move) {
		for (int k = 0; k < pieza.ROW_TETRIS; k++) {
			for (int l = 0; l < pieza.ROW_TETRIS; l++) {
					if (this.pieza.piezaBlockArray[l][k].isPiece()) {
						if (this.blockTablero.contains(new Vector2(
								this.pieza.piezaBlockArray[l][k].getTableroPositionX() + move,
								this.pieza.piezaBlockArray[l][k].getTableroPositionY()))) {
							return false;

						}

					}
				

			}
		}

		return true;

	}

	/**
	 * Método que se encarga de calcular la posición más baja que puede adoptar una  pieza en el tablero
	 */
	public void calcularOffset() {


	}

	/**
	 * Método experimental para conocer el estacio que está quedando disponible entre los lados de la pieza
	 */
	public void checkSpace() {
		int minPos = 0;
		int maxPos = 10;
		
		for (int i = 0; i < pieza.ROW_TETRIS; i++) {
			for (int j = 0; j < pieza.COLUMN_TETRIS; j++) {
				if (pieza.piezaBlockArray[j][i].isPiece()) {
					
					//j: es igual a X. Horizontal
					//i: es igual a Y. Vertical
					
					int yPos= pieza.piezaBlockArray[j][i].getTableroPositionY();
					int xPos= pieza.piezaBlockArray[j][i].getTableroPositionX();

					//Derecha
					for (int m = xPos; m < Constants.TABLERO_COLUMN; m++) {
						if (this.tableroArray[m][yPos].isTable()) {
							if (this.tableroArray[m][yPos].getTableroPositionX() < maxPos)
								maxPos = this.tableroArray[m][yPos].getTableroPositionX();
						}
					}				
					//Izquierda
					for (int n = xPos - 1; n >= 0; n--) {
						if (this.tableroArray[n][yPos].isTable()) {
							if(this.tableroArray[n][yPos].getTableroPositionX()>minPos)	
								minPos=this.tableroArray[n][yPos].getTableroPositionX();
							}

						}
					}					

				}
			}
		
			SIZE = (maxPos - (minPos+1));
			
		//	System.out.println("Hueco: "+SIZE);
	}
	
	
	/**
	 * Metodo encargado realizar operaciones en el caso de que la pieza llegue al
	 * suelo
	 * 
	 * @return TRUE si la pieza ha tocado al suelo; FALSE si la pieza no ha tocado
	 *         el suelo
	 * 
	 */
	public boolean checkGround() {
		for (int i = 0; i < pieza.ROW_TETRIS; i++) {
			for (int j = 0; j < pieza.COLUMN_TETRIS; j++) {
				if (pieza.piezaBlockArray[j][i].isPiece()) {
					if (this.pieza.piezaBlockArray[j][i].getPosGlobalBlockY() <= Constants.GROUND) {
						newPiece();
						return true;
					}
				}
			}

		}
		return false;

	}

		
/**
 * Método encargado de añadir todos los bloques de la "Pieza" al tablero	
 */
	public void setAllBlocksPieceToTablero() {
		for (int i = 0; i < pieza.ROW_TETRIS; i++) {
			for (int j = 0; j < pieza.ROW_TETRIS; j++) {
				if (this.pieza.piezaBlockArray[j][i].isPiece()) {
					
					int posX = this.pieza.piezaBlockArray[j][i].getTableroPositionX();
					int posY = this.pieza.piezaBlockArray[j][i].getTableroPositionY();

					this.tableroArray[posX][posY].setTable(true);
					this.tableroArray[posX][posY].setColor(this.pieza.piezaBlockArray[j][i].getColor());
					
					this.blockTablero.add(new Vector2(posX,posY));
					
					
				}

			}
		}
		this.pieza.remove();
	}
	
	/**
	 * Metodo encargado de gestionar las rotaciones
	 */
	public void rotatePiece() {
		if (this.pieza.canRotate)
			this.pieza.rotate();
			this.checkPositionRightLeft();
		
	}
/**
 * Comprueba la posición de la pieza dentro del tablero y limita su movimento tanto a la derecha como a la izquierda
 *
 */
	public void checkPositionRightLeft() {
		float globalX = 0;
		float desp = 0;

		for (int i = 0; i < pieza.ROW_TETRIS; i++) {
			for (int j = 0; j < pieza.COLUMN_TETRIS; j++) {
				if (this.pieza.piezaBlockArray[j][i].isPiece()) {

					//Posición global de la pieza sobre el tablero
					globalX = this.pieza.piezaBlockArray[j][i].getLocalPositionX() + this.pieza.getX();
					
					if (globalX < LIMIT_LEFT) {
						if (globalX < desp) {desp = globalX;}
						this.pieza.setX(this.pieza.getX() - desp);
					}
					
					if (globalX > LIMIT_RIGHT) {
						if (globalX > desp) {desp = globalX;}
						this.pieza.setX(this.pieza.getX() - (desp - LIMIT_RIGHT));
					}
				}
			}
		}

	}  
	
	public void moveDown() {
		this.pieza.setY(this.pieza.getY()- Constants.SPACE);
		System.out.println("Posición GlobalX: "+this.pieza.getX());
		System.out.println("Posición GlobalY: "+this.pieza.getY());

	}

	/**
	 * Método encargado de poner en juego una nueva pieza en el tablero
	 */
	private void newPiece() {
		setAllBlocksPieceToTablero();
		genRandomPiece();
		game.stage.addActor(pieza);
	}
	/**
	 * Método encargado de generar una nueva "Pieza" aleatoria
	 * 
	 * @return un Objeto "Pieza"
	 */
	public Pieza genRandomPiece(){
		
		int randomNumber;
		do {
			randomNumber = (int) (Math.random() * 7) + 1;
		} while (prevNumber == randomNumber);

        prevNumber = randomNumber;


        switch(randomNumber) {
            case 1:this.pieza = new TetrisI();break;
            case 2:this.pieza = new TetrisJ();break;
            case 3:this.pieza = new TetrisL();break;
            case 4:this.pieza = new TetrisO();break;
            case 5:this.pieza = new TetrisS();break;
            case 6:this.pieza = new TetrisT();break;
            case 7:this.pieza = new TetrisZ();break;
            
            default: {} break;
        }

        this.pieza.setPosition(Constants.SALIDA_X, Constants.SALIDA_Y);
        
        return this.pieza;

	}

	
	public Block[][] getTableroArray() {
		return tableroArray;
	}








	
}

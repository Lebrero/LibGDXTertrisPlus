package com.lebmorda.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.lebmorda.gdx.MainGame;
import com.lebmorda.gdx.objetos.Tablero;
import com.lebmorda.gdx.utils.Constants;

public class GameScreen extends ScreenAdapter {

	private Tablero tablero;
	private MainGame game;

	private float velocidadCaida = 0.8f;
	private float tiempoInicialCaida = 0;

	private float velocidadMover = 0.1f;
	private float tiempoInicialMover = 0;


	// Debug
	public GameScreen(MainGame mainGame) {
		this.game = mainGame;
	}

	@Override
	public void show() {
		game.stage = new Stage(new FitViewport(396, 960));

		//Creamos un tablero
		tablero = new Tablero(game);

		//Añadimos los actores a la Stage
		game.stage.addActor(tablero);
		game.stage.addActor(tablero.pieza);

		//Seleccionar el input processsor
		Gdx.input.setInputProcessor(game.stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.stage.act(Gdx.graphics.getDeltaTime());
		game.stage.draw();

		chekFall();
		input();


	}

	/**
	 * método encargado de controlar en input
	 */
	private void input() {
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (tablero.canMove(Constants.MOVE_RIGHT)) {
				this.movePiece(Constants.MOVE_RIGHT);
			}
		}else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (tablero.canMove(Constants.MOVE_LEFT)) {
				this.movePiece(Constants.MOVE_LEFT);
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			velocidadCaida = 0.1f;
		} else {
			velocidadCaida = 2f;

		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			tablero.rotatePiece();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
		//	getinfo();

		}

		tablero.checkPositionRightLeft();
	}

	/**
	 * Método encargado de calcular cuando se debe mover la pieza tanto a la izquierda como a la derecha
	 * @param dir
	 */
	private void movePiece(int dir) {
		tiempoInicialMover+= Gdx.graphics.getDeltaTime();
		if (Math.abs(tiempoInicialMover) > velocidadMover) {
			tablero.pieza.setX(tablero.pieza.getX() + Constants.SPACE * dir);
			tiempoInicialMover = 0;
		}

	}

	/**
	 * Método encarfado de hacer las comprobaciones cada vez aue la pieza avanza una posición en el eje Y. Se està llamando desde el método 'Render'
	 */
	private void chekFall() {
		tiempoInicialCaida += Gdx.graphics.getDeltaTime();

		tablero.checkSpace();

		if (Math.abs(tiempoInicialCaida) > velocidadCaida) {
			//Comprobamos si hemos llegado al suelo
			tablero.checkGround();

			//Comprobamos si estamos colisionando con otras piezas
			tablero.isCollide();

			//Movemos la pieza hacia abajo
			tablero.moveDown();

			//Reseamos los contadores
			tiempoInicialCaida = 0;
		}

	}

	/** Método encargado de recoger toda la información de las piezas que se estan moviendo en el tablero
	 *
	 */
	public void getPiezaInfo() {
		System.out.println("Posición Global Pieza:");				

		for (int i = 0; i < tablero.pieza.ROW_TETRIS; i++) {
			for (int j = 0; j < tablero.pieza.ROW_TETRIS; j++) {
				if (tablero.pieza.piezaBlockArray[j][i].isPiece()) {
					
					System.out.println("Posición Block_X: " + tablero.pieza.piezaBlockArray[j][i].getPosGlobalBlockX()
							+ " Posición Block_Y: " + tablero.pieza.piezaBlockArray[j][i].getPosGlobalBlockY());

				}

			}
		}
		System.out.println();
	}

//	public void getinfo() {
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//
//				if (tablero.pieza.piezaArray[j][i].isPiece() || tablero.pieza.piezaArray[j][i].isTable()) {
//
//					System.out.println("PosiciónX: " + tablero.pieza.piezaArray[j][i].getPosGlobalBlockX());
//					System.out.println("PosiciónY: " + tablero.pieza.piezaArray[j][i].getPosGlobalBlockY());
//					System.out.println("\n");
//
//				}
//			}
//		}
//		System.out.println("------------------\n");
//	}

	@Override
	public void resize(int width, int height) {
		game.stage.getViewport().update(width, height);
	}

	@Override
	public void dispose() {
		game.stage.dispose();

	}
}

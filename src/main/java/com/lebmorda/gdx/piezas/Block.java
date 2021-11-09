package com.lebmorda.gdx.piezas;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.lebmorda.gdx.utils.Assets;
import com.lebmorda.gdx.utils.Constants;

public class Block {
	//Posición XY dentro de la propia pieza
	protected int idPositionX;
	protected int idPositionY;

	//Posición XY dentro del tablerp
	protected int tableroPositionX;
	protected int tableroPositionY;

	//Posición XY dentro dentro tablero teniendo en cuenta la pieza
	public int posGlobalBlockX;
	public int posGlobalBlockY;

	//Color del bloque
	private int color;

	//Indica si el bloque pertenece a la tabla
	public boolean table;
	//Indica si el bloque pertenece a la la pieza
	public boolean piece;
	//Indica si la pieza tiene espacio para rotar
	public boolean rotate;


	protected AtlasRegion region;
	protected Sprite elementBlock;

	private ShapeRenderer blockShape = new ShapeRenderer();
	private Rectangle boundsBlock = new Rectangle();

	//bounds


	public Block(int idPositionX,int idPositionY, int color) {

		this.color = color;
		this.idPositionX = idPositionX;
		this.idPositionY = idPositionY;

		init();
	}

	private void init() {
		this.blockShape= new ShapeRenderer();
		this.boundsBlock = new Rectangle();
		getRegion();
	}

	/**
	 * Método encargado de dibujar los bloque en pantalla
	 * @param parentAlpha transparencia
	 * @param batch bach
	 * @param piecePositionX Posición X de la pieza
	 * @param piecePositionY Posición Y de la pieza
	 */
	public void drawBlocks(float parentAlpha, Batch batch, int piecePositionX, int piecePositionY) {

	//	System.out.println("piece positionX: "+piecePositionX);
	//	System.out.println("piece positionY: "+piecePositionY);

		this.posGlobalBlockX = getLocalPositionX() + piecePositionX;
		this.posGlobalBlockY = getLocalPositionY() + piecePositionY;

		this.tableroPositionX = this.idPositionX + (piecePositionX / Constants.SPACE);
		this.tableroPositionY = this.idPositionY + (piecePositionY / Constants.SPACE);

		batch.draw(
                this.elementBlock,
				this.posGlobalBlockX,
				this.posGlobalBlockY,
				this.elementBlock.getWidth(),
				this.elementBlock.getHeight());


	}
	public void drawBlocksShape(float parentAlpha, Batch batch, int piecePositionX, int piecePositionY,int offSet) {

		this.posGlobalBlockX = getLocalPositionX() + piecePositionX;
		this.posGlobalBlockY = getLocalPositionY() + piecePositionY;

		this.tableroPositionX = this.idPositionX + (piecePositionX / Constants.SPACE);
		this.tableroPositionY = this.idPositionY + (piecePositionY / Constants.SPACE);
		batch.end();

		//Figura
		blockShape.begin(ShapeRenderer.ShapeType.Line);

		blockShape.rect(
				this.posGlobalBlockX,
				this.posGlobalBlockY-offSet,
				this.elementBlock.getWidth(),
				this.elementBlock.getHeight());

		blockShape.end();

		batch.begin();

		blockShape.setProjectionMatrix(batch.getProjectionMatrix());
		blockShape.setTransformMatrix((batch.getTransformMatrix()));
	}

		public boolean isTable() {
		return table;
	}

	public void setTable(boolean table) {
		this.table = table;
	}

	public boolean isPiece() {
		return piece;
	}

	public void setPiece(boolean piece) {
		this.piece = piece;
	}

	public int getPosGlobalBlockX() {
		return posGlobalBlockX;
	}

	public int getPosGlobalBlockY() {
		return posGlobalBlockY;
	}

	public int getLocalPositionX() {
		return idPositionX * Constants.SPACE;
	}

	public int getLocalPositionY() {
		return idPositionY * Constants.SPACE;
	}

	public int getIdPositionX() {
		return idPositionX;
	}

	public void setIdPositionX(int idPositionX) {
		this.idPositionX = idPositionX;
	}

	public int getIdPositionY() {
		return idPositionY;
	}

	public void setIdPositionY(int idPositionY) {
		this.idPositionY = idPositionY;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color=color;
		this.getRegion();
	}

	public void setRegion(AtlasRegion region) {
		this.region = region;
	}

	public int getTableroPositionX() {
		return tableroPositionX;
	}

	public int getTableroPositionY() {
		return tableroPositionY;
	}

	public void setPosGlobalBlockX(int posGlobalBlockX) {
		this.posGlobalBlockX = posGlobalBlockX;
	}

	public void setPosGlobalBlockY(int posGlobalBlockY) {
		this.posGlobalBlockY = posGlobalBlockY;
	}


	public boolean isRotate() {
		return rotate;
	}


	public void setRotate(boolean rotate) {
		this.rotate = rotate;
	}

	public void getRegion() {

		switch(this.color) {

			case 1:this.region = Assets.blockBlue; break;
			case 2:this.region = Assets.blockGreen; break;
			case 3:this.region = Assets.BlockOrange; break;
			case 4:this.region = Assets.blockPurple; break;
			case 5:this.region = Assets.blockRed; break;

			default: {} break;
		}

		this.elementBlock = new Sprite(region);
		this.elementBlock.setSize(Constants.SPACE,Constants.SPACE);
	}





}

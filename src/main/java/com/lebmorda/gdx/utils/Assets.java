package com.lebmorda.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {
	public static AtlasRegion blockBlue;
	public static AtlasRegion blockGreen;
	public static AtlasRegion BlockOrange;
	public static AtlasRegion blockPurple;
	public static AtlasRegion blockRed;

	public static AtlasRegion background;
	public static TextureAtlas atlas;

	public static void load() {
		//Carga de fondo
		atlas = new TextureAtlas(Gdx.files.internal("data/atlas/tetrisPlus.atlas"));
		
		
		//Carga de imagenes
		blockBlue = atlas.findRegion("block_hd_blue");
		blockGreen = atlas.findRegion("block_hd_green");
		BlockOrange = atlas.findRegion("block_hd_orange");
		blockPurple = atlas.findRegion("block_hd_purple");
		blockRed = atlas.findRegion("block_hd_red");
		background = atlas.findRegion("background");
	}
	
	
	public AtlasRegion getBlock() {
		return null;
	}

}

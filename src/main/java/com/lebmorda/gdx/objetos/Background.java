package com.lebmorda.gdx.objetos;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lebmorda.gdx.utils.Assets;

public class Background extends Actor{
	
	private AtlasRegion fondo;

	public Background() {		
		fondo = Assets.background;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(fondo, 0, 0, fondo.getRegionWidth(), fondo.getRegionHeight());
	}
}

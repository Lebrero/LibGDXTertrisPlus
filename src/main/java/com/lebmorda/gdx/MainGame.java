package com.lebmorda.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.lebmorda.gdx.screens.GameScreen;
import com.lebmorda.gdx.utils.Assets;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {
	
    public SpriteBatch batch;
    public Stage stage;

	@Override
	public void create() {
        batch = new SpriteBatch();
        stage = new Stage(new FitViewport(396, 960));
        Assets.load();
        setScreen(new GameScreen(this));
	}


    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
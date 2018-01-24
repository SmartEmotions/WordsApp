package com.words.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ButtonPlay extends Button
{
	public ButtonPlay(int x, int y)
	{
		super(x, y);
		buttonTexture = new Texture(Gdx.files.internal("boton_play.png"));
	}

	@Override
	protected void funcionamiento()
	{
		Screens.juego.setScreen(Screens.GAMESCREEN);
	}
}
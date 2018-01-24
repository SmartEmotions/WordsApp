package com.words.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ButtonExit extends Button
{

	public ButtonExit(int x, int y)
	{
		super(x, y);
		buttonTexture = new Texture(Gdx.files.internal("boton_exit.png"));
	}

	@Override
	protected void funcionamiento()
	{
		Gdx.app.exit();
	}
}
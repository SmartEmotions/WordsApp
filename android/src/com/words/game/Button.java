package com.words.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class Button
{
	protected Texture buttonTexture;
	protected Rectangle bordes;
	
	protected float xMinima;
	protected float yMinima;
	protected float xMaxima;
	protected float yMaxima;

	public Button(int x, int y) {
		Texture buttonSize = new Texture(Gdx.files.internal("boton_exit.png"));
		bordes = new Rectangle(x, y, buttonSize.getWidth(), buttonSize.getHeight());
		xMinima = bordes.x;
		yMaxima = Gdx.graphics.getHeight() - bordes.y;
		xMaxima = bordes.x + bordes.width;
		yMinima = Gdx.graphics.getHeight() - (bordes.y + bordes.height);
	}

	public void draw(SpriteBatch batch) // El SpriteBatch es como el Graphics en java, con el cual se puede graficar.
	{
		batch.draw(buttonTexture, bordes.x, bordes.y, bordes.width, bordes.height);
	}

	public void update()
	{
		if(sePulsaElBoton())
			funcionamiento();
	}
	private boolean sePulsaElBoton()
	{
		return  Gdx.input.isTouched() &&
				Gdx.input.getX() >= xMinima &&
				Gdx.input.getX() <= xMaxima &&
				Gdx.input.getY() >= yMinima &&
				Gdx.input.getY() <= yMaxima;
	}

	protected abstract void funcionamiento();
}
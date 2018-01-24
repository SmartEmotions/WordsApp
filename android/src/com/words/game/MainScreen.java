package com.words.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen extends AbstractScreen
{
	public SpriteBatch batch;
	private Texture fondoInicio;
	private Button exit, play, about;
	public static final String MIXPANEL_TOKEN = "YOUR_TOKEN";

	public MainScreen(MenuInicio menuInicio)
	{
		super(menuInicio);
	}
	
	@Override
	public void show()
	{
		batch = menuInicio.getBatch();
		fondoInicio = new Texture(Gdx.files.internal("fondoInicio.png"));
		Texture buttonSize = new Texture(Gdx.files.internal("boton_exit.png"));
		int centroY = Gdx.graphics.getHeight() / 2 - buttonSize.getHeight() / 2;
		int centroX = Gdx.graphics.getWidth() / 2 - buttonSize.getWidth() / 2;
		exit = new ButtonExit(centroX, centroY - 150);
		play = new ButtonPlay(centroX, centroY + 150);
		about = new ButtonAbout(centroX, centroY );
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Limpiar
		
		exit.update();
		play.update();
		about.update();
		
		batch.begin();
		batch.draw(fondoInicio, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		exit.draw(batch);
		play.draw(batch);
		about.draw(batch);
		batch.end();
	}
}

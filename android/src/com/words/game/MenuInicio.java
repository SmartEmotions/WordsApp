package com.words.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

public class MenuInicio extends Game
{
	private SpriteBatch batch;
	private static long SPLASH_MINIMUM_MILLIS = 1000L;

	public MenuInicio() { super(); }
	@Override
	public void create ()
	{
		////////////////////////////////////////////////////////////////////////////////
		batch = new SpriteBatch();
		Gdx.input.setCatchBackKey(true); // Bloquea el boton "Back" de android para que se tenga que salir del juego usando el boton "Exit"
		Screens.juego = this;
		Screens.GAMESCREEN = new GameScreen(this);
		Screens.MAINSCREEN = new MainScreen(this);
		////////////////////////////////////////////////////////////////////////////////
		setScreen(new SplashScreen());
		final long splash_start_time = System.currentTimeMillis();
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				Gdx.app.postRunnable(new Runnable()
				{
					@Override
					public void run()
					{

						long splash_elapsed_time = System.currentTimeMillis() - splash_start_time;
						if (splash_elapsed_time < MenuInicio.SPLASH_MINIMUM_MILLIS)
						{
							Timer.schedule(
									new Timer.Task()
									{
										@Override
										public void run()
										{
											setScreen(Screens.MAINSCREEN);
										}
									}, (float)(MenuInicio.SPLASH_MINIMUM_MILLIS - splash_elapsed_time) / 1000f);
						}
						else
						{
						setScreen(Screens.MAINSCREEN);
						}
					}
				});
			}
		}).start();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		Screens.GAMESCREEN.dispose();
	}

	public SpriteBatch getBatch()
	{
		return batch;
	}
}

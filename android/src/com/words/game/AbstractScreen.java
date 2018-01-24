package com.words.game;

import com.badlogic.gdx.Screen;

//Implementa la interfaz de Screen, es decir, se comportara con las caracteristicas de una pantalla
//sus funciones se llaman automaticamente cuando ocurre el evento al que estan asociadas (renderizar,
//reescalar, pausar, resumir...) menos con dispose, para liberar los recursos hay que llamar a dispose manualmente

public class AbstractScreen implements Screen
{
	protected MenuInicio menuInicio; // Necesario en el futuro para pasar de una pantalla a otra.

	public AbstractScreen(MenuInicio menuInicio) {
		this.menuInicio = menuInicio;
	}

	@Override
	public void render(float delta) { // Parecido a paint...
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}

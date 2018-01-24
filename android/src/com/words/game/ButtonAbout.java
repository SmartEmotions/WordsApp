package com.words.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Carlos Guadir on 6/10/2016.
 */

public class ButtonAbout extends Button {
    public ButtonAbout(int x, int y)
    {
        super(x, y);
        buttonTexture = new Texture(Gdx.files.internal("boton_about.png")); // Se asigna textura. Muy importante!!
    }
    protected void funcionamiento()
    {

    }
}

package com.words.game;

//import android.widget.Toast;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Carlos Guadir on 7/10/2016.
 */

public class ButtonOption
{
    private final int x;
    private final int y;
    protected Rectangle bordes;
    protected String wordOption;
    protected Texture buttonOption;
    protected float xMinima;
    protected float yMinima;
    protected float xMaxima;
    protected float yMaxima;


    public ButtonOption(int x, int y, String option, String buttonOption)
    {
        this.x = x;
        this.y = y;
        this.wordOption = option;
        this.buttonOption = new Texture(Gdx.files.internal(buttonOption));
        bordes = new Rectangle(x, y, Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()/8);
        xMinima = bordes.x;
        yMaxima = Gdx.graphics.getHeight() - bordes.y;
        xMaxima = bordes.x + bordes.width;
        yMinima = Gdx.graphics.getHeight() - (bordes.y + bordes.height);
    }

    public boolean isPushThisOption()
    {
        return  Gdx.input.isTouched() &&
                Gdx.input.getX() >= xMinima &&
                Gdx.input.getX() <= xMaxima &&
                Gdx.input.getY() >= yMinima &&
                Gdx.input.getY() <= yMaxima;
    }

    public String getOption()
    {
        return wordOption.toString();
    }

    public Texture getButtonOption() {return buttonOption;}
}

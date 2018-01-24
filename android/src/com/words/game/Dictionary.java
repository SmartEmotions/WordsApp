package com.words.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Carlos Guadir on 8/10/2016.
 */

public class Dictionary
{
    public String englishWord;
    public String translation;
    public String pathImage;
    public String button;
    public Texture wordTexture;
    public Dictionary(String word, String translation, String path, String button)
    {
        this.englishWord = word;
        this.translation = translation;
        this.pathImage = path;
        this.button = button;
        this.wordTexture = new Texture(Gdx.files.internal(path));
    }

    public String getEnglishWord()
    {
        return englishWord;
    }

    public Texture getWordTexture()
    {
        return wordTexture;
    }

    public String getButton() {return  button;}

    public String getPathImage()
    {
        return pathImage;
    }

    public boolean isSolution(String englishWord)
    {
        return this.englishWord.equals(englishWord);
    }
}

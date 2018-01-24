package com.words.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScreen extends AbstractScreen
{
	private SpriteBatch batch;
	private Texture fondoGame, imageSolution;
	private BitmapFont font;
	private List<Dictionary> diccionario;
	private List<ButtonOption> opciones;
	public Dictionary solution;
	//private Preferences preferencias; // Permite almacenar información en un fichero xml, de manera que el juego pueda extraerlos y sobreescribirlos.
	//private int puntuacion, puntuacionMaxima; // Las dos puntuaciones del juego.

    /**
     * Metodo constructor que muestra inicia las opciones que se muestran
     * al usuario, se carga el diccionario con las palabras disponibles
     * @param menuInicio Este parametro carga
     */
	public GameScreen(MenuInicio menuInicio)
	{
		super(menuInicio);
		opciones = new ArrayList<>();
		diccionario = new ArrayList<>();
		loadDictionary();
		loadOptions();
    }

	@Override
    /**
     * Este metodo se llama cuando el Screen es colocado en pantalla.
     *
     */
	public void show()
	{
		batch = menuInicio.getBatch();
		fondoGame = new Texture(Gdx.files.internal("fondoGameScreen.png"));
		font = new BitmapFont(Gdx.files.internal("ubuntu.fnt"), Gdx.files.internal("ubuntu.png"), false);
		font.setColor(Color.BLUE);
	}
	
	@Override
    /**
     * Metodo para generar los graficos; imagen a resolver
     * opciones de imagen en ingles
     * imagen de fondo
     * Tambien es un escuchador que analiza si el usuario
     * ha tocado la pantalla en una de las opciones.
     */
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //updatePuntuación();

        salirMenu();
        batch.begin();
        batch.draw(fondoGame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(solution.getWordTexture(),
                   Gdx.graphics.getWidth()/8,
                   Gdx.graphics.getHeight()/4,
                  (Gdx.graphics.getWidth()/8)*3,
                  (Gdx.graphics.getHeight()/8)*5 );
        for (int i = 0; i < opciones.size(); i++) {
            ButtonOption buttonOption = (opciones.get(i));
            batch.draw(buttonOption.getButtonOption(),
                       buttonOption.xMinima,
                       Gdx.graphics.getHeight()/16,
                       Gdx.graphics.getWidth()/6,
                       Gdx.graphics.getHeight()/8);
            if (buttonOption.isPushThisOption())
            {
                if (isSolution(buttonOption.getOption()))
                {
                    loadOptions();
                    this.pause();
                    this.resume();
                    break;
                }
            }
        }
        batch.end();
    }


    /**
     * Se carga las opciones con un ciclo while, por
     * defecto se cargan 5 opciones aleatorias del Diccionario
     * tambien se valida que las opciones no se repitan.
     */
	public void loadOptions()
	{
        opciones.clear();
        int maxOpciones = 0;
        while (maxOpciones < 4) {
            int numero = new Random().nextInt(diccionario.size());
            String englishWord = diccionario.get(numero).getEnglishWord();
            while (validarOpcion(englishWord)) {
                numero = new Random().nextInt(diccionario.size());
                englishWord = diccionario.get(numero).getEnglishWord();
            }
            String buttonOption = diccionario.get(numero).getButton();
            opciones.add(new ButtonOption((Gdx.graphics.getWidth()/6)/2 +
                                          ((Gdx.graphics.getWidth()/6 + (Gdx.graphics.getWidth()/6)/3) * maxOpciones),
                                          Gdx.graphics.getHeight()/16,
                                          englishWord,
                                          buttonOption));
            maxOpciones ++;
        }
        ButtonOption button = opciones.get(new Random().nextInt(opciones.size()));
		String clave = button.getOption();
        for (int i = 0; i < diccionario.size(); i++) {
           if (diccionario.get(i).getEnglishWord().equals(clave))
           {
               solution = diccionario.get(i);
               break;
           }
        }
        imageSolution = new Texture(Gdx.files.internal(solution.getPathImage()));
    }


    /**
     * Metodo para validar si en las opciones cargadas para vizualizar se repite
     * este metodo compara con el parametro y el contenido de la lista opciones.
     * @param opcion Esta es una opcion que se lanza de manera randomica
     *               desde el metodo cargarOpciones.
     * @return Valor booleano, retorna 'true', si la opcione ya esta en la lista
     * si no se encuentra, retorna falso.
     */
    public boolean validarOpcion(String opcion)
    {
        for (int i = 0 ; i < opciones.size(); i++)
        {
            ButtonOption buttonOption = (opciones.get(i));
            if (buttonOption.getOption().equals(opcion))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isSolution(String englishWord)
    {
        return solution.isSolution(englishWord);
    }
	
	@Override
	public void dispose()
	{
		//preferencias.putInteger("puntuacionMaxima", puntuacionMaxima);
		//preferencias.flush();
	}

    /**
     * Aqui se carga el diccionario con las palabras, aqui se cargan las direcciones
     * a cada imagen, correspondiente.
     */
    public void loadDictionary()
    {
        diccionario.add(new Dictionary("Strawberry","Fresa", "icons/fresa.png", "icons/strawberry.png"));
        diccionario.add(new Dictionary("Banana","Banano", "icons/banano.png", "icons/banana.png"));
        diccionario.add(new Dictionary("Cherry","Cereza", "icons/cereza.png", "icons/cherry.png"));
        diccionario.add(new Dictionary("Soursop","Guanabana", "icons/guanabana.png", "icons/soursop.png"));
        diccionario.add(new Dictionary("Grapes","Uvas", "icons/uvas.png", "icons/grapes.png"));
        diccionario.add(new Dictionary("Bee","Abeja", "icons/abeja.png", "icons/bee.png"));
        diccionario.add(new Dictionary("Tree","Arbol", "icons/arbol.png", "icons/tree.png"));
        diccionario.add(new Dictionary("Zebra","Cebra", "icons/cebra.png", "icons/zebra.png"));
        diccionario.add(new Dictionary("Cup","Copa", "icons/copa.png", "icons/cup.png"));
        diccionario.add(new Dictionary("Heart","Corazon", "icons/corazon.png", "icons/heart.png"));
        diccionario.add(new Dictionary("Dices","Dados", "icons/dados.png", "icons/dices.png"));
        diccionario.add(new Dictionary("Ghost","Fantasma", "icons/fantasma.png", "icons/ghost.png"));
        diccionario.add(new Dictionary("Fire","Fuego", "icons/fuego.png", "icons/fire.png"));
        diccionario.add(new Dictionary("Giraffe","Jirafa", "icons/jirafa.png", "icons/giraffe.png"));
        diccionario.add(new Dictionary("Lettuce","Lechuga", "icons/lechuga.png", "icons/lettuce.png"));
        diccionario.add(new Dictionary("Ladybug","Mariquita", "icons/mariquita.png", "icons/ladybug.png"));
        diccionario.add(new Dictionary("Pepper","Pimienta", "icons/pimienta.png", "icons/pepper.png"));
        diccionario.add(new Dictionary("Radish","Rabano", "icons/rabano.png", "icons/radish.png"));
        diccionario.add(new Dictionary("Frog","Rana", "icons/rana.png", "icons/frog.png"));
        diccionario.add(new Dictionary("Carrot","Zanahoria", "icons/zanahoria.png", "icons/carrot.png"));
        diccionario.add(new Dictionary("Mushroom","Seta", "icons/seta.png", "icons/mushroom.png"));
        diccionario.add(new Dictionary("Shoes","Zapatos", "icons/zapato.png", "icons/shoes.png"));
        diccionario.add(new Dictionary("Arrow","Flecha", "icons/flecha.png", "icons/arrow.png"));
        diccionario.add(new Dictionary("Fox","Zorro", "icons/zorro.png", "icons/fox.png"));
        diccionario.add(new Dictionary("Eye","Ojo", "icons/ojo.png", "icons/eye.png"));
        diccionario.add(new Dictionary("Scissors","Tijera", "icons/tijera.png", "icons/scissors.png"));
        diccionario.add(new Dictionary("Sunflower","Girasol", "icons/girasol.png", "icons/sunflower.png"));
        diccionario.add(new Dictionary("Coin","Moneda", "icons/moneda.png", "icons/coin.png"));
        diccionario.add(new Dictionary("Mouth","Boca", "icons/boca.png", "icons/mouth.png"));
        diccionario.add(new Dictionary("Unicorn","Unicornio", "icons/unicornio.png", "icons/unicorn.png"));
        diccionario.add(new Dictionary("Watermelon","Sandia", "icons/sandia.png", "icons/watermelon.png"));
        diccionario.add(new Dictionary("Goat","Cabra", "icons/cabra.png", "icons/goat.png"));
        diccionario.add(new Dictionary("Umbrella","Paraguas", "icons/sombrilla.png", "icons/umbrella.png"));
        diccionario.add(new Dictionary("Gamepad","Joystick", "icons/joystick.png", "icons/gamepad.png"));
        diccionario.add(new Dictionary("Smartwatch","Reloj Inteligente", "icons/reloj.png", "icons/smartwatch.png"));
        diccionario.add(new Dictionary("Cricket","Grillo", "icons/grillo.png", "icons/cricket.png"));
        diccionario.add(new Dictionary("Branch","Rama", "icons/rama.png", "icons/branch.png"));
        diccionario.add(new Dictionary("Leaf","Hoja", "icons/hoja.png", "icons/leaf.png"));
        diccionario.add(new Dictionary("Screw","Tornillo", "icons/tornillos.png", "icons/screw.png"));
        diccionario.add(new Dictionary("Hand","Mano", "icons/mano.png", "icons/hand.png"));
        diccionario.add(new Dictionary("Think","Pensar", "icons/pensando.png", "icons/think.png"));
        diccionario.add(new Dictionary("Rocket","Cohete", "icons/cohete.png", "icons/rocket.png"));
        diccionario.add(new Dictionary("Wolf","Lobo", "icons/lobo.png", "icons/wolf.png"));
        diccionario.add(new Dictionary("Rope","Cuerda", "icons/cuerda.png", "icons/rope.png"));
        diccionario.add(new Dictionary("Midge","Mosquito", "icons/mosca.png", "icons/midge.png"));
        diccionario.add(new Dictionary("Vetch","Arbeja", "icons/arbeja.png", "icons/vetch.png"));
        diccionario.add(new Dictionary("Hammer","Martillo", "icons/martillo.png", "icons/hammer.png"));
    }

    @Override
    public void hide()
    {
        font.dispose();
        fondoGame.dispose();
    }


    /**
     * Metodo que analiza si el boton back del dispositivo ha sido
     * presionado, si es el caso, se retorna al menu de inicio.
     */
    private void salirMenu()
    {
        if(Gdx.input.isKeyPressed(Keys.BACK)){
            Screens.juego.setScreen(Screens.MAINSCREEN);
        }
    }
}

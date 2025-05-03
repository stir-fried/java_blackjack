package io.github.some_example_name;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import java.util.ArrayList;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {

    private Texture background;
    private FitViewport viewport;
    private SpriteBatch spriteBatch;
    private Texture cardTexture;
    private List<Sprite> cardSprites;
    private OrthographicCamera camera;




    @Override
    public void create() {

        background = new Texture("table.jpg");
        camera = new OrthographicCamera();
        viewport = new FitViewport(914, 610, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        spriteBatch = new SpriteBatch();
        cardSprites = new ArrayList<>();
        Deck deck = new Deck();


        /*deck.shuffle();

        for (Cards card : deck.getCards()) {
            String fileName = card.getAssetFileName();
            Texture cardTexture = new Texture(fileName);
            Sprite cardSprite = new Sprite(cardTexture);
            cardSprites.add(cardSprite);
        }*/

        Cards card = deck.dealCard();
        if (card != null) {
        String fileName = card.getAssetFileName();
        Texture cardTexture = new Texture(fileName);
        System.out.println("Loaded texture for: " + card);
    }

    }

    @Override
    public void resize(int width, int height) {
        // Resize your application here. The parameters represent the new window size.
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2,
            camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void render() {
        // Draw your application here
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 914, 610);

        // Draw all card sprites
        float x = 0, y = 0; // Starting position
        for (Sprite cardSprite : cardSprites) {
            cardSprite.setPosition(x, y);
            cardSprite.setSize(100, 150); // Set size for each card
            cardSprite.draw(spriteBatch);
            x += 110; // Adjust spacing between cards
        }

        spriteBatch.end();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
    }
}

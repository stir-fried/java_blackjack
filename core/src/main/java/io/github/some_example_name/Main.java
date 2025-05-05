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
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main implements ApplicationListener {

    private Texture background;
    private FitViewport viewport;
    private SpriteBatch spriteBatch;
    private Texture cardTexture;
    private List<Sprite> cardSprites;
    private OrthographicCamera camera;
    private ArrayList<Sprite> playerCards;
    private ArrayList<Sprite> dealerCards;
    private Deck deck;
    private Boolean gameOver;

    public void dealPlayer()
    {

        Cards playerCard = deck.dealCard();
         if (playerCard != null) {
             String fileName = playerCard.getAssetFileName();
             Texture cardTexture = new Texture(fileName);
             Sprite cardSprite = new Sprite(cardTexture);
             playerCards.add(cardSprite);
             System.out.println("Player card value: " + playerCard);
         }
    }

    public void dealDealer(){

        Cards dealerCard = deck.dealCard();
         if (dealerCard != null) {
             String fileName = dealerCard.getAssetFileName();
             Texture cardTexture = new Texture(fileName);
             Sprite cardSprite = new Sprite(cardTexture);
             dealerCards.add(cardSprite);
             System.out.println("dealer card value: " + dealerCard);
         }

    }


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
        deck = new Deck();
        playerCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        gameOver = false;

        deck.shuffle();
        dealPlayer();
        dealDealer();
        dealPlayer();
        dealDealer();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2,
            camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 914, 610);


        float x = 200, y = 50;
        for (Sprite card : playerCards) {
            card.setPosition(x, y);
            card.draw(spriteBatch);
            card.setSize(163,232);
            x += 81;
        }

        x = 200;
        y = 350;
        for (int i = 0; i < dealerCards.size(); i++) {
        Sprite card = dealerCards.get(i);
        if (i == 1 && !gameOver) {

            Texture hiddenTexture = new Texture("hidden_card.png");
            Sprite hiddenCard = new Sprite(hiddenTexture);
            hiddenCard.setPosition(x, y);
            hiddenCard.setSize(163, 232);
            hiddenCard.draw(spriteBatch);
        } else {

            card.setPosition(x, y);
            card.setSize(163, 232);
            card.draw(spriteBatch);
        }
        x += 81;
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

    public void move(){

    }
}

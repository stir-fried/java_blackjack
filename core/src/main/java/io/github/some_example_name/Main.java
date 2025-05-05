package io.github.some_example_name;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


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
    private Boolean showDealer;
    private BitmapFont font;
    private Stage stage;
    private Skin skin;
    private int playerScore;
    private int dealerScore;
    private String winner;
    private boolean gameOver;

    public void dealPlayer() {

        Cards playerCard = deck.dealCard();
        if (playerCard != null) {
            String fileName = playerCard.getAssetFileName();
            Texture cardTexture = new Texture(fileName);
            Sprite cardSprite = new Sprite(cardTexture);
            playerCards.add(cardSprite);

            playerScore += GameRules.getCardValue(Cards.getRank(fileName));

            if (playerScore == 21){
                showDealer = true;
                winner = "Player Wins";
            }
            if (playerScore > 21) {
                showDealer = true;
                winner = "Dealer Wins";
            }

        }
    }

    public void dealDealer() {

        Cards dealerCard = deck.dealCard();
        if (dealerCard != null) {
            String fileName = dealerCard.getAssetFileName();
            Texture cardTexture = new Texture(fileName);
            Sprite cardSprite = new Sprite(cardTexture);
            dealerCards.add(cardSprite);
            System.out.println("Dealer card: " + dealerCard);
            dealerScore += GameRules.getCardValue(Cards.getRank(fileName));


        }

    }

    public void checker(){
         if (gameOver){
             winner = GameRules.winCondition(playerScore, dealerScore);
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
        showDealer = false;
        winner = "";
        gameOver = false;

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        TextButton hitButton = new TextButton("HIT", skin);
        hitButton.setSize(100, 40);
        hitButton.setPosition(200, 230);
        hitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (hitButton.isDisabled()) return;
                dealPlayer();
                checker();

            }
        });


        TextButton holdButton = new TextButton("HOLD", skin);
        holdButton.setSize(100, 40);
        holdButton.setPosition(300, 230);
        holdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (holdButton.isDisabled()) return;
                showDealer = true;
                hitButton.setDisabled(true);
                while (dealerScore < 17) {
                    dealDealer();
                }
                gameOver = true;
                checker();

            }
        });

        stage.addActor(hitButton);
        stage.addActor(holdButton);


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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen at the start
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 914, 610);

        font.draw(spriteBatch, "Player Score: " + playerScore, 50, 40);
        font.draw(spriteBatch, winner, 600, 330);

        float x = 200, y = 50;
        for (Sprite card : playerCards) {
            card.setPosition(x, y);
            card.setSize(163, 232);
            card.draw(spriteBatch);
            x += 81;
        }

        x = 200;
        y = 350;
        for (int i = 0; i < dealerCards.size(); i++) {
            Sprite card = dealerCards.get(i);
            if (i == 1 && !showDealer) {
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
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        stage.dispose();
        spriteBatch.dispose();
        background.dispose();
        skin.dispose();
    }

    public void move() {

    }
}

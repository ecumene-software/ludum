package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

public class KingScreen implements Screen, InputProcessor{
    private OrthographicCamera camera;

    // Characters

    private Sculpture sculpture;

    private Texture kingsRoom;
    private Texture pleb;
    private Texture pleb_happy;
    private Texture pleb_sad;
    private Sprite  king;
    private Sprite  king1;
    private Sprite  king2;
    private Sprite  king3;
    private Sprite  king4;
    private Sprite  king_angry;
    private Sprite  king_happy;
    private Sprite  speechbubble;

    private BitmapFont font;

    public KingScreen(Sculpture sculpture){
        this.pleb         = new Texture(Gdx.files.internal("plebpresentingsculpture.png"));
        this.pleb_happy   = new Texture(Gdx.files.internal("plebpresentingsculpture_happy.png"));
        this.pleb_sad     = new Texture(Gdx.files.internal("plebpresentingsculpture_sad.png"));
        this.kingsRoom    = new Texture(Gdx.files.internal("kingsRoom.png"));
        this.king         = new Sprite(new Texture(Gdx.files.internal("king.png")));
        this.king1        = new Sprite(new Texture(Gdx.files.internal("king1.png")));
        this.king2        = new Sprite(new Texture(Gdx.files.internal("king2.png")));
        this.king3        = new Sprite(new Texture(Gdx.files.internal("king3.png")));
        this.king4        = new Sprite(new Texture(Gdx.files.internal("king4.png")));
        this.king_angry   = new Sprite(new Texture(Gdx.files.internal("king_angry.png")));
        this.king_happy   = new Sprite(new Texture(Gdx.files.internal("king_happy.png")));
        this.speechbubble = new Sprite(new Texture(Gdx.files.internal("speechbubble.png")));
        speechbubble.flip(true, false);
        this.sculpture = sculpture;
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    private boolean speaking = false;
    private float time;
    private boolean urArtIsShit = false;
    private String speech = "";
    private Random random = new Random();

    @Override
    public void render(float delta) {
        time += delta;

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if(time > 1) king = king1;
        if(time > 2) king = king2;
        if(time > 3) king = king3;
        if(time > 4) king = king4;

        if(speech.equals("") && time > 8) {
            urArtIsShit = random.nextBoolean();
            String[] responses_good = new String[]{
                    "Absolutely beautiful. I love the tapestry!",
                    "Hmm, needs more clothing... But a fine work\n none the less!",
                    "It'll take it, but could do with some more\n edges. Be bold!",
                    "Oh boy, this one's a winner! Pay the man!",
                    "Now we're speaking! That's a fine sculpture!\n What satire!",
                    "I love it! I hope there's more to come?",
                    "Such fine curves, the lines... The\n shapes!",
            };
            String[] responses_bad = new String[]{
                    "Christ, how did I trust a pleb? This is\n absolute rubbish!",
                    "Just horrible, no vision! Bring me a\n sculptor with VISION!",
                    "*scoff* GET OUT!",
                    "I can't believe you plebs. Get out of\n my kingdom you swine!",
                    "Needs more cowbell! Begone!",
                    "Haha, well. You tried!",
                    "Oh please... Get out.",
            };
            if(urArtIsShit) speech = responses_bad[random.nextInt(responses_bad.length-1)];
            else            speech = responses_good[random.nextInt(responses_good.length-1)];
        }
        if(time > 8) {
            speaking = true;
            if (urArtIsShit) king = king_angry;
            else             king = king_happy;
        }
        if(time > 8+1) {
            if (urArtIsShit) pleb = pleb_sad;
            else             pleb = pleb_happy;
        }

        camera.update();
        SculptureSimulator.getInstance().batch.setProjectionMatrix(camera.combined);
        SculptureSimulator.getInstance().batch.begin();
        SculptureSimulator.getInstance().batch.draw(kingsRoom, 0, 0);
        SculptureSimulator.getInstance().batch.end();
        sculpture.render(360, pleb.getHeight() + 20, 17);
        SculptureSimulator.getInstance().batch.begin();
        SculptureSimulator.getInstance().batch.draw(king, 0, Gdx.graphics.getHeight() - king.getHeight());
        SculptureSimulator.getInstance().batch.draw(pleb, 260, 0, pleb.getWidth()*1.5f, pleb.getHeight()*1.5f);
        if(speaking) {
            SculptureSimulator.getInstance().batch.draw(speechbubble, king.getWidth(), Gdx.graphics.getHeight() - speechbubble.getHeight());
            font.setColor(new Color(0, 0, 0, 1));
            font.draw(SculptureSimulator.getInstance().batch, speech, king.getWidth() + 40, Gdx.graphics.getHeight() - speechbubble.getHeight() + 120);
        }
        SculptureSimulator.getInstance().batch.end();

        if(time > 12) SculptureSimulator.getInstance().setScreen(new SculptingScreen());
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(width, height);
        camera.position.set(width/2, height/2, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }
}

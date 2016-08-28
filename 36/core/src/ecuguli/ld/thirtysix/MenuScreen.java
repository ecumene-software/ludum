package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MenuScreen implements Screen, InputProcessor {

    private OrthographicCamera camera;
    // Authors
    private Texture author_1;
    private Texture author_2;

    private Sprite logo_1;
    private Sprite logo_2;
    private Sprite play_button;
    private Sprite background;

    private BitmapFont font;
    private boolean firstTime;

    public MenuScreen(boolean firstTime){
        this.firstTime = firstTime;

        author_1 = new Texture(Gdx.files.internal("ecumenenoframe.png"));
        author_2 = new Texture(Gdx.files.internal("rangulinoframe.png"));

        logo_1 = new Sprite(new Texture(Gdx.files.internal("logo.png")));
        logo_2 = new Sprite(new Texture(Gdx.files.internal("sublogo.png")));
        play_button = new Sprite(new Texture(Gdx.files.internal("play.png")));
        background = new Sprite(new Texture(Gdx.files.internal("background.png")));

        font = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    private float logo1Y = 1000;
    private float logo2Y = -100000;
    private float authorsX = -300000;
    private float x;
    private float mx, my;

    @Override
    public void render(float delta) {
        x += delta * 5;

        float logo1TargetY = (Gdx.graphics.getHeight()/2);
        float logo2TargetY = ((Gdx.graphics.getHeight()/2)-(logo_1.getHeight())/2)-20;
        float authorsTargetX = 10;

        Gdx.gl.glClearColor(0.564705882f, 0.384313725f, 0.53725490196f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        logo1Y += ((logo1TargetY - logo1Y) * .9)*(delta *4);
        logo2Y -= ((logo2Y - logo2TargetY) * .9)*(delta *4);
        authorsX += (authorsTargetX-authorsX * .9)*(delta *4);

        camera.update();
        SculptureSimulator.getInstance().batch.setProjectionMatrix(camera.combined);
        SculptureSimulator.getInstance().batch.begin();
        float logoWidth = logo_1.getWidth();
        SculptureSimulator.getInstance().batch.draw(background, (mx - Gdx.graphics.getWidth()/2)*.015f, (my - Gdx.graphics.getHeight()/2)*.015f, background.getWidth() * 1.2f, background.getHeight() * 1.2f);
        SculptureSimulator.getInstance().batch.draw(logo_1, (Gdx.graphics.getWidth()-logoWidth)/2 , logo1Y);
        SculptureSimulator.getInstance().batch.draw(logo_2, (Gdx.graphics.getWidth()-logoWidth)/2 , logo2Y);
        SculptureSimulator.getInstance().batch.draw(play_button, (Gdx.graphics.getWidth()-play_button.getWidth())/2 , (logo2Y - play_button.getHeight() - 20)+((float)Math.abs(Math.cos(x))*20));
        font.draw(SculptureSimulator.getInstance().batch, "M A D E  B Y ", authorsX, author_1.getHeight() + 35);
        SculptureSimulator.getInstance().batch.draw(author_1, authorsX + author_1.getWidth(), 5);
        SculptureSimulator.getInstance().batch.draw(author_2, authorsX, 5);
        SculptureSimulator.getInstance().batch.end();
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

    public boolean isFirstTime() {
        return firstTime;
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
        if(isFirstTime()) SculptureSimulator.getInstance().setScreen(new TutorialScreen());
        else              SculptureSimulator.getInstance().setScreen(new SculptingScreen());
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        mx = i;
        my = i1;
        return false;
    }
}

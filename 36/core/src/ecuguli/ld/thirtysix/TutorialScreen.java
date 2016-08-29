package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TutorialScreen implements Screen, InputProcessor {

    private OrthographicCamera camera;
    private BitmapFont         font;
    private Texture            scroll;

    public TutorialScreen(){
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        scroll = new Texture(Gdx.files.internal("scroll.png"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    float x1 = -2000;
    float x2 = 8000;
    float x3 = -90000;
    float y1 = -900000;
    float x = 0;

    @Override
    public void render(float delta) {
        x += delta * 5;

        Gdx.gl.glClearColor(194f/255f, 182f/255f, 142f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        float x1Target = -250;
        float x2Target = -390;
        float x3Target = -350;
        float y1Target = -120;

        x1 += ((x1Target - x1)  * .9)*(delta *4);
        x2 -= ((x2 - x2Target)  * .9)*(delta *4);
        x3 += ((x3Target - x3)  * .9)*(delta *4);
        y1 += ((y1Target - y1)  * .9)*(delta *4);

        camera.update();
        SculptureSimulator.getInstance().batch.setProjectionMatrix(camera.combined);
        SculptureSimulator.getInstance().batch.begin();
        float width  = (scroll.getWidth()) * 2.5f;
        float height = (scroll.getHeight())* 1.5f;
        SculptureSimulator.getInstance().batch.draw(scroll, -width/2, -height/2, width, height);
        font.setColor(new Color(0.4f, 0.4f, 0.4f, 255));
        font.draw(SculptureSimulator.getInstance().batch, "THE KING IS OPENING AN ART GALLERY", x1, 60);
        font.draw(SculptureSimulator.getInstance().batch, "YOU ARE HIS ROYAL SCULPTOR, TASKED TO MAKE SCULPTURES TO HIS TASTES", x2, 0);
        font.draw(SculptureSimulator.getInstance().batch, "YOUR WORK WILL BE PRESENTED TO HIS MAJESTY BY YOU PERSONALLY", x3, -60);
        font.draw(SculptureSimulator.getInstance().batch, "(Click!)", -20, (float) Math.abs(Math.sin(x)) * 20f + y1);
        SculptureSimulator.getInstance().batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(width, height);
        camera.position.set(0, 0, 0);
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
        SculptureSimulator.getInstance().setScreen(new SculptingScreen());
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

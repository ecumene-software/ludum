package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class SculptingScreen implements Screen, InputProcessor {

    private OrthographicCamera camera;
    private BitmapFont font;

    // Characters
    private Texture pleb;
    private Texture pleb_chisel1;
    private Texture pleb_chisel2;
    private Texture background;
    private Texture table;

    private Sculpture sculpture;

    private Texture pleb_state;

    public SculptingScreen(){
        pleb         = new Texture(Gdx.files.internal("pleb.png"));
        pleb_chisel1 = new Texture(Gdx.files.internal("plebchisel1.png"));
        pleb_chisel2 = new Texture(Gdx.files.internal("plebchisel2.png"));
        background   = new Texture(Gdx.files.internal("sculpting.png"));
        table        = new Texture(Gdx.files.internal("table.png"));
        sculpture    = new Sculpture(10, 16);
        pleb_state = pleb;
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    public static boolean chiselFrame;

    String confirmText = "Done?";

    float x;
    float tableTarget = 0;
    float tableY = 0;

    @Override
    public void render(float delta) {
        x += delta * 14;

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if(chiselFrame) {
            pleb_state = pleb_chisel2;
            tableTarget = -20;
        }
        else{
            pleb_state = pleb_chisel1;
            tableTarget = 0;
        }

        tableY += ((tableTarget - tableY) * 0.9f) * (delta * 4);

        camera.update();
        SculptureSimulator.getInstance().batch.setProjectionMatrix(camera.combined);
        SculptureSimulator.getInstance().batch.begin();
        SculptureSimulator.getInstance().batch.draw(background, 0, 0);
        SculptureSimulator.getInstance().batch.draw(table, 0, tableY);
        SculptureSimulator.getInstance().batch.draw(pleb_state, 220, 0, pleb_state.getWidth()*2, pleb_state.getHeight()*2);
        font.draw(SculptureSimulator.getInstance().batch, confirmText, Gdx.graphics.getWidth()-200, 30);
        font.draw(SculptureSimulator.getInstance().batch, "Restart: 'R' ", 165, 180+tableY);
        SculptureSimulator.getInstance().batch.end();

        sculpture.render(125, 190 + tableY, 17);
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
        if(i == Input.Keys.R) sculpture = new Sculpture(10, 16);
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
        if(!sculpture.isInRange(125, 190, i, i1, 17, 220)) chiselFrame = true;
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        sculpture.onMouseClick(125, 190, i, i1, 17, 220);
        if(i > Gdx.graphics.getWidth() - 200 && i1 > Gdx.graphics.getHeight() - 30) {
            if (confirmText == "Are you sure?") SculptureSimulator.getInstance().setScreen(new KingScreen(sculpture));
            confirmText = "Are you sure?";
        }

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

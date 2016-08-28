package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class SculptingScreen implements Screen {

    private OrthographicCamera camera;

    // Characters
    private Texture pleb;
    private Texture background;
    private Texture table;

    private Sculpture sculpture;

    public SculptingScreen(){
        pleb       = new Texture(Gdx.files.internal("pleb.png"));
        background = new Texture(Gdx.files.internal("sculpting.png"));
        table      = new Texture(Gdx.files.internal("table.png"));
        sculpture = new Sculpture(35, 56);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camera.update();
        SculptureSimulator.getInstance().batch.setProjectionMatrix(camera.combined);
        SculptureSimulator.getInstance().batch.begin();
        SculptureSimulator.getInstance().batch.draw(background, 0, 0);
        SculptureSimulator.getInstance().batch.draw(table, 0, 0);
        SculptureSimulator.getInstance().batch.draw(pleb, Gdx.graphics.getWidth() - pleb.getWidth(), pleb.getHeight());
        SculptureSimulator.getInstance().batch.end();

        sculpture.render(125, 230, 5);
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

}

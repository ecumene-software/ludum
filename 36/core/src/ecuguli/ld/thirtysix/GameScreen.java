package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {

    private OrthographicCamera camera;

    // Characters
    private Texture king_face;
    private Texture pleb_face;

    public GameScreen(){
        king_face = new Texture(Gdx.files.internal("ecumenenoframe.png"));
        pleb_face = new Texture(Gdx.files.internal("rangulinoframe.png"));
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
}

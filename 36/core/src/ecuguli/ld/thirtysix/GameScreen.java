package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class GameScreen implements Screen {

    private Sculpture          sculpture;
    private SculptureRenderer  renderer;
    private OrthographicCamera camera;

    public GameScreen(){
        sculpture = new Sculpture(10, 10, 10);
        sculpture.sculpt(0, 9, 0);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        renderer = new SculptureRenderer(200, 500);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(20, 20);
        camera.position.set(60, 60, 60);
        camera.lookAt(0, 0, 0);
        camera.near = 0;
        camera.far  = 10000;
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
        modelBatch.dispose();
    }
}

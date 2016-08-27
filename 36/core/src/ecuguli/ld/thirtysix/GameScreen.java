package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private Sculpture          sculpture;
    private SculptureRenderer  renderer;

    public GameScreen(){
        sculpture = new Sculpture(10, 10, 10);
        sculpture.sculpt(0, 9, 0);
        renderer = new SculptureRenderer(sculpture, 1200, 900);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camera.update();
        SculptureSimulator.getInstance().batch.setProjectionMatrix(camera.combined);
        SculptureSimulator.getInstance().batch.begin();
        Sprite s = new Sprite(renderer.renderSculpture());
        s.flip(false, true);
        SculptureSimulator.getInstance().batch.draw(s, 0, 0);
        SculptureSimulator.getInstance().batch.getShader().begin();
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
        renderer.dispose();
    }
}

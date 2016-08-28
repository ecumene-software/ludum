package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MenuScreen implements Screen {

    private OrthographicCamera camera;
    // Authors
    private Texture author_1;
    private Texture author_2;

    private Sprite logo_1;
    private Sprite logo_2;
    private Sprite play_button;

    public MenuScreen(){
        author_1 = new Texture(Gdx.files.internal("ecumenenoframe.png"));
        author_2 = new Texture(Gdx.files.internal("rangulinoframe.png"));

        logo_1 = new Sprite(new Texture(Gdx.files.internal("logo.png")));
        logo_2 = new Sprite(new Texture(Gdx.files.internal("sublogo.png")));
        play_button = new Sprite(new Texture(Gdx.files.internal("play.png")));
    }

    @Override
    public void show() {

    }

    private float logo1Y = 1000;
    private float logo2Y = -100000;
    private float x;

    @Override
    public void render(float delta) {
        x += delta * 5;

        float logo1TargetY = (Gdx.graphics.getHeight()/2);
        float logo2TargetY = ((Gdx.graphics.getHeight()/2)-(logo_1.getHeight())/2)-20;

        Gdx.gl.glClearColor(0.564705882f, 0.384313725f, 0.53725490196f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        logo1Y += ((logo1TargetY - logo1Y) * .9)*(delta *4);
        logo2Y -= ((logo2Y - logo2TargetY) * .9)*(delta *4);

        camera.update();
        SculptureSimulator.getInstance().batch.setProjectionMatrix(camera.combined);
        SculptureSimulator.getInstance().batch.begin();
        float logoWidth = logo_1.getWidth();
        SculptureSimulator.getInstance().batch.draw(logo_1, (Gdx.graphics.getWidth()-logoWidth)/2 , logo1Y);
        SculptureSimulator.getInstance().batch.draw(logo_2, (Gdx.graphics.getWidth()-logoWidth)/2 , logo2Y);
        SculptureSimulator.getInstance().batch.draw(play_button, (Gdx.graphics.getWidth()-play_button.getWidth())/2 , (logo2Y - play_button.getHeight() - 20)+((float)Math.abs(Math.cos(x))*20));
        SculptureSimulator.getInstance().batch.draw(author_1, (Gdx.graphics.getWidth()-play_button.getWidth())/2 - author_1.getWidth(), logo2Y - author_1.getHeight() - play_button.getHeight() - 10);
        SculptureSimulator.getInstance().batch.draw(author_2, (Gdx.graphics.getWidth()-play_button.getWidth())/2 - author_1.getWidth(), logo2Y - author_2.getHeight() - play_button.getHeight() - 10);
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

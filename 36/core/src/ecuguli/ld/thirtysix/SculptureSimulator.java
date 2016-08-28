package ecuguli.ld.thirtysix;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class SculptureSimulator extends Game {

	public static SculptureSimulator INSTANCE;
	public SpriteBatch batch;

	public SculptureSimulator(){}

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(true));
	}

	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}

	public static SculptureSimulator getInstance() {
		return INSTANCE;
	}
}

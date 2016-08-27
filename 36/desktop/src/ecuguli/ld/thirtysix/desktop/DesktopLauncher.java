package ecuguli.ld.thirtysix.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ecuguli.ld.thirtysix.SculptureSimulator;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(SculptureSimulator.INSTANCE = new SculptureSimulator(), config);
	}
}

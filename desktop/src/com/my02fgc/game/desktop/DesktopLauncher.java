package com.my02fgc.game.desktop;

		import com.badlogic.gdx.Files;
		import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
		import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
		import com.my02fgc.game.game.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Calabazin Escape 02FGC";
		config.width = 1024;
		config.height = 768;
		config.resizable=false;
		config.addIcon("juego.bmp", Files.FileType.Internal);
		new LwjglApplication(new Juego(), config);
	}
}

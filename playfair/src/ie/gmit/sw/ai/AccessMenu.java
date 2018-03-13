package ie.gmit.sw.ai;

import java.io.IOException;

public interface AccessMenu {

	void start() throws IOException, InterruptedException;

	void showOptionsKey() throws IOException, InterruptedException;

	void showOptionsEncDec();

	void clearConsole();

	void showLogo();

}
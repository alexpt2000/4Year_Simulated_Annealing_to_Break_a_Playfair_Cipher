package ie.gmit.sw.ai.file;

import java.io.IOException;

public interface AccessFileManager {

	/**
	 * List files on screen.
	 *
	 * @return the string
	 */
	String listFilesScreen();

	/**
	 * Force decrypt parsing file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	void ForceDecryptParsingFile(String file) throws IOException, InterruptedException;

	/**
	 * Decrypt parsing file.
	 *
	 * @param file the file
	 * @param keyword the keyword
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	void DecryptParsingFile(String file, String keyword) throws IOException, InterruptedException;

	/**
	 * Encrypt parsing file.
	 *
	 * @param file the file
	 * @param keyword the keyword
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	void EncryptParsingFile(String file, String keyword) throws IOException, InterruptedException;

	/**
	 * Validate file.
	 *
	 * @param file the file
	 * @return the boolean
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	Boolean validateFile(String file) throws IOException;

}
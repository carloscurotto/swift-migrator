package ar.com.carloscurotto.swiftalliance.migrator;

import java.io.File;

import ar.com.carloscurotto.swiftalliance.migrator.reader.SwiftFileReader;
import ar.com.carloscurotto.swiftalliance.migrator.record.SwiftRecord;
import ar.com.carloscurotto.swiftalliance.migrator.writer.SwiftConsoleWriter;

/**
 * This is the main runner class that will migrate all the files specified in
 * the given directory and its sub-directories.
 * 
 * @author carloscurotto
 * 
 */
public class SwiftDirectoryMigratorRunner {

	/**
	 * Migrates all the files contained in the given directory and its
	 * sub-directories.
	 * 
	 * @param directory
	 *            the root directory to migrate.
	 */
	private static void migrateFiles(final File directory) {
		for (final File file : directory.listFiles()) {
			if (file.isDirectory()) {
				migrateFiles(file);
			} else {
				migrateFile(file);
			}
		}
	}

	/**
	 * Migrates all the entries in the given file.
	 * 
	 * @param file
	 *            the file to migrate.
	 */
	private static void migrateFile(final File file) {
		SwiftFileReader reader = new SwiftFileReader(file.getAbsolutePath());
		SwiftConsoleWriter writer = new SwiftConsoleWriter();
		Migrator<SwiftRecord> migrator = new Migrator<SwiftRecord>(reader,
				writer);

		migrator.open();
		migrator.migrate();
		migrator.close();
	}

	public static void main(String[] args) {
		
		String directoryPath = "/Users/carloscurotto/Documents/development/workspace/swift-migrator/src/test/resources";
		
		File directory = new File(directoryPath);
		
		migrateFiles(directory);
	}

}

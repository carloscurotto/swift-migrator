package ar.com.carloscurotto.swiftalliance.migrator;

import ar.com.carloscurotto.swiftalliance.migrator.reader.SwiftFileReader;
import ar.com.carloscurotto.swiftalliance.migrator.record.SwiftRecord;
import ar.com.carloscurotto.swiftalliance.migrator.writer.SwiftConsoleWriter;

/**
 * This is the main runner class that will migrate all the entries specified in
 * the given file.
 * 
 * @author carloscurotto
 * 
 */
public class SwiftFileMigratorRunner {

	public static void main(String[] args) {

		String filePath = "/Users/carloscurotto/Documents/development/workspace/swift-migrator/src/test/resources/example.txt";

		SwiftFileReader reader = new SwiftFileReader(filePath);
		SwiftConsoleWriter writer = new SwiftConsoleWriter();
		Migrator<SwiftRecord> migrator = new Migrator<SwiftRecord>(reader,
				writer);

		migrator.open();
		migrator.migrate();
		migrator.close();
	}

}

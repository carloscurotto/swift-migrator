package ar.com.carloscurotto.swiftalliance.migrator;

import ar.com.carloscurotto.swiftalliance.migrator.reader.SwiftFileReader;
import ar.com.carloscurotto.swiftalliance.migrator.record.SwiftRecord;
import ar.com.carloscurotto.swiftalliance.migrator.writer.SwiftConsoleWriter;

public class MigratorRunner {
	
	public static void main(String[] args) {
		
		SwiftFileReader reader = new SwiftFileReader("/Users/carloscurotto/Documents/development/workspace/swift-migrator/src/test/resources/example.txt");
		SwiftConsoleWriter writer = new SwiftConsoleWriter();
		
		Migrator<SwiftRecord> migrator = new Migrator<SwiftRecord>(reader, writer);
		
		migrator.open();
		
		migrator.migrate();
		
		migrator.close();
	}

}

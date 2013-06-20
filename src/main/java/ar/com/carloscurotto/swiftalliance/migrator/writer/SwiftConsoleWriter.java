package ar.com.carloscurotto.swiftalliance.migrator.writer;

import ar.com.carloscurotto.swiftalliance.migrator.record.SwiftRecord;
import ar.com.carloscurotto.swiftalliance.migrator.service.AbstractService;

public class SwiftConsoleWriter extends AbstractService implements Writer<SwiftRecord> {

	@Override
	public void write(SwiftRecord record) {
		System.out.println(record);
	}

	@Override
	protected void doOpen() {
		System.out.println("Opening console writer...");
	}

	@Override
	protected void doClose() {
		System.out.println("Closing console writer...");
	}

}

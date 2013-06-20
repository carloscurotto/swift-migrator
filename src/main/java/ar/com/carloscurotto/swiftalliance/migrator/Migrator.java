package ar.com.carloscurotto.swiftalliance.migrator;

import ar.com.carloscurotto.swiftalliance.migrator.reader.Reader;
import ar.com.carloscurotto.swiftalliance.migrator.record.Record;
import ar.com.carloscurotto.swiftalliance.migrator.service.AbstractService;
import ar.com.carloscurotto.swiftalliance.migrator.writer.Writer;

public class Migrator<T extends Record> extends AbstractService {
	
	private Reader<T> reader;
	private Writer<T> writer;
	
	public Migrator(Reader<T> reader, Writer<T> writer) {
		this.reader = reader;
		this.writer = writer;
	}
	
	@Override
	public void doOpen() {
		this.reader.open();
		this.writer.open();		
	}
	
	@Override
	public void doClose() {
		this.reader.close();
		this.writer.close();
	}

	public void migrate() {
		this.ensureOpened();
		while (this.reader.hasNext()) {
			T record = this.reader.read();
			this.writer.write(record);			
		}
	}

}

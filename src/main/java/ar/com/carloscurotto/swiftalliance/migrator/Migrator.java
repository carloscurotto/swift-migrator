package ar.com.carloscurotto.swiftalliance.migrator;

import ar.com.carloscurotto.swiftalliance.migrator.reader.Reader;
import ar.com.carloscurotto.swiftalliance.migrator.record.Record;
import ar.com.carloscurotto.swiftalliance.migrator.service.AbstractService;
import ar.com.carloscurotto.swiftalliance.migrator.writer.Writer;

/**
 * Represents a component that know how to migrate records for a specific type.
 * 
 * @author carloscurotto
 * 
 * @param <T>
 *            the specified type record to migrate.
 */
public class Migrator<T extends Record> extends AbstractService {

	/**
	 * The {@link Reader} to use.
	 */
	private Reader<T> reader;

	/**
	 * The {@link Writer} to use.
	 */
	private Writer<T> writer;

	/**
	 * Constructor.
	 * 
	 * @param reader
	 *            the reader to use.
	 * @param writer
	 *            the writer to use.
	 */
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

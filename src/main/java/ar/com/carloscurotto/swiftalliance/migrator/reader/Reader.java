package ar.com.carloscurotto.swiftalliance.migrator.reader;

import ar.com.carloscurotto.swiftalliance.migrator.record.Record;
import ar.com.carloscurotto.swiftalliance.migrator.service.Service;

/**
 * Represents a component that can read reocrds of the specified type.
 * 
 * @author carloscurotto
 * 
 * @param <T>
 *            the specified type record to read.
 */
public interface Reader<T extends Record> extends Service {

	/**
	 * Reads the next record of the specified type.
	 * 
	 * @return the record read.
	 */
	public T read();

	/**
	 * Tells if there is another record to read.
	 * 
	 * @return true if there is another reacord to read, otherwise false.
	 */
	public boolean hasNext();

}

package ar.com.carloscurotto.swiftalliance.migrator.writer;

import ar.com.carloscurotto.swiftalliance.migrator.service.Service;

/**
 * Represents a component that can write records of a specified type.
 * 
 * @author carloscurotto
 * 
 * @param <T>
 *            the specified type record to write.
 */
public interface Writer<T> extends Service {

	/**
	 * Writes the given record.
	 * 
	 * @param record
	 *            the record to write.
	 */
	public void write(T record);

}

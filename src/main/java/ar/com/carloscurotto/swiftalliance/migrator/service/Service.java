package ar.com.carloscurotto.swiftalliance.migrator.service;

/**
 * Represents a service that can perform some operation.
 * 
 * @author carloscurotto
 * 
 */
public interface Service {

	/**
	 * Opens the service to start working.
	 */
	public void open();

	/**
	 * Closes the service to stop working.
	 */
	public void close();

	/**
	 * Tells if the service is already opened.
	 * 
	 * @return true if the service is already opened, otherwise false.
	 */
	public boolean isOpened();

	/**
	 * Tells if the service is already closed.
	 * 
	 * @return true if the service is already closed, otherwise false.
	 */
	public boolean isClosed();

	/**
	 * Ensures if the service is already opened.
	 */
	public void ensureOpened();

	/**
	 * Ensures if the service is already closed.
	 */
	public void ensureClosed();

}

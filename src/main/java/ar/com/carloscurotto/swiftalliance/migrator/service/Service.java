package ar.com.carloscurotto.swiftalliance.migrator.service;

public interface Service {
	
	public void open();
	public void close();
	
	public boolean isOpened();
	public boolean isClosed();
	
	public void ensureOpened();
	public void ensureClosed();

}

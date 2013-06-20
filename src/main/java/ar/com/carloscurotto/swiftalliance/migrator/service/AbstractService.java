package ar.com.carloscurotto.swiftalliance.migrator.service;

public abstract class AbstractService implements Service {
	
	private boolean opened;
	
	@Override
	public void open() {
		try {
			this.ensureClosed();
			this.doOpen();
			this.opened = true;
		} catch (Exception e) {
			throw new RuntimeException("Error opening service.", e);
		}
	}
	
	protected abstract void doOpen();
	
	@Override
	public boolean isOpened() {
		return this.opened;
	}

	@Override
	public void ensureOpened() {
		if (!this.isOpened()) {
			throw new RuntimeException("Service not opened.  Call open first.");
		}
	}

	@Override
	public void close() {
		try {
			this.doClose();
			this.opened = false;
		} catch (Exception e) {
			throw new RuntimeException("Error closing service.", e);
		}
	}
	
	protected abstract void doClose();
	
	@Override
	public boolean isClosed() {
		return !this.opened;
	}
	
	@Override
	public void ensureClosed() {
		if (!this.isClosed()) {
			throw new RuntimeException("Service not closed.  Call close first.");
		}
	}
}

package ar.com.carloscurotto.swiftalliance.migrator.reader;

import ar.com.carloscurotto.swiftalliance.migrator.service.Service;

public interface Reader<T> extends Service {
	
	public T read();
	
	public boolean hasNext();

}

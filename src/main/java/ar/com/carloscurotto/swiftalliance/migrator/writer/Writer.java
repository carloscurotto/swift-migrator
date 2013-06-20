package ar.com.carloscurotto.swiftalliance.migrator.writer;

import ar.com.carloscurotto.swiftalliance.migrator.service.Service;

public interface Writer<T> extends Service {

	public void write(T record);
	
}

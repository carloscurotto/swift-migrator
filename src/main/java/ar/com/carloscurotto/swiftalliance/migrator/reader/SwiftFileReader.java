package ar.com.carloscurotto.swiftalliance.migrator.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ar.com.carloscurotto.swiftalliance.migrator.record.SwiftRecord;
import ar.com.carloscurotto.swiftalliance.migrator.service.AbstractService;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class SwiftFileReader extends AbstractService implements
		Reader<SwiftRecord> {

	private String filePath;
	private BufferedReader reader;
	private List<String> currentRecordLines;

	public SwiftFileReader(String filePath) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(filePath),
				"Can not create a file reader for an empty file path.");
		this.filePath = filePath;
		this.currentRecordLines = new ArrayList<String>();
	}

	@Override
	public SwiftRecord read() {
		this.processCurrentRecordLines();
		
		SwiftRecord record = new SwiftRecord();
		
		record.setLocalBank(this.extractLocalBank());
		record.setLocalSession(this.extractLocalSession());
		record.setLocalSequence(this.extractLocalSequence());
		record.setTransactionType(this.extractTransactionType());
		record.setMessageType(this.extractMessageType());
		record.setLocalTime(this.extractLocalTime());
		
		record.setForeignBank(this.extractForeignBank());
		record.setForeignSession(this.extractForeignSession());
		record.setForeignSequence(this.extractForeignSequence());
		record.setForeignTime(this.extractForeignTime());
		
		record.setGenericFields(extractGenericFields());
		
		return record;
	}
	
	private void processCurrentRecordLines() {
		int processedLinesIndex = 0;
		Vector<String> processedLines = new Vector<String>();
		for (int currentLinesIndex = 0; currentLinesIndex < this.currentRecordLines.size(); currentLinesIndex++) {
			String currentLine = this.currentRecordLines.get(currentLinesIndex);
			if (!(currentLine.startsWith("{1:") || currentLine.startsWith(":") || currentLine.startsWith("-}$"))) {
				if (currentLinesIndex != 0) {
					String previousLine = processedLines.remove(processedLinesIndex - 1);
					processedLines.add(previousLine + currentLine);
				}
			} else {
				processedLines.add(processedLinesIndex, currentLine);
				processedLinesIndex++;
			}			
		}
		this.currentRecordLines = new ArrayList<String>(processedLines);
	}

	private Map<String, String> extractGenericFields() {
		Map<String, String> result = new HashMap<String, String>();
		for (String line : this.currentRecordLines) {
			if (line.startsWith(":")) {
				String key = line.substring(1, line.substring(1).indexOf(":") + 1);
				String value = line.substring(line.substring(1).indexOf(":") + 2);
				result.put(key, value);
			}
		}
		return result;
	}

	private String extractForeignTime() {
		String line = currentRecordLines.get(0);
		int beginIndex = line.indexOf("N}") - 10;
		int endIndex = beginIndex + 10;		
		return line.substring(beginIndex, endIndex);
	}

	private Long extractForeignSequence() {
		String line = currentRecordLines.get(0);
		int beginIndex = line.indexOf("N}") - 16;
		int endIndex = beginIndex + 6;		
		return Long.parseLong(line.substring(beginIndex, endIndex));
	}

	private Long extractForeignSession() {
		String line = currentRecordLines.get(0);
		int beginIndex = line.indexOf("N}") - 20;
		int endIndex = beginIndex + 4;		
		return Long.parseLong(line.substring(beginIndex, endIndex));
	}

	private String extractForeignBank() {
		String line = currentRecordLines.get(0);
		int beginIndex = line.indexOf("{2:") + 17;
		int endIndex = line.indexOf("N}") - 20;
		return line.substring(beginIndex, endIndex);				
 	}

	private Integer extractMessageType() {
		String line = currentRecordLines.get(0);
		int beginIndex = line.indexOf("{2:") + 4;
		int endIndex = beginIndex + 3;
		return Integer.parseInt(line.substring(beginIndex, endIndex));				
	}

	private String extractTransactionType() {
		String line = currentRecordLines.get(0);
		int beginIndex = line.indexOf("{2:") + 3;
		int endIndex = beginIndex + 1;
		return line.substring(beginIndex, endIndex);				
	}

	private String extractLocalTime() {
		String line = currentRecordLines.get(0);
		int beginIndex = line.indexOf("{2:") + 7;
		int endIndex = beginIndex + 10;
		return line.substring(beginIndex, endIndex);		
	}

	private Long extractLocalSession() {
		String line = currentRecordLines.get(0);
		int endIndex = line.indexOf("}") - 6;
		int beginIndex = endIndex - 4;
		return Long.parseLong(line.substring(beginIndex, endIndex));
	}

	private Long extractLocalSequence() {
		String line = currentRecordLines.get(0);
		int endIndex = line.indexOf("}");
		int beginIndex = endIndex - 6;
		return Long.parseLong(line.substring(beginIndex, endIndex));
	}

	private String extractLocalBank() {
		String line = currentRecordLines.get(0);
		int beginIndex = 6;
		int endIndex = line.indexOf("}") - 10;
		return line.substring(beginIndex, endIndex);
	}

	@Override
	public boolean hasNext() {
		try {
			this.currentRecordLines = new ArrayList<String>();
			String line = this.reader.readLine();
			if (line.startsWith("{1:")) {
				this.currentRecordLines.add(line);
				boolean endRecord = false;
				while (!endRecord && line != null) {
					line = this.reader.readLine();
					if (line != null) {
						this.currentRecordLines.add(line);
						if (line.endsWith("-}$")) {
							endRecord = true;
						}						
					}
				}
				return endRecord;
			} else {
				throw new RuntimeException(
						"Wrong formatted file.  Check that the file is properly formatted.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error reading file [" + this.filePath
					+ "].", e);
		}
	}

	@Override
	protected void doOpen() {
		try {
			this.reader = new BufferedReader(new FileReader(this.filePath));
		} catch (Exception e) {
			throw new RuntimeException("Error opening file [" + this.filePath
					+ "].", e);
		}
	}

	@Override
	protected void doClose() {
		try {
			if (this.reader != null) {
				this.reader.close();
				this.reader = null;
			}
		} catch (IOException e) {
			throw new RuntimeException("Error closing file [" + this.filePath
					+ "].", e);
		}
	}

}

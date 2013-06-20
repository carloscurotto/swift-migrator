package ar.com.carloscurotto.swiftalliance.migrator.record;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public class SwiftRecord implements Record {

	/**
	 * Local Bank
	 */
	private String localBank;
	private Long localSession;
	private Long localSequence;
	private String transactionType;
	private Integer messageType;
	private String localTime;
	
	/**
	 * Foreign Bank
	 */
	private String foreignBank;
	private Long foreignSession;
	private Long foreignSequence;
	private String foreignTime;
	
	/**
	 * Generic Fields
	 */
	private Map<String, String> fields = new HashMap<String, String>();

	public String getLocalBank() {
		return localBank;
	}

	public void setLocalBank(String localBank) {
		this.localBank = localBank;
	}

	public Long getLocalSession() {
		return localSession;
	}

	public void setLocalSession(Long localSession) {
		this.localSession = localSession;
	}

	public Long getLocalSequence() {
		return localSequence;
	}

	public void setLocalSequence(Long localSequence) {
		this.localSequence = localSequence;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getLocalTime() {
		return localTime;
	}

	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}

	public String getForeignBank() {
		return foreignBank;
	}

	public void setForeignBank(String foreignBank) {
		this.foreignBank = foreignBank;
	}

	public Long getForeignSession() {
		return foreignSession;
	}

	public void setForeignSession(Long foreignSession) {
		this.foreignSession = foreignSession;
	}

	public Long getForeignSequence() {
		return foreignSequence;
	}

	public void setForeignSequence(Long foreignSequence) {
		this.foreignSequence = foreignSequence;
	}

	public String getForeignTime() {
		return foreignTime;
	}

	public void setForeignTime(String foreignTime) {
		this.foreignTime = foreignTime;
	}

	public Map<String, String> getFields() {
		return fields;
	}
	
	public String getField(Integer number) {
		return this.fields.get(number);
	}

	public void setGenericFields(Map<String, String> fields) {
		this.fields = fields;
	}
	
	public void addField(String key, String value) {
		this.fields.put(key, value);
	}

	@Override
	public String toString() {
		ToStringHelper helper = Objects.toStringHelper(SwiftRecord.class);
		
		helper.add("LocalBank", this.localBank);
		helper.add("LocalSession", this.localSession);
		helper.add("LocalSequence", this.localSequence);
		helper.add("TransactionType", this.transactionType);
		helper.add("MessageType", this.messageType);
		helper.add("LocalTime", this.localTime);
		
		helper.add("ForeignBank", this.foreignBank);
		helper.add("ForeignSession", this.foreignSession);
		helper.add("ForeignSequence", this.foreignSequence);
		helper.add("ForeignTime", this.foreignTime);
		
		helper.add("GenericFields", this.fields);
		
		return helper.toString();
	}
	
}

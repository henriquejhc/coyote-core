package org.coyote.core.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorResult {

	private boolean validator;
	private List<String> messageList;

	public ValidatorResult() {
		this.validator = true;
		this.messageList = new ArrayList<String>();
	}

	public void addMessage(String message) {
		setValidator(false);
		this.messageList.add(message);
	}

	public String getMessageFormated() {
		StringBuilder messageResult = new StringBuilder();
		for (String message : this.messageList) {
			messageResult.append(message);
			messageResult.append("\n");
		}
		return messageResult.toString();
	}

	private void setValidator(boolean validator) {
		this.validator = validator;
	}

	public boolean isValidator() {
		return validator;
	}

	public List<String> getMessageList() {
		return messageList;
	}

}
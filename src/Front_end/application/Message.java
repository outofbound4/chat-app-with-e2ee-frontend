package application;

import com.google.gson.annotations.SerializedName;

public class Message {
		@SerializedName("sender")
		public Long sender;
		
		@SerializedName("receipient")
		public Long receipient;

		@SerializedName("message")
		public String message;

		public Long getSender() {
			return sender;
		}

		public void setSender(Long sender) {
			this.sender = sender;
		}

		public Long getReceipient() {
			return receipient;
		}

		public void setReceipient(Long receipient) {
			this.receipient = receipient;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

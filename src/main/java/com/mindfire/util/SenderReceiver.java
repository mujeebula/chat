package com.mindfire.util;

public class SenderReceiver {

	private String sendername;
	private String receivername;

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	@Override
	public String toString() {
		return "SenderReceiver [sendername=" + sendername + ", receivername=" + receivername + "]";
	}

}

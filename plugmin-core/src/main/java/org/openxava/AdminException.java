package org.openxava;

public class AdminException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public AdminException() {
		
	}
	
	public AdminException(String msg) {
		this.setMsg(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

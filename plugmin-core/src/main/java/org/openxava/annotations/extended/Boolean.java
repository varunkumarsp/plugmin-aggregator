package org.openxava.annotations.extended;

public enum Boolean {
	TRUE(new java.lang.Boolean(true)),
	FALSE(new java.lang.Boolean(false)),
	NULL(null);
	
	private final java.lang.Boolean bool;

	private Boolean(java.lang.Boolean bool) {
		this.bool = bool;
	}

	public java.lang.Boolean getBool() {
		return bool;
	}

	public java.lang.Boolean getValue() {
		return bool;
	}
	
}

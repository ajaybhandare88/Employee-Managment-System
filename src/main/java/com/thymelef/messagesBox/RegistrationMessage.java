package com.thymelef.messagesBox;

public class RegistrationMessage {
	
	private String contain;
	private String color;
	
	public RegistrationMessage(String cantain,String colour)
	{
		this.contain=cantain;
		this.color=colour;
	}

	public String getContain() {
		return contain;
	}

	public void setContain(String contain) {
		this.contain = contain;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}

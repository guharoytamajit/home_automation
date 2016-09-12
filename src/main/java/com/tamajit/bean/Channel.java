package com.tamajit.bean;

public class Channel {
	private String channelId;
	private boolean active;
	private boolean working;
	private boolean state;

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getWorking() {
		return this.working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

	public boolean getState() {
		return this.state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}

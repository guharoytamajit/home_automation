package com.tamajit.bean;

import java.util.ArrayList;

public class Device {
	private Mode mode;
	private String userId;
	private String deviceId;
	private boolean active;
	private boolean working;
	private ArrayList<Channel> channels;

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	public ArrayList<Channel> getChannels() {
		return this.channels;
	}

	public void setChannels(ArrayList<Channel> channels) {
		this.channels = channels;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
}

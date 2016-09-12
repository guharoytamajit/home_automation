package com.tamajit.bean;

import java.util.ArrayList;

public class UserStatusDTO {
	private String userId;
	private boolean active;
	private ArrayList<Device> deviceList;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<Device> getDeviceList() {
		if(this.deviceList==null){
			return new ArrayList<Device>();
		}
		return this.deviceList;
	}

	public void setDeviceList(ArrayList<Device> deviceList) {
		this.deviceList = deviceList;
	}
}

package com.tamajit;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.tamajit.bean.Device;

@Component
public class DeviceManager {
	private ConcurrentHashMap<String, Device> userIdUserStatusMap = new ConcurrentHashMap<String, Device>();

	public void addDevice(Device device) {
		userIdUserStatusMap.put(device.getDeviceId(), device);
	}
	public void removeDevice(String deviceId) {
		userIdUserStatusMap.remove(deviceId);
	}
}

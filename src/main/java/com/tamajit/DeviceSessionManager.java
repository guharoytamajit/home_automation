package com.tamajit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class DeviceSessionManager {
	private Map<String, WebSocketSession> deviceIdDeviceWebsocketMap = new ConcurrentHashMap<String, WebSocketSession>();

	public void addDeviceSession(String completeDeviceId,
			WebSocketSession webSocketSession) {
		this.deviceIdDeviceWebsocketMap.put(completeDeviceId, webSocketSession);
	}
	public void removeDeviceSession(String completeDeviceId){
		this.deviceIdDeviceWebsocketMap.remove(completeDeviceId);
	}
	public WebSocketSession getDeviceSession(String deviceId){
		return this.deviceIdDeviceWebsocketMap.get(deviceId);
	}
	
	public boolean hasOpenConnection(String deviceId){
		WebSocketSession webSocketSession = deviceIdDeviceWebsocketMap.get(deviceId);
		if(webSocketSession==null)return false;
		if(webSocketSession.isOpen())return true;
		return false;
	}

}

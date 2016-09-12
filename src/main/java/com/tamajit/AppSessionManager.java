package com.tamajit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class AppSessionManager {
	private Map<String,WebSocketSession> deviceIdAppWebsocketMap=new ConcurrentHashMap<String,WebSocketSession>();

	public void addAppSession(String completeDeviceId,
			WebSocketSession webSocketSession) {
		this.deviceIdAppWebsocketMap.put(completeDeviceId, webSocketSession);
	}
	public void removeAppSession(String completeDeviceId){
		this.deviceIdAppWebsocketMap.remove(completeDeviceId);
	}
	
	public WebSocketSession getAppSession(String deviceId){
		return this.deviceIdAppWebsocketMap.get(deviceId);
	}
	public boolean hasOpenConnection(String deviceId){
		WebSocketSession webSocketSession = deviceIdAppWebsocketMap.get(deviceId);
		if(webSocketSession==null)return false;
		if(webSocketSession.isOpen())return true;
		return false;
	}
}

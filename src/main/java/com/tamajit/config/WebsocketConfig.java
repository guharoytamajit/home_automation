package com.tamajit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamajit.AppSessionManager;
import com.tamajit.DeviceManager;
import com.tamajit.DeviceSessionManager;
import com.tamajit.bean.Device;
import com.tamajit.bean.Mode;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	DeviceSessionManager deviceSessionManager;
	@Autowired
	AppSessionManager appSessionManager;
	@Autowired
	DeviceManager deviceManager;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new DeviceWebSocketHandler(), "/deviceRegister");
		registry.addHandler(new AppWebSocketHandler(), "/appRegister");
	}

	class DeviceWebSocketHandler extends TextWebSocketHandler {

		@Override
		public void afterConnectionClosed(WebSocketSession session,
				CloseStatus status) throws Exception {
		}

		@Override
		public void afterConnectionEstablished(WebSocketSession session)
				throws Exception {

		}

		@Override
		protected void handleTextMessage(WebSocketSession session,
				TextMessage message) throws Exception {
			// TODO Authenticate Device
			Device device = objectMapper.readValue(message.getPayload(),
					Device.class);
			if (device.getMode() == Mode.SEND) {
//				deviceManager.addDevice(device);
				if (appSessionManager.hasOpenConnection(device.getDeviceId())) {
					WebSocketSession appSession = appSessionManager
							.getAppSession(device.getDeviceId());
					device.setMode(Mode.RECEIVE);
					// synchronized(socketSession) {
					appSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(device)));
					// }
				}
			} else if (device.getMode() == Mode.REGISTER) {
				deviceSessionManager.addDeviceSession(device.getDeviceId(),
						session);
			} else if (device.getMode() == Mode.RECEIVE) {
				
			} else {
				throw new RuntimeException("You must set mode in DTO");
			}
		}

		@Override
		public void handleTransportError(WebSocketSession session,
				Throwable exception) throws Exception {

			super.handleTransportError(session, exception);
		}

	}

	class AppWebSocketHandler extends TextWebSocketHandler {

		@Override
		public void afterConnectionClosed(WebSocketSession session,
				CloseStatus status) throws Exception {
		}

		@Override
		public void afterConnectionEstablished(WebSocketSession session)
				throws Exception {
		}

		@Override
		protected void handleTextMessage(WebSocketSession session,
				TextMessage message) throws Exception {
			// TODO Authenticate Device
			Device device = objectMapper.readValue(message.getPayload(),
					Device.class);
			if (device.getMode() == Mode.SEND) {
				if (deviceSessionManager.hasOpenConnection(device.getDeviceId())) {
					WebSocketSession deviceSession = deviceSessionManager
							.getDeviceSession(device.getDeviceId());
					// synchronized(socketSession) {
					deviceSession.sendMessage(message);
					// }
				}
			} else if (device.getMode() == Mode.REGISTER) {
				appSessionManager.addAppSession(device.getDeviceId(), session);

			} else if (device.getMode() == Mode.RECEIVE) {
				session.sendMessage(message);

			}  else {
				throw new RuntimeException("You must set mode in DTO");
			}
		}

		@Override
		public void handleTransportError(WebSocketSession session,
				Throwable exception) throws Exception {

			super.handleTransportError(session, exception);
		}

	}
}

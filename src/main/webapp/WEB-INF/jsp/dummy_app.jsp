<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<style>
.arduino {
	background-color: #13c;
	display: inline;
	padding: 10px;
}

.device {
	background-color: #337;
	display: inline;
	padding: 10px;
}
</style>
	<script type="text/javascript" src="../../jquery-2.1.4.js"></script>

	<script type="text/javascript">
		var ws;
		var refreshIntervalId;
		var registered = false;
		var device;
		function sendMessage() {
			ws.send(JSON.stringify(buildStatus()));
		}

		function registerWS() {
			ws = new WebSocket("ws://localhost:8080/appRegister");
			ws.onmessage = function(event) {
				console.log(event.data);
				 device = JSON.parse(event.data);

				buildChannelView()
			}
			ws.onopen = function() {
				console.log("ws connection established ");
				if (registered == false) {
					registered = true;
					ws.send(buildRegisterRequest());
				}
			}
			ws.onclose = function(event) {
				console.log("ws connection closed");
				registered = false;
			}

		}
		function unregisterWS() {
			ws.close();
		}

		var C1, C2, C3, C4;
		var channelList = [ C1, C2, C3, C4 ];
		var device = {};
/* 		function buildStatus() {
			device.deviceId = $("#deviceId").val();
			device.channels = channelList;
			console.log(JSON.stringify(device));
			return device;
		} */
		function buildRegisterRequest() {
			device.deviceId = $("#deviceId").val();
			device.mode = "REGISTER";
			var regRequet = JSON.stringify(device);
			device.mode = null;
			return regRequet;
		}

		function toogleRequest(channelid, channelstate) {
			var d={};
			d.deviceId = device.deviceId;
			d.mode = "SEND";
			d.channels = [ {
				channelId : channelid,
				state : channelstate
			} ];
			ws.send(JSON.stringify(d));
		}
		function activateDevice() {
			refreshIntervalId = setInterval(function() {
				sendMessage()
			}, 10000);
		}
		function deActivateDevice() {
			clearInterval(refreshIntervalId);
		}
		function buildChannelView() {
			var buttonStr = "Relay";
			for (c in device.channels) {
				if(device.channels[c].state){
					
				buttonStr += '<button style="background-color : #4a4" class="channel" onClick="toogleRequest(\''
						+ device.channels[c].channelId + '\',' + !device.channels[c].state + ')" id="' + device.channels[c].channelId + '">'+device.channels[c].channelId+'</button>'
				}else{
					buttonStr += '<button style="background-color : #a22" class="channel" onClick="toogleRequest(\''
						+ device.channels[c].channelId + '\',' + !device.channels[c].state + ')" id="' + device.channels[c].channelId + '">'+device.channels[c].channelId+'</button>'
					
				}

			}
			$("#appscreen").html(buttonStr);
		}
	</script>
	<h2>Dummy App</h2>


	<input id="deviceId" placeholder="Device # to connect">
	<button onClick="registerWS()">Connect</button>
	<br>
	<br>
	<div class="device" id="appscreen" style="padding-right: 60px"></div>
	<br>
	<br>
	<!-- <button onclick="buildStatus()">Status</button> -->
	<button onclick="registerWS()">Register</button>
	<button onclick="unregisterWS()">Un Register</button>
<!-- 	<button onclick="registerWS()">Activate</button>
	<button onclick="unregisterWS()">De-Activate</button> -->
</body>
</html>
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
		var registered=false;
		function sendMessage() {
			ws.send(buildStatus());
		}

		function registerWS() {
			ws = new WebSocket("ws://localhost:8080/deviceRegister");
			console.log(buildRegisterRequest());
			ws.onmessage = function(event) {
				console.log(event.data);
				var d = JSON.parse(event.data);
				var channel = d.channels[0];

				for (c in channelList) {
					if (channelList[c].channelId == channel.channelId) {
						channelList[c].state = channel.state;
						updateView( channelList[c].channelId, channel.state)
						break;
					}
				}

			}
			ws.onopen = function() {
				console.log("ws connection established ");
				if(registered==false){
					registered=true;
				ws.send(buildRegisterRequest());
				}
			}
			ws.onclose = function(event) {
				console.log("ws connection closed");
				registered=false;
			}
		}
		function unregisterWS() {
			ws.close();
		}

		var C1 = {
			channelId : "C1",
			state : false,
			active : true,
			working : true
		};
		var C2 = {
			channelId : "C2",
			state : false,
			active : true,
			working : true
		};
		var C3 = {
			channelId : "C3",
			state : false,
			active : true,
			working : true
		};
		var C4 = {
			channelId : "C4",
			state : false,
			active : true,
			working : true
		};
		var channelList = [ C1, C2, C3, C4 ];
		var device = {};
		function buildStatus() {
			device.deviceId = $("#deviceId").val();
			device.channels = channelList;
			device.mode = "SEND";
			var regRequet=JSON.stringify(device);
			device.mode=null;
			return regRequet;
		}
		function buildRegisterRequest() {
			d={};
			d.deviceId = $("#deviceId").val();
			d.mode = "REGISTER";
			var regRequet=JSON.stringify(d);
			d.mode=null;
			return regRequet;
		}

		function toogle(id) {
			window[id]['state'] = !window[id]['state'];
			if (window[id]['state']) {
				$("#" + id).css({
					"background-color" : "#4a4"
				});
			} else {
				$("#" + id).css({
					"background-color" : "#a22"
				});
			}
		} 
		function updateView(id,state){
			if(state){
				$("#" + id).css({
					"background-color" : "#4a4"
				});
			} else {
				$("#" + id).css({
					"background-color" : "#a22"
				});
			}
			
		}
		function activateDevice() {
			refreshIntervalId = setInterval(function() {
				sendMessage()
			}, 10000);
		}
		function deActivateDevice() {
			clearInterval(refreshIntervalId);
		}
	</script>
	<h2>Dummy Device</h2>

	<div class="arduino">
		Arduino <input id="deviceId" placeholder="Device #">
	</div>
	<br>
	<br>
	<div class="device" style="padding-right: 60px">
		Relay
		<button class="channel" style="background-color : #a22" onClick="toogle('C1')" id="C1">C1</button>
		<button class="channel" style="background-color : #a22" onClick="toogle('C2')" id="C2">C2</button>
		<button class="channel" style="background-color : #a22" onClick="toogle('C3')" id="C3">C3</button>
		<button class="channel" style="background-color : #a22" onClick="toogle('C4')" id="C4">C4</button>
	</div>
	<br>
	<br>
	<button onclick="buildStatus()">Log Status</button>
	<button onclick="registerWS()">Register</button>
	<button onclick="unregisterWS()">Un Register</button>
	<button onclick="sendMessage()">Publish Status</button>
<!-- 	<button onclick="registerWS()">Activate</button>
	<button onclick="unregisterWS()">De-Activate</button> -->
</body>
</html>
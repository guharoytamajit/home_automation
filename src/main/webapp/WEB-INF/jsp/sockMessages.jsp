<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Bus Tracker</title>
    <style>
      html, body {
        height: 95%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 95%;
      }
    </style>
</head>
<body>
	<script type="text/javascript" src="../../jquery-2.1.4.js"></script>
	
	<script type="text/javascript">		
		var ws;
		function sendMessage() {
			ws.send($("#message").val());
			$("#message").val("");
		}

		function registerWS() {
			ws = new WebSocket("/deviceRegister");
			ws.onmessage = function(event) {
				console.log(event.data);
				var data = JSON.parse(event.data);
			
			}
			ws.onopen = function() {
				console.log("ws connection established ");
			}
			ws.onclose = function(event) {
				console.log("ws connection closed");
			}

		}
		function unregisterWS() {
			ws.close();
		}

	</script>
	<h2>Dummy Device</h2>
	<textarea id="message" rows="3" cols="5"></textarea>
	<button onclick="sendMessage()">Send</button>
	<button onclick="registerWS()">Register</button>
	<button onclick="unregisterWS()">Un Register</button>
	<div id="map"></div>
</body>
</html>
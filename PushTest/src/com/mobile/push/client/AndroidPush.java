package com.mobile.push.client;


public class AndroidPush {
	
	public native static void init() /*-{
		var push = $wnd.PushNotification.init({
			"android" : {
				"senderID" : "954310322655"
			},
			"ios" : {
				"alert" : "true",
				"badge" : "true",
				"sound" : "true"
			},
			"windows" : {}
		});

		push.on('registration', function(data) {
			$wnd.alert("registered");
		});

		push.on('notification', function(data) {
			$wnd.alert("Received message");
		});

		push.on('error', function(e) {
			$wnd.alert("Got error");
		});
	}-*/;

}

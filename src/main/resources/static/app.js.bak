var stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	} else {
		$("#conversation").hide();
	}
	$("#greetings").html("");
}

function connect() {
	socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		sessionId = /\/([^\/]+)\/websocket/.exec(socket._transport.url)[1];
		console.log(sessionId);
		/*
		 * stompClient.subscribe('/topic/greetings', function(greeting) {
		 * console.log(greeting.body.message); });
		 */
	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	stompClient.send("/app/private/" + $("#receiver").val(), {}, JSON
			.stringify({
				'content' : $("#name").val()
			}));
}

function login() {

	stompClient.send("/app/login", {}, JSON.stringify({
		'userName' : $("#username").val(),
		'password' : $("#password").val()
	}));
	// on login success
	stompClient.subscribe('/user/position-updates', function(msg) {
		console.log('from: /user/position-updates ->' + msg.body.message);
	});

	stompClient.subscribe('/topic/greeting-' + sessionId, function(greeting) {
		console.log(greeting.body.content);
	});
}

function showGreeting(message) {
	$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() {
		connect();
	});
	$("#disconnect").click(function() {
		disconnect();
	});
	$("#send").click(function() {
		sendName();
	});
	$("#login").click(function() {
		login();
	});
});

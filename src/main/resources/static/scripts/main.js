function connect() {
	const socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log("Connected: " + frame);
		stompClient.subscribe('/topic/counter', function(response) {
			draw(response.body);
		});
		$.get("/count", function(response) {
			draw(response);
			$("div#button").html('<button onclick=\"add()\" class=\"button\">+1</button>');
		});
	});
}

function draw(value) {
	console.log("Drawing...");
	$("div#counter").html(value);
}

function disconnect(){
	console.log("Disconnecting...");
	stompClient.disconnect();
}
function add(){
	stompClient.send("/app/add");
}

connect()
$(window).on('unload', function(){
	disconnect();
})
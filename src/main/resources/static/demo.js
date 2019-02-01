var s;
var stompClient;

function setUpSocket() {
    s = new SockJS('/socket');
    stompClient = Stomp.over(s);

    var clientId = document.getElementById("clientId").value;

    putMessage('Connecting...')

    stompClient.connect({}, function () {
        console.log('Socket connected!');
        putMessage('Connected to server!');
        stompClient.subscribe('/user/' + clientId + '/msg', function (data) {
            putMessage(data.body);
        });

    });

}

function sendMessage() {

    var receiverId = document.getElementById("receiverId").value;
    var clientId = document.getElementById("clientId").value;
    var message = document.getElementById("messageInput").value;

    stompClient.send("/app/one2one", {}, JSON.stringify({
        clientId: clientId,
        message: message,
        receiverId: receiverId
    }));
    putMessage(clientId + ':' + message);

}

function putMessage(msg) {
    document.getElementById("messageBox").innerHTML += '<span>' + msg + '</span> <br>';
}

function setUpSocket() {
    var s = new SockJS('/socket');
    var stompClient = Stomp.over(s);
    stompClient.connect({}, function () {
        console.log('notice socket connected!');
        stompClient.subscribe('/topic/notice', function (data) {
            document.getElementById("notice").innerHTML = data;
        });
    });

}

setUpSocket();

function sendMessage() {
    var m = $('input#messageInput').val().trim();

    var d = {
        "message": m,
        "clientId" : 1
    };
    var k = JSON.stringify(d);

    $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader('Content-Type', 'application/json');
        },
        url: '/message',
        type: 'post',
        dataType:'json',
        data: k,
        success: function(result){
            console.log('Sent message', m);
        }
    });

}
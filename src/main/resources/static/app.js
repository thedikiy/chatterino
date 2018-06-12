var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

function connect() {
    var socket = new SockJS('/socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (message) {
            getMessages(function(data){
              $("#message-table").html("");
              getMessagePage(data["page"]["totalPages"]-1);
	      renderPageBar();
            })
        });
        renderPageBar();
    });
}

function renderPageBar(){
  $(".page-bar").html("");
  getMessages(function(data){
    for(var i = 1 ; i <= data["page"]["totalPages"]; i++){
      $(".page-bar").append("<a href=\"#\" class=\"page-index\"> " + i + "</a>");
    }
    $(".page-index").on("click", function(event){
        event.preventDefault();
        $("#message-table").html("");
        getMessagePage($(this).text()-1);
    })
  });
}

function getMessages(done){
  $.get("/messages?size=15").done(done);
}

function getMessagePage(page){
  return $.get("/messages?size=15&page="+page).done(function(data){
    $.each(data["_embedded"]["messages"],function(index, message){
      showMessage(message["content"]);
    });
  });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'content': $("#message").val()}));
}

function showMessage(message) {
      $("#message-table").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

connect();

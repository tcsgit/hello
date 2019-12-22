var userId = null;
var userName = null;
var userAvatar = null;
var stompClient = null;

var userPaneBody = $('user_pane_body');
var messagePaneBody = $('message_pane_body');

function $(elementId) {
	return document.getElementById(elementId);
}

function selectAvatar() {
	if (userAvatar) $(userAvatar).classList.remove('avatar-selected');
	userAvatar = event.target.id;	
	event.target.classList.add('avatar-selected');
}

function connect() {
	userName = $('user_name').value.trim();

	if(userName && userAvatar) {
		 $('join_info').innerHTML = 'Connecting ...';
		 $('join_info').classList.add('blink');
				
		var socket = new SockJS('/websocket');
		stompClient = Stomp.over(socket);

		stompClient.connect({}, onConnect, onError);		
	}
	event.preventDefault();
}

function disconnect() {
	if (stompClient !== null) stompClient.disconnect();

	 $('join_info').innerHTML = 'Hi, join and say hello ;)';
	 $('join_info').classList.remove('blink');
	
	$(userAvatar).classList.remove('avatar-selected');
		
	$('messaging_page').classList.add('hidden');
	$('join_page').classList.remove('hidden');
	
	$('user_name').value = '';
	$('user_name').focus();
}

function onConnect() {
	stompClient.subscribe('/topic/hello', onMessage);
	
	userId = generateUID();
		
	var jsonMessage = {
		type: 'JOIN',
		senderid : userId,
		sender: userName,
		avatar: userAvatar,
		timestamp: getTimestamp()
	};
    
	stompClient.send('/app/addUser', {}, JSON.stringify(jsonMessage));

	$('join_page').classList.add('hidden');
	$('messaging_page').classList.remove('hidden');
}


function onError() {
	 $('join_info').innerHTML = 'Could not connect, please try again ;(';
	 $('join_info').classList.remove('blink');
}

function sendMessage() {
	var message = $('message').value.trim();

	if(message && stompClient) {
		var jsonMessage = {
			type: 'CHAT',
			content: $('message').value,
			senderid: userId,
			sender: userName,
			avatar: userAvatar,
			timestamp: getTimestamp()
		};

		stompClient.send('/app/sendMessage', {}, JSON.stringify(jsonMessage));
		$('message').value = '';
	}
	event.preventDefault();
}

function onMessage(payload) {
	var jsonMessage = JSON.parse(payload.body);
		
	if(jsonMessage.type == 'JOIN' || jsonMessage.type == 'LEAVE') {
		
		$('user_pane_body').innerHTML='';
		
		var userList = jsonMessage.content.split('|');
		
		for (const item of userList) {		
			var user = item.split('~');
			var self = userId == user[0] ? '-self' : '';
	
			var userItem = document.createElement('div');
			userItem.classList.add('user-item' + self);
	
			var userAvatar = document.createElement('img');
			userAvatar.classList.add('user-avatar');
			userAvatar.src = 'img/' + user[2] + '.jpg';
			if (userId == user[0]) userAvatar.title = 'Joined on pod : ' + podName;
	
			var userNameText = document.createTextNode(user[1]);
	
			var userTime = document.createElement('div');
			userTime.classList.add('user-time');	
			var userTimeText = document.createTextNode(user[3]);	
			userTime.appendChild(userTimeText);
	
			userItem.appendChild(userAvatar);
			userItem.appendChild(userNameText);
			userItem.appendChild(userTime);
	
			userPaneBody.appendChild(userItem);
			userPaneBody.scrollTop = userPaneBody.scrollHeight;
		}
		
	} else if (jsonMessage.type == 'CHAT') {
		var self = userId == jsonMessage.senderid ? '-self' : '';
		
		var messageItem = document.createElement('div');
		messageItem.classList.add('message-item');

		var messageAvatar = document.createElement('img');
		messageAvatar.classList.add('message-avatar' + self);
		messageAvatar.src = 'img/' + jsonMessage.avatar + '.jpg';
  
		var messageBubble = document.createElement('div');
		messageBubble.classList.add('message-bubble' + self);
		var messageBubbleHead = document.createElement('p');
		var messageBubbleHeadText = document.createTextNode(jsonMessage.sender + ' ' + jsonMessage.timestamp);
		messageBubbleHead.appendChild(messageBubbleHeadText);
		var messageBubbleContent = document.createTextNode(jsonMessage.content);
		messageBubble.appendChild(messageBubbleHead);
		messageBubble.appendChild(messageBubbleContent);
  
		messageItem.appendChild(messageAvatar);
		messageItem.appendChild(messageBubble);
  
		messagePaneBody.appendChild(messageItem);
		messagePaneBody.scrollTop = messagePaneBody.scrollHeight;
	}
}

function getTimestamp() {
	var date = new Date();
	var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
	var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
	return hours + ':' + minutes;
}

function generateUID() {
	return '_' + Math.random().toString(36).substr(2, 9);
}

$('join_form').addEventListener('submit', connect, true);
$('message_form').addEventListener('submit', sendMessage, true);
$('off').addEventListener('click', disconnect, true);
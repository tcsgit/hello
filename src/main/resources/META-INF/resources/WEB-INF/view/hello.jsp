<html>
<head>
	<title>Hello ;)</title>
	<link rel="stylesheet" href="css/hello.css">
</head>
<body>

	<div id="join_page" class="join-page">
		<h1 id="join_info">Hi, join and say hello ;)</h1>
		<hr>
		<img id="m1" src="img/m1.jpg" class="avatar" onclick="selectAvatar();">&nbsp;&nbsp;&nbsp;
		<img id="m2" src="img/m2.jpg" class="avatar" onclick="selectAvatar();">&nbsp;&nbsp;&nbsp;
		<img id="m3" src="img/m3.jpg" class="avatar" onclick="selectAvatar();">&nbsp;&nbsp;&nbsp;
		<img id="f1" src="img/f1.jpg" class="avatar" onclick="selectAvatar();">&nbsp;&nbsp;&nbsp;
		<img id="f2" src="img/f2.jpg" class="avatar" onclick="selectAvatar();">&nbsp;&nbsp;&nbsp;
		<img id="f3" src="img/f3.jpg" class="avatar" onclick="selectAvatar();">
		<hr>
		<form id="join_form">
			<input type="text" id="user_name" placeholder="Username" maxlength="20" autocomplete="off" autofocus class="username" oninput="this.value = this.value.replace(/[|~]/g, '')"/><button type="submit" class="join">join</button>
		</form>	
	</div>
	
	<div id="messaging_page" class="messaging-page hidden">
		<div class="user-pane">
			<div class="user-pane-header">Users</div>
			<div id="user_pane_body" class="user-pane-body">

			</div>
			<div class="user-pane-footer">
			</div>
		</div>
		<div class="message-pane">
			<div class="message-pane-header">Messages<img id="off" src="img/off.png" class="off"></div>
			<div id="message_pane_body" class="message-pane-body">

			</div>
			<div class="message-pane-footer">
				<form id="message_form">
					<input type="text" id="message" placeholder="Type your message..." autocomplete="off" autofocus class="message"/><button type="submit" class="send"><img src="img/arrow.png" class="arrow"></button>
				</form>	
			</div>			
		</div>
	</div>
	
	<script>var podName = '${podName}';</script>
	<script src="/webjars/sockjs-client/sockjs.min.js"></script>
	<script src="/webjars/stomp-websocket/stomp.min.js"></script>
	<script src="js/hello.js"></script>
</body>
</html>
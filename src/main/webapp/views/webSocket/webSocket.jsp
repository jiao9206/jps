<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/views/common/common.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebSocket</title>
</head>
<body>
<button onclick="sendMessage()">发送消息</button>
<script type="text/javascript">
	var ws;
	$(function(){
		ws=new WebSocket("ws://localhost:8080/jps/testWebSocket");
		ws.onopen=function(e){
			
		};
		ws.onmessage=function(e){
			alert("服务端收到消息，并发送了回执，回执内容是："+e.data);
		}
	})
	
	function sendMessage(){
		ws.send("Button Send Message!");
	}
</script>
</body>
</html>
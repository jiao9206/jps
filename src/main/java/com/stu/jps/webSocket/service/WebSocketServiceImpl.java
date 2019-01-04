package com.stu.jps.webSocket.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketServiceImpl extends TextWebSocketHandler {

	private static final Map<String,WebSocketSession> users=new HashMap<String,WebSocketSession>();
	/**
	 * 成功建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		try {
			String userid=UUID.randomUUID().toString().replaceAll("-", "");
			users.put(userid, session);
			session.getAttributes().put("userId", userid);
			session.sendMessage(new TextMessage(userid+"，您已经与我们建立了通讯！"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 关闭连接的时候
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
		try {
			String username= (String) session.getAttributes().get("userId");
			users.remove(username);
			System.out.println("用户"+username+"已退出！closeStatus="+closeStatus);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 接收客户端发送的消息(ws.send())
	 */
	@Override
	public void handleMessage(WebSocketSession session,WebSocketMessage<?> message) {
		try {
			String messge=(String) message.getPayload();
			String username= (String) session.getAttributes().get("userId");
			session.sendMessage(new TextMessage(username+"，我们接收到了你的消息："+messge));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 给某个用户发消息
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(String userName, TextMessage message) {
		try {
			WebSocketSession user=users.get(userName);
			user.sendMessage(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

}

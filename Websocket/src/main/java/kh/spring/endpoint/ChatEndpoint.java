package kh.spring.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonObject;

import kh.spring.configurator.WSConfig;

@ServerEndpoint(value="/chat", configurator = WSConfig.class)
public class ChatEndpoint {
	
	private HttpSession session;
	private static List<Session> clients = Collections.synchronizedList(new ArrayList<>());
	
	@OnOpen
	public void onConnect(Session session, EndpointConfig config) {
		clients.add(session);
		this.session = (HttpSession)config.getUserProperties().get("hSession");
	}
	
	@OnMessage
	public void onMessage(String message) {
		// 메세지를 같은 웹소켓 앤드포인트에 접속한 클라이언트들에게 배포해야한다.
		
		
		String userID = (String)this.session.getAttribute("loginID");
		JsonObject obj = new JsonObject();
		obj.addProperty("id", userID);
		obj.addProperty("message", message);
		
		synchronized(clients){
			for(Session client : clients) {
				try {
					client.getBasicRemote().sendText(obj.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("클라이언트의 접속이 끊어졌습니다.");
	}
}

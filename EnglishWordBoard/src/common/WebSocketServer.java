package common;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// 웹 소켓 서버로 이용할 거에요.
@ServerEndpoint("/wsServer")
public class WebSocketServer {

	private ShareSocketList share = ShareSocketList.getSharedSocket();
	 
	@OnOpen
	public void openServer(Session session){
		System.out.println("칼라이언트 호출: "+session);
		share.addSession(session);
		//session은 클라이언트의 websocket이라고 생각하면되요
	}
	
	@OnClose
	public void closeClient(Session session){
		share.removeSession(session);
		System.out.println("칼라이언트 접속종료: "+session);	
	}
	
	@OnMessage
	public void receive(String msg, Session session){
		JSONParser paser = new JSONParser();
		try{
		JSONObject object = (JSONObject) (paser.parse(msg));
		String json = object.toJSONString();
		share.broadCast(json,session);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}

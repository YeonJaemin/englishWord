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

// �� ���� ������ �̿��� �ſ���.
@ServerEndpoint("/wsServer")
public class WebSocketServer {

	private ShareSocketList share = ShareSocketList.getSharedSocket();
	 
	@OnOpen
	public void openServer(Session session){
		System.out.println("Į���̾�Ʈ ȣ��: "+session);
		share.addSession(session);
		//session�� Ŭ���̾�Ʈ�� websocket�̶�� �����ϸ�ǿ�
	}
	
	@OnClose
	public void closeClient(Session session){
		share.removeSession(session);
		System.out.println("Į���̾�Ʈ ��������: "+session);	
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

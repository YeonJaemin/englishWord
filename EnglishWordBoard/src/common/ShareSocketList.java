package common;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.Session;

public class ShareSocketList {

		private static ShareSocketList share = new ShareSocketList();
		private Object monitor = new Object();
		private ArrayList<Session> list = null;
		
		
		private ShareSocketList(){
			list = new ArrayList<Session>();
		}
		
		public static ShareSocketList getSharedSocket(){
			return share;
		}
		public void addSession (Session s){
			synchronized (monitor) {
				list.add(s);	
			}
			
		}
		public void removeSession (Session s){
			synchronized (monitor) {
				list.remove(s);
			}
			
		}
		
		public void broadCast(String json, Session session){
			synchronized (monitor) {
				for(Session s : list )
				{
				try {
					if(s!=session){
					s.getBasicRemote().sendText(json);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		}
	

}

# 集成Websocket       
本文基础抄袭：[SpringBoot整合websocket](https://cloud.tencent.com/developer/article/1498941)      

使用websocket简单的做一个聊天室的例子，代码完善，但未考虑断网重连，等等一些异常问题      

Spring Boot集成websocket比较简单      
1. 引入依赖   
```  
<dependency>  
     <groupId>org.springframework.boot</groupId>  
     <artifactId>spring-boot-starter-websocket</artifactId>  
</dependency>
```     
2. 启用WebSocket的支持   
```   
@Configuration  
public class WebSocketConfig {  
    @Bean  
    public ServerEndpointExporter serverEndpointExporter() {  
        return new ServerEndpointExporter();  
    }  
}
```      
3. WebSocketServer   
因为WebSocket是类似客户端服务端的形式(采用ws协议)，那么这里的WebSocketServer其实就相当于一个ws协议的Controller直接@ServerEndpoint("/websocket")、@Component启用即可，然后在里面实现@OnOpen,@onClose,@onMessage等方法  
代码很简单，收到消息就转发给所有人。   
```   
@ServerEndpoint("/websocket/{roomid}")
@Component
public class WebsocketServer {

	private static CopyOnWriteArraySet<WebsocketServer> webSocketSet = new CopyOnWriteArraySet<WebsocketServer>();
	private Session mSession;
	private String mRoomId = "";

	@OnOpen
	public void onOpen(Session session, @PathParam("roomid") String roomids) {
		this.mSession = session;
		this.mRoomId = roomids;
		webSocketSet.add(this);
	}

	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	@OnMessage
	public void onMessage(Session session, String msg) {
		System.err.println("" + msg);
		sendGroup(msg, this.mRoomId, session);
	}

	public static void sendGroup(String msg, String roomid, Session session) {
		for (WebsocketServer item : webSocketSet) {
			if (item.mRoomId.equals(roomid)) {
				try {
					if (item.mSession.isOpen()) {
						if (item.mSession != session) {
							item.sendMsg(msg);
						}
					} else {
						webSocketSet.remove(item);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 给连接的Session发送消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	private void sendMsg(String msg) throws IOException {
		if (msg == null || msg.isEmpty()) {
			return;
		}
		if (mSession.isOpen()) {
			mSession.getBasicRemote().sendText(msg);
		}
	}

}
```     

客户端我用Android写了一个，就先不介绍了，服务器只服务只转消息的话，内容全由客户端定义 ，所以会使用Websocket就可以    。        



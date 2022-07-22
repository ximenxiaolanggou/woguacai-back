package top.damoncai.wogua.app.ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.damoncai.wogua.app.iot.entity.Temp;
import top.damoncai.wogua.app.iot.service.TempService;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ServerEndpoint("/ws/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";

    private static TempService tempService;

    @Autowired
    public void setDeviceListenerService(TempService tempService) {
        WebSocketServer.tempService = tempService;
    }

    /**
     * 连接建立成功调用的方法
     * */
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("{\"msg\":\"连接成功\"}");
        } catch (IOException e) {
            log.error("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        JSONObject object = JSONObject.parseObject(message);
        Integer type = (Integer) object.get("type");
        if(type == 1) { // 首页 温度
            String sn = (String) object.get("sn");
            tempHandle(sn);
        }
        log.info("用户消息:"+userId+",报文:"+message);
    }

    /**
     * 温度处理
     * @param sn
     */
    private void tempHandle(String sn) throws IOException {
        Map map = new HashMap(2);
        List<Temp> temps = tempService.listBySourceLimt(sn, 30);
        temps = temps.stream().sorted((e1,e2) -> e1.getCreatetime().isAfter(e2.getCreatetime()) ? 1: -1 ).collect(Collectors.toList());
        map.put("first",true);
        map.put("data",temps);
        sendMessage(JSON.toJSONString(map));
    }

    /**
     *  发生错误时候
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        //加入线程锁
        synchronized (session){
            try {
                //同步发送信息
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("服务器推送失败:"+e.getMessage());
            }
        }
    }


    /**
     * 发送自定义消息
     * */
    /**
     * 发送自定义消息
     * @param message 发送的信息
     * @param toUserId  如果为null默认发送所有
     * @throws IOException
     */
    public static void sendInfo(String message,String toUserId) throws IOException {
        //如果userId为空，向所有群体发送
//        if(StringUtils.isEmpty(toUserId)) {
        //向所有用户发送信息
        Iterator<String> itera = webSocketMap.keySet().iterator();
        while (itera.hasNext()) {
            String keys = itera.next();
            WebSocketServer item = webSocketMap.get(keys);
            item.sendMessage(message);
        }
//        }
//        //如果不为空，则发送指定用户信息
//        if(webSocketMap.containsKey(toUserId)){
//            WebSocketServer item = webSocketMap.get(toUserId);
//            item.sendMessage(message);
//        }else{
//            log.error("请求的userId:"+toUserId+"不在该服务器上");
//        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static synchronized ConcurrentHashMap<String,WebSocketServer> getWebSocketMap(){
        return WebSocketServer.webSocketMap;
    }
}


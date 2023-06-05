package top.hueng.apm.controller;

import org.springframework.stereotype.Component;
import top.hueng.apm.common.SessionManager;
import top.hueng.apm.SpringUtil;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/websocket")
public class JavaxWebSocketServerEndpoint {

    SessionManager sessionManager = SpringUtil.getBean(SessionManager.class);

    @OnOpen
    public void onOpen(Session session, EndpointConfig config,
                       @PathParam(value = "type") String type) throws InterruptedException {
        //连接建立
        System.out.println("add");
        sessionManager.add(session);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        //连接关闭
        sessionManager.remove(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        //接收文本信息
    }


    @OnError
    public void onError(Session session, Throwable e) {
        //异常处理
        new RuntimeException(e).printStackTrace();
    }

}

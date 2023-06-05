package top.hueng.apm.common;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.List;

@Component
@AllArgsConstructor
public class SessionManager {

    private final List<Session> list ;

    public void add(Session session) {
        System.out.println("add session");
        list.add(session);
        System.out.println("session id: " + session.getId());
        System.out.println(list.size());
    }

    public void remove(Session session) {
        list.remove(session);
    }

    public List<Session> getList() {
        return list;
    }

}

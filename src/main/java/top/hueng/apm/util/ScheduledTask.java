package top.hueng.apm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.hueng.apm.common.CommonResult;
import top.hueng.apm.common.SessionManager;
import top.hueng.apm.service.ProcessService;

import javax.websocket.Session;

@Component
@AllArgsConstructor
public class ScheduledTask {

    private final SessionManager sessionManager;
    @Autowired
    ProcessService processService;
    @Autowired
    ObjectMapper objectMapper;

    @Scheduled(fixedRate = 3000)
    public void scheduledTask() {
        if(sessionManager.getList().size() == 0) {
            return;
        }
        for(Session session : sessionManager.getList()) {
            try {
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(CommonResult.success(processService.getProcess(),"请求成功")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

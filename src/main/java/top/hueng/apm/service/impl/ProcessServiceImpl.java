package top.hueng.apm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hueng.apm.entity.Process;
import top.hueng.apm.service.ProcessService;
import top.hueng.apm.util.SSHHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private SSHHelper sshHelper;

    public static final String CMD_RAM_GREP = "ps auxc --sort %mem | tail -n 20";

    @Override
    public List<Process> getProcess() {
        StringBuffer stringBuffer = sshHelper.sendCmd(CMD_RAM_GREP);
        List<Process> list = toProcess(stringBuffer);
//        log.info("process:{}",process);
        return list;
    }

    public List<Process> toProcess(StringBuffer stringBuffer){
        List<Process> list = new ArrayList<>();
//        root         446  0.1  0.5  43696 37672 ?        S    09:53   0:32 python3
        Pattern pattern = Pattern.compile("(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+");
        Matcher matcher = pattern.matcher(stringBuffer);
        while (matcher.find()) {
            Process process = new Process(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(11),
                    matcher.group(3),
                    matcher.group(4),
                    matcher.group(8),
                    matcher.group(9),
                    matcher.group(10),
                    new Date()
            );
            list.add(process);
        }
        return list;
    }
}

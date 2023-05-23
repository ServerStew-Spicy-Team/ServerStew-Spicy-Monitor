package top.hueng.apm.service.impl;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hueng.apm.entity.Ram;
import top.hueng.apm.mapper.RAMMapper;
import top.hueng.apm.service.RamService;
import top.hueng.apm.util.SSHHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: RAMServiceImpl
 * Package: top.hueng.apm.service.impl
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/4/15 - 0:29
 * @Version: 1.0
 */
@Service
@Slf4j
public class RamServiceImpl extends ServiceImpl<RAMMapper, Ram> implements RamService {

    @Autowired
    private SSHHelper sshHelper;
    @Autowired
    RAMMapper ramMapper;

    public static final String CMD_RAM_GREP = "free -m | grep \"^Mem\"";
    @Override
    public Ram getRamInfo() {
        StringBuffer stringBuffer = sshHelper.sendCmd(CMD_RAM_GREP);
        Ram ram = toRAM(stringBuffer);
        log.info("ram:{}",ram);
        addRAM(ram);
        return ram;
    }
    @Override
    public boolean addRAM(Ram ram) {
        try {
            return ramMapper.insert(ram) == 1;
        } catch (MybatisPlusException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Ram toRAM(StringBuffer stringBuffer){
        //           total        used        free      shared  buff/cache   available
    //Mem:           1998         222          69           0        1706        1583
        Pattern pattern = Pattern.compile("(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)");
        Matcher matcher = pattern.matcher(stringBuffer);
        Ram ram = null;
        if (matcher.find()) {
            ram = new Ram(
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(7)),
                    Double.parseDouble( new DecimalFormat("#0.0").format(
                            (Double.parseDouble(matcher.group(3))/Double.parseDouble(matcher.group(2)))*100)),
                    new Date()
            );
        }
        return ram;
    }
}

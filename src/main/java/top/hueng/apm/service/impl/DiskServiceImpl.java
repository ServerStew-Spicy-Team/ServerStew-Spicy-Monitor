package top.hueng.apm.service.impl;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hueng.apm.entity.Disk;
import top.hueng.apm.mapper.DiskMapper;
import top.hueng.apm.service.DiskService;
import top.hueng.apm.util.SSHHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: DiskServiceImpl
 * Package: top.hueng.apm.service.impl
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/4/15 - 0:30
 * @Version: 1.0
 */
@Service
@Slf4j
public class DiskServiceImpl extends ServiceImpl<DiskMapper, Disk> implements DiskService {
    @Autowired
    private SSHHelper sshHelper;
    @Autowired
    DiskMapper diskMapper;
    //这则表达式：表示以 /dev/ 开头行
    public static final String CMD_DF_GREP = "df -h -m | grep \"^/dev/\"";

    @Override
    public Disk getDiskInfo() {
        Disk disk = toDisk(sshHelper.sendCmd(CMD_DF_GREP));
        log.info("disk:{}",disk);
        addDisk(disk);
        return disk;
    }

    @Override
    public boolean addDisk(Disk disk) {;
        try {
            return diskMapper.insert(disk) == 1;
        } catch (MybatisPlusException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Disk toDisk(StringBuffer stringBuffer){
        // /dev/vda1          40189  5546     32907  15% /
        //“\S”表示任何非空白字符，“+”表示重复前面的内容一次或多次，“\s”表示任何空白字符，可能包括空格、制表符等
        Pattern pattern = Pattern.compile("(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)");
        Matcher matcher = pattern.matcher(stringBuffer);
        Disk disk = null;
        if (matcher.find()) {
            disk = new Disk(
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5).replaceAll("[^0-9]", "")),
                    new Date()
            );
        }
        return disk;
    }

}

package top.hueng.apm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hueng.apm.entity.Ram;

/**
 * ClassName: RAMService
 * Package: top.hueng.apm.service.impl
 * Description:
 *
 * @Author: oceanLover
 * @Create: 2023/4/15 - 0:29
 * @Version: 1.0
 */
public interface RamService extends IService<Ram> {
    public Ram getRamInfo();
    public boolean addRAM(Ram ram);
}
